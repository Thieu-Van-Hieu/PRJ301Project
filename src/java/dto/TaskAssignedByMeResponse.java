package dto;

import java.util.ArrayList;
import java.sql.Timestamp;
import entity.Member;

public class TaskAssignedByMeResponse {
    private int id;
    private String name;
    private String description;
    private ArrayList<Integer> assignedMembersId;
    private String status;
    private Timestamp dueDate;

    public TaskAssignedByMeResponse() {
    }

    public TaskAssignedByMeResponse(int id, String name, String description, ArrayList<Integer> assignedMembersId,
            String status, Timestamp dueDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedMembersId = assignedMembersId;
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

    public ArrayList<Integer> getAssignedMembers() {
        return assignedMembersId;
    }

    public void setAssignedMembers(ArrayList<Integer> assignedMembersId) {
        this.assignedMembersId = assignedMembersId;
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

    public boolean isMemberInAssignedMembers(int memberId) {
        for (Integer assignedMember : assignedMembersId) {
            if (assignedMember == memberId) {
                return true;
            }
        }
        return false;
    }
}
