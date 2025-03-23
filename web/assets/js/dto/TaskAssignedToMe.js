class TaskAssignedToMe {
    constructor(
        taskId,
        taskName,
        taskDescription,
        taskAssignedBy,
        taskAssignedById,
        taskAssignedTo,
        taskStatus,
        taskDueDate,
        clubId
    ) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskAssignedBy = taskAssignedBy;
        this.taskAssignedById = taskAssignedById;
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

    getTaskAssignedById() {
        return this.taskAssignedById;
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
