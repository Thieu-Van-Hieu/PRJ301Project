package dto;

import java.util.ArrayList;
import java.sql.Timestamp;

public class TaskAssignedByMeResponse {
    private int id;
    private int clubId;
    private String name;
    private String description;
    private int assignedBy;
    private ArrayList<Integer> assignedTo;
    private String status;
    private Timestamp dueDate;

    public TaskAssignedByMeResponse() {
    }

    public TaskAssignedByMeResponse(int id, int clubId, String name, String description, int assignedBy,
            ArrayList<Integer> assignedTo,
            String status, Timestamp dueDate) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
        this.description = description;
        this.assignedBy = assignedBy;
        this.assignedTo = assignedTo;
        this.status = status;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
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

    public int getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(int assignedBy) {
        this.assignedBy = assignedBy;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Integer> getAssignedTo() {
        return assignedTo;
    }

    public void addAssignedMember(int memberId) {
        assignedTo.add(memberId);
    }

    public void setAssignedTo(ArrayList<Integer> assignedTo) {
        this.assignedTo = assignedTo;
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
        for (Integer assignedMember : assignedTo) {
            if (assignedMember == memberId) {
                return true;
            }
        }
        return false;
    }

}
