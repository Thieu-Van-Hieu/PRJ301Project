package model;

import java.sql.Timestamp;
import java.util.Calendar;

public class Event {
    private int id;
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;

    public Event() {
    }

    public int getId() {
        return id;
    }

    public Event setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Calendar getStartDateCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        return cal;
    }

    public Event setStartDate(Timestamp startDate) {
        this.startDate = startDate;
        return this;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Calendar getEndDateCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        return cal;
    }

    public Event setEndDate(Timestamp endDate) {
        this.endDate = endDate;
        return this;
    }

    public int getNumberOfSlots() {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        return end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
    }
}