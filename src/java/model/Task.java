package model;

import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private String assignedBy;
    private String assignedTo;
    private String status;
    private Date dueDate;

    public Task() {
    }

    public Task(int id, String name, String description, String assignedBy, String assignedTo, String status,
            Date dueDate) {
        this.id = id;
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

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return this.status.equals("In Progress") && this.dueDate.before(Date.valueOf(LocalDate.now()));
    }
}
