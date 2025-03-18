package entity;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private int assignedBy;
    private int assignedTo;
    private String status;
    private Timestamp dueDate;

    public Task() {
    }

    public Task(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.assignedBy = builder.assignedBy;
        this.assignedTo = builder.assignedTo;
        this.status = builder.status;
        this.dueDate = builder.dueDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAssignedBy() {
        return assignedBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public boolean isOverdue() {
        return this.status.equals("In Progress")
                && this.dueDate.before(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
    }

    public static class TaskBuilder {
        private int id;
        private String name;
        private String description;
        private int assignedBy;
        private int assignedTo;
        private String status;
        private Timestamp dueDate;

        public TaskBuilder() {
        }

        public TaskBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setAssignedBy(int assignedBy) {
            this.assignedBy = assignedBy;
            return this;
        }

        public TaskBuilder setAssignedTo(int assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public TaskBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public TaskBuilder setDueDate(Timestamp dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
