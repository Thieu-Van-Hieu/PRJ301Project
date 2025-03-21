package entity;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Task {
    private int id;
    private int clubId;
    private String name;
    private String description;
    private int assignedBy;
    private String status;
    private Timestamp dueDate;

    public Task() {
    }

    public Task(Builder builder) {
        this.id = builder.id;
        this.clubId = builder.clubId;
        this.name = builder.name;
        this.description = builder.description;
        this.assignedBy = builder.assignedBy;
        this.status = builder.status;
        this.dueDate = builder.dueDate;
    }

    public int getId() {
        return id;
    }

    public int getClubId() {
        return clubId;
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

    public static class Builder {
        private int id;
        private int clubId;
        private String name;
        private String description;
        private int assignedBy;
        private String status;
        private Timestamp dueDate;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setAssignedBy(int assignedBy) {
            this.assignedBy = assignedBy;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setDueDate(Timestamp dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
