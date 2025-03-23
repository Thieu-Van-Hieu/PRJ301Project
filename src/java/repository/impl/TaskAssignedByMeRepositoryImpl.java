package repository.impl;

import dto.TaskAssignedByMeResponse;

import java.sql.*;
import java.util.ArrayList;
import repository.TaskAssignedByMeRepository;
import util.DBContext;

public class TaskAssignedByMeRepositoryImpl implements TaskAssignedByMeRepository {

    @Override
    public ArrayList<TaskAssignedByMeResponse> getTasksAssignedByMe(int clubId, int memberId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select *
                    from tasks
                    where clubId = ? and assignedBy = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, memberId);
            ResultSet rs = statement.executeQuery();
            ArrayList<TaskAssignedByMeResponse> tasks = new ArrayList<>();

            while (rs.next()) {
                TaskAssignedByMeResponse task = new TaskAssignedByMeResponse();
                task.setId(rs.getInt("id"));
                task.setClubId(rs.getInt("clubId"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setDueDate(rs.getTimestamp("dueDate"));
                task.setAssignedBy(rs.getInt("assignedBy"));
                task.setAssignedTo(getAssignedMembers(task.getId()));
                tasks.add(task);
            }

            return tasks;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

    private ArrayList<Integer> getAssignedMembers(int taskId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select assignedTo
                    from task_assignees
                    where taskId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> assignedMembers = new ArrayList<>();
            while (rs.next()) {
                assignedMembers.add(rs.getInt("assignedTo"));
            }
            return assignedMembers;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

    @Override
    public boolean isTaskCompleted(int taskId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    select count(*)
                    from task_assignees
                    where taskId = ? and status != N'Đã xong'
                    """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return false;
    }

    @Override
    public boolean setTaskUncompleted(int taskId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    update task_assignees
                    set status = 'Quá hạn'
                    where taskId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            int rs = statement.executeUpdate();
            if (rs == 0) {
                return false;
            }

            sql = """
                    update tasks
                    set status = 'Quá hạn'
                    where id = ?
                    """;
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            rs = statement.executeUpdate();
            if (rs == 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return true;
    }

    @Override
    public TaskAssignedByMeResponse createTask(TaskAssignedByMeResponse task) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    insert into tasks (clubId, name, description, status, dueDate, assignedBy)
                    values (?, ?, ?, ?, ?, ?)
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, task.getClubId());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getStatus());
            statement.setTimestamp(5, task.getDueDate());
            statement.setInt(6, task.getAssignedBy());
            int rs = statement.executeUpdate();
            if (rs == 0) {
                return null;
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getInt(1));
            } else {
                return null;
            }

            ArrayList<Integer> assignedTo = task.getAssignedTo();
            for (Integer assignedMember : assignedTo) {
                sql = """
                        insert into task_assignees (taskId, assignedTo, status, clubId)
                        values (?, ?, N'Đang làm', ?)
                        """;
                statement = db.getConnection().prepareStatement(sql);
                statement.setInt(1, task.getId());
                statement.setInt(2, assignedMember);
                statement.setInt(3, task.getClubId());
                rs = statement.executeUpdate();
                if (rs == 0) {
                    return null;
                }
            }
            return task;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

    @Override
    public TaskAssignedByMeResponse editTask(TaskAssignedByMeResponse task) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = "";

            ArrayList<Integer> assignedMembers = getAssignedMembers(task.getId());
            ArrayList<Integer> assignedTo = task.getAssignedTo();

            // Xóa những người không còn trong danh sách assignedTo
            for (Integer assignedMember : assignedMembers) {
                if (!assignedTo.contains(assignedMember)) {
                    sql = """
                            delete from task_assignees
                            where taskId = ? and assignedTo = ?
                            """;
                    PreparedStatement statement = db.getConnection().prepareStatement(sql);
                    statement.setInt(1, task.getId());
                    statement.setInt(2, assignedMember);
                    int rs = statement.executeUpdate();
                    if (rs == 0) {
                        return null;
                    }
                }
            }

            // Thêm những người mới vào danh sách assignedTo
            for (Integer assignedMember : assignedTo) {
                if (!assignedMembers.contains(assignedMember)) {
                    sql = """
                            insert into task_assignees (taskId, assignedTo, status, clubId)
                            values (?, ?, N'Đang làm', ?)
                            """;
                    PreparedStatement statement = db.getConnection().prepareStatement(sql);
                    statement.setInt(1, task.getId());
                    statement.setInt(2, assignedMember);
                    statement.setInt(3, task.getClubId());
                    int rs = statement.executeUpdate();
                    if (rs == 0) {
                        return null;
                    }
                }
            }

            sql = """
                    update tasks
                    set name = ?, description = ?, status = ?, dueDate = ?
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setString(3, task.getStatus());
            statement.setTimestamp(4, task.getDueDate());
            statement.setInt(5, task.getId());
            int rs = statement.executeUpdate();
            if (rs == 0) {
                return null;
            }

            return task;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return null;
    }

    @Override
    public boolean deleteTask(int taskId) {
        DBContext db = DBContext.getInstance();
        try {
            String sql = """
                    delete from task_assignees
                    where taskId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            int rs = statement.executeUpdate();
            if (rs == 0) {
                return false;
            }

            sql = """
                    delete from tasks
                    where id = ?
                    """;
            statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            rs = statement.executeUpdate();
            if (rs == 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }

        return false;
    }

}
