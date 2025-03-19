package dto;

import java.sql.Timestamp;
import entity.Member;

public class TaskAssignedToMeResponse {
    private int id;
    private String name;
    private String description;
    private Member assignedByMember;
    private String status;
    private Timestamp dueDate;

    public TaskAssignedToMeResponse() {
    }

    public TaskAssignedToMeResponse(int id, String name, String description, Member assignedByMember, String status,
            Timestamp dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedByMember = assignedByMember;
        this.status = status;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getAssignedByMember() {
        return assignedByMember;
    }

    public void setAssignedByMember(Member assignedByMember) {
        this.assignedByMember = assignedByMember;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }
}
