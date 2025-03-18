package entity;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private int assignedBy;
    private int assignedTo;
    private String status;
    private Date dueDate;

    public Task() {
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

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isOverdue() {
        return this.status.equals("In Progress") && this.dueDate.before(Date.valueOf(LocalDate.now()));
    }

    public static class Builder {
        private Task task;

        public Builder() {
            task = new Task();
        }

        public Builder setId(int id) {
            task.id = id;
            return this;
        }

        public Builder setName(String name) {
            task.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            task.description = description;
            return this;
        }

        public Builder setAssignedBy(int assignedBy) {
            task.assignedBy = assignedBy;
            return this;
        }

        public Builder setAssignedTo(int assignedTo) {
            task.assignedTo = assignedTo;
            return this;
        }

        public Builder setStatus(String status) {
            task.status = status;
            return this;
        }

        public Builder setDueDate(Date dueDate) {
            task.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return task;
        }
    }
}
