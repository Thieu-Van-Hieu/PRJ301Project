package entity;

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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Calendar getStartDateCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        return cal;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Calendar getEndDateCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        return cal;
    }

    public int getNumberOfSlots() {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        return end.get(Calendar.HOUR_OF_DAY) - start.get(Calendar.HOUR_OF_DAY);
    }

    public static class Builder {
        private Event event;

        public Builder() {
            event = new Event();
        }

        public Builder setId(int id) {
            event.id = id;
            return this;
        }

        public Builder setName(String name) {
            event.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            event.description = description;
            return this;
        }

        public Builder setStartDate(Timestamp startDate) {
            event.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Timestamp endDate) {
            event.endDate = endDate;
            return this;
        }

        public Event build() {
            return event;
        }
    }
}