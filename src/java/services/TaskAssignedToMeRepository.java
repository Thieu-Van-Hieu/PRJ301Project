package services;

import java.util.ArrayList;
import dto.TaskAssignedToMeResponse;

public interface TaskAssignedToMeRepository {
    public ArrayList<TaskAssignedToMeResponse> getTasksAssignedToMe(int clubId, int memberId);

    public TaskAssignedToMeResponse getTaskAssignedToMe(int taskId);

    public TaskAssignedToMeResponse updateStatusTask(TaskAssignedToMeResponse task, int memberId);
}
