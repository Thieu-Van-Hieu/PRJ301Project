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

    public TaskAssignedByMeResponse(int id, String name, String description, ArrayList<Member> assignedMembers,
            String status, String dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedMembers = assignedMembers;
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

    public ArrayList<Member> getAssignedMembers() {
        return assignedMembers;
    }

    public void setAssignedMembers(ArrayList<Member> assignedMembers) {
        this.assignedMembers = assignedMembers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
