package factory;

import repository.MemberTaskRepository;
import repository.TaskAssignedByMeRepository;
import repository.impl.MemberTaskRepositoryImpl;
import repository.impl.TaskAssignedByMeRepositoryImpl;
import repository.impl.TaskAssignedToMeRepositoryImpl;
import services.TaskAssignedToMeRepository;

public class TaskFactory {
    private final static TaskAssignedToMeRepository taskAssignedToMeRepository = new TaskAssignedToMeRepositoryImpl();
    private final static TaskAssignedByMeRepository taskAssignedBymeRepository = new TaskAssignedByMeRepositoryImpl();
    private final static MemberTaskRepository memberTaskRepository = new MemberTaskRepositoryImpl();

    public static TaskAssignedByMeRepository getTaskAssignedByMeRepository() {
        return taskAssignedBymeRepository;
    }

    public static TaskAssignedToMeRepository getTaskAssignedToMeRepository() {
        return taskAssignedToMeRepository;
    }

    public static MemberTaskRepository getMemberTaskRepository() {
        return memberTaskRepository;
    }
}
