class TaskAssignedByMe {
    constructor(taskId, clubId, assignedBy, assignedTo, status) {
        this.taskId = taskId;
        this.clubId = clubId;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = status;
    }

    getTaskId() {
        return this.taskId;
    }

    getClubId() {
        return this.clubId;
    }

    getAssignedBy() {
        return this.assignedBy;
    }

    getAssignedTo() {
        return this.assignedTo;
    }

    getStatus() {
        return this.status;
    }
}
