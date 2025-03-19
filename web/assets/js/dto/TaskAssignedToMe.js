class TaskAssignedToMe {
    constructor(
        taskId,
        taskName,
        taskDescription,
        taskAssignedBy,
        taskAssignedTo,
        taskStatus,
        taskDueDate,
        clubId
    ) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskAssignedBy = taskAssignedBy;
        this.taskAssignedTo = taskAssignedTo;
        this.taskStatus = taskStatus;
        this.taskDueDate = taskDueDate;
        this.clubId = clubId;
    }

    getTaskId() {
        return this.taskId;
    }

    getTaskName() {
        return this.taskName;
    }

    getTaskDescription() {
        return this.taskDescription;
    }

    getTaskStatus() {
        return this.taskStatus;
    }

    getTaskAssignedBy() {
        return this.taskAssignedBy;
    }

    getTaskAssignedTo() {
        return this.taskAssignedTo;
    }

    getTaskDueDate() {
        return this.taskDueDate;
    }

    getClubId() {
        return this.clubId;
    }
}
