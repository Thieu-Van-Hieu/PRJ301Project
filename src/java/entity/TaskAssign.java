package entity;

public class TaskAssign {
    private int id;
    private int assignedTo;
    private int taskId;
    private String status;

    public TaskAssign() {
    }

    public TaskAssign(Builder builder) {
        this.id = builder.id;
        this.assignedTo = builder.assignedTo;
        this.taskId = builder.taskId;
        this.status = builder.status;
    }

    public int getId() {
        return id;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {

        private int id;
        private int assignedTo;
        private int taskId;
        private String status;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setAssignedTo(int assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public Builder setTaskId(int taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TaskAssign build() {
            return new TaskAssign(this);
        }
    }
}
