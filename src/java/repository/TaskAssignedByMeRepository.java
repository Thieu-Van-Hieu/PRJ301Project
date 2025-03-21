package repository;

import java.util.ArrayList;
import dto.TaskAssignedByMeResponse;

public interface TaskAssignedByMeRepository {
    public ArrayList<TaskAssignedByMeResponse> getTasksAssignedByMe(int clubId, int memberId);

    public boolean isTaskCompleted(int taskId);

    public boolean setTaskUncompleted(int taskId);

    public TaskAssignedByMeResponse createTask(TaskAssignedByMeResponse task);

    public TaskAssignedByMeResponse editTask(TaskAssignedByMeResponse task);

    public boolean deleteTask(int taskId);
}
