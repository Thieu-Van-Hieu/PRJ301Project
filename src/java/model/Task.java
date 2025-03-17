package model;

import java.lang.reflect.Member;
import java.sql.Date;
import java.time.LocalDate;

public class Task {
    private int id;
    private String name;
    private String description;
    private Member assignedBy;
    private Member assignedTo;
    private String status;
    private Date dueDate;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public Task setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAssignedBy() {
        return assignedBy.getName();
    }

    public Task setAssignedBy(String assignedBy) {
        this.assignedBy.setName(assignedBy);
        return this;
    }

    public String getAssignedTo() {
        return assignedTo.getName();
    }

    public Task setAssignedTo(String assignedTo) {
        this.assignedTo.setName(assignedTo);
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Task setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Task setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public boolean isOverdue() {
        return this.status.equals("In Progress") && this.dueDate.before(Date.valueOf(LocalDate.now()));
    }
}
