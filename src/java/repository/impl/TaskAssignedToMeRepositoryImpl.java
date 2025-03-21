package repository.impl;

import dto.TaskAssignedToMeResponse;
import java.util.ArrayList;
import repository.TaskAssignedToMeRepository;
import util.DBContext;
import java.sql.*;

public class TaskAssignedToMeRepositoryImpl implements TaskAssignedToMeRepository {

    @Override
    public ArrayList<TaskAssignedToMeResponse> getTasksAssignedToMe(int clubId, int memberId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select t.id, t.clubId, t.name, t.description, ta.status, t.dueDate, ui.firstName
                    from tasks as t
                    join task_assignees as ta on t.id = ta.taskId
                    join members as m on ta.assignedTo = m.id
                    join user_informations as ui on m.id = ui.userId
                    where t.clubId = ? and ta.assignedTo = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, memberId);
            ResultSet rs = statement.executeQuery();
            ArrayList<TaskAssignedToMeResponse> tasks = new ArrayList<>();
            while (rs.next()) {
                TaskAssignedToMeResponse task = new TaskAssignedToMeResponse();
                task.setId(rs.getInt("id"));
                task.setClubId(rs.getInt("clubId"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setDueDate(rs.getTimestamp("dueDate"));
                task.setAssignedByMemberName((rs.getString("firstName")));
                tasks.add(task);
            }
            return tasks;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
            return null;
        }
    }

    @Override
    public TaskAssignedToMeResponse getTaskAssignedToMe(int taskId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select t.id, t.clubId, t.name, t.description, ta.status, t.dueDate, ui.firstName
                    from tasks as t
                    join task_assignees as ta on t.id = ta.taskId
                    join members as m on ta.assignedTo = m.id
                    join user_informations as ui on m.id = ui.userId
                    where t.id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                TaskAssignedToMeResponse task = new TaskAssignedToMeResponse();
                task.setId(rs.getInt("id"));
                task.setClubId(rs.getInt("clubId"));
                task.setName(rs.getString("name"));
                task.setDescription(rs.getString("description"));
                task.setStatus(rs.getString("status"));
                task.setDueDate(rs.getTimestamp("dueDate"));
                task.setAssignedByMemberName((rs.getString("firstName")));
                return task;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }
        return null;
    }

    @Override
    public TaskAssignedToMeResponse updateStatusTask(TaskAssignedToMeResponse task, int memberId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    update task_assignees
                    set status = ?
                    where taskId = ? and assignedTo = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setString(1, task.getStatus());
            statement.setInt(2, task.getId());
            statement.setInt(3, memberId);
            int rs = statement.executeUpdate();
            if (rs > 0) {
                return task;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            }
        }
        return null;
    }

}
