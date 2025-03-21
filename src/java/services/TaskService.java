package services;

import java.util.ArrayList;

import repository.MemberTaskRepository;
import repository.TaskAssignedByMeRepository;
import factory.TaskFactory;
import dto.MemberTaskResponse;
import dto.TaskAssignedByMeResponse;
import dto.TaskAssignedToMeResponse;
import entity.Member;

public class TaskService {
    private final TaskAssignedByMeRepository taskAssignedByMeRepository;
    private final TaskAssignedToMeRepository taskAssignedToMeRepository;
    private final MemberTaskRepository memberTaskRepository;

    public TaskService() {
        this.taskAssignedByMeRepository = TaskFactory.getTaskAssignedByMeRepository();
        this.taskAssignedToMeRepository = TaskFactory.getTaskAssignedToMeRepository();
        this.memberTaskRepository = TaskFactory.getMemberTaskRepository();
    }

    public ArrayList<TaskAssignedByMeResponse> getTasksAssignedByMe(int clubId, int memberId) {
        ArrayList<TaskAssignedByMeResponse> tasks = taskAssignedByMeRepository.getTasksAssignedByMe(clubId, memberId);
        if (tasks == null) {
            return null;
        }
        for (TaskAssignedByMeResponse task : tasks) {
            if (isTaskOverdue(task)) {
                task.setStatus("Quá hạn");
            } else if (taskAssignedByMeRepository.isTaskCompleted(task.getId())) {
                task.setStatus("Đã xong");
            }
        }
        return tasks;
    }

    public boolean setTaskUncompleted(int taskId) {
        return taskAssignedByMeRepository.setTaskUncompleted(taskId);
    }

    public boolean isMemberInvalid(ArrayList<Integer> memberIds, ArrayList<MemberTaskResponse> validMembers) {
        ArrayList<Integer> validMemberIds = new ArrayList<>();
        for (MemberTaskResponse member : validMembers) {
            validMemberIds.add(member.getId());
        }
        for (Integer memberId : memberIds) {
            if (!validMemberIds.contains(memberId)) {
                return true;
            }
        }
        return false;
    }

    public TaskAssignedByMeResponse createTask(TaskAssignedByMeResponse task) {
        Member member = new Member.Builder().setId(task.getAssignedBy()).setClubId(task.getClubId()).build();
        if (isMemberInvalid(task.getAssignedTo(), memberTaskRepository.getMembers(member))) {
            return null;
        }

        if (isTaskOverdue(task)) {
            task.setStatus("Quá hạn");
        }
        return taskAssignedByMeRepository.createTask(task);
    }

    private boolean isTaskOverdue(TaskAssignedByMeResponse task) {
        if (task.getDueDate() == null) {
            return false;
        }
        return task.getDueDate().getTime() < System.currentTimeMillis();
    }

    private boolean isTaskOverdue(TaskAssignedToMeResponse task) {
        if (task.getDueDate() == null) {
            return false;
        }
        return task.getDueDate().getTime() < System.currentTimeMillis();
    }

    public boolean isTaskCompleted(int taskId) {
        return taskAssignedByMeRepository.isTaskCompleted(taskId);
    }

    public TaskAssignedByMeResponse editTask(TaskAssignedByMeResponse task) {
        if (isTaskOverdue(task)) {
            task.setStatus("Quá hạn");
        } else if (taskAssignedByMeRepository.isTaskCompleted(task.getId())) {
            task.setStatus("Đã xong");
        }
        return taskAssignedByMeRepository.editTask(task);
    }

    public boolean deleteTask(int taskId) {
        return taskAssignedByMeRepository.deleteTask(taskId);
    }

    public ArrayList<TaskAssignedToMeResponse> getTasksAssignedToMe(int clubId, int memberId) {
        return taskAssignedToMeRepository.getTasksAssignedToMe(clubId, memberId);
    }

    public TaskAssignedToMeResponse updateStatusTask(TaskAssignedToMeResponse task, int memberId) {
        if (isTaskOverdue(task)) {
            task.setStatus("Quá hạn");
        }

        return taskAssignedToMeRepository.updateStatusTask(task, memberId);
    }

    public ArrayList<MemberTaskResponse> getMembers(Member member) {
        return memberTaskRepository.getMembers(member);
    }

    public TaskAssignedToMeResponse getTaskAssignedToMe(int taskId) {
        return taskAssignedToMeRepository.getTaskAssignedToMe(taskId);
    }
}
