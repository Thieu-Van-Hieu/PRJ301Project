package dto;

import java.util.ArrayList;
import entity.Member;

public class TaskAssignedByMeResponse {
    private int id;
    private String name;
    private String description;
    private ArrayList<Member> assignedMembers;
    private String status;
    private String dueDate;

    public TaskAssignedByMeResponse() {
    }

    public TaskAssignedByMeResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.assignedMembers = builder.assignedMembers;
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

    public ArrayList<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public String getStatus() {
        return status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;
        private ArrayList<Member> assignedMembers;
        private String status;
        private String dueDate;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
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

        public Builder setAssignedMembers(ArrayList<Member> assignedMembers) {
            this.assignedMembers = assignedMembers;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskAssignedByMeResponse build() {
            return new TaskAssignedByMeResponse(this);
        }
    }
}
