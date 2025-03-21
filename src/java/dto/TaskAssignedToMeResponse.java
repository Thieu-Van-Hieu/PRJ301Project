package dto;

import java.sql.Timestamp;

public class TaskAssignedToMeResponse {
    private int id;
    private int clubId;
    private String name;
    private String description;
    private int assignedByMemberId; // Thêm trường mới
    private String assignedByMemberName; // Đổi tên trường từ assignedBy
    private String status;
    private Timestamp dueDate;

    public TaskAssignedToMeResponse() {
    }

    public TaskAssignedToMeResponse(int id, int clubId, String name, String description, int assignedByMemberId,
            String assignedByMemberName, String status, Timestamp dueDate) {
        this.id = id;
        this.clubId = clubId;
        this.name = name;
        this.description = description;
        this.assignedByMemberId = assignedByMemberId;
        this.assignedByMemberName = assignedByMemberName;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignedByMemberId() { // Getter cho trường mới
        return assignedByMemberId;
    }

    public void setAssignedByMemberId(int assignedByMemberId) { // Setter cho trường mới
        this.assignedByMemberId = assignedByMemberId;
    }

    public String getAssignedByMemberName() { // Đổi tên getter
        return assignedByMemberName;
    }

    public void setAssignedByMemberName(String assignedByMemberName) { // Đổi tên setter
        this.assignedByMemberName = assignedByMemberName;
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
