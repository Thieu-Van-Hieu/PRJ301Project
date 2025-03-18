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

    public Event(EventBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
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

    public static class EventBuilder {
        private int id;
        private String name;
        private String description;
        private Timestamp startDate;
        private Timestamp endDate;

        public EventBuilder() {
        }

        public EventBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public EventBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public EventBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public EventBuilder setStartDate(Timestamp startDate) {
            this.startDate = startDate;
            return this;
        }

        public EventBuilder setEndDate(Timestamp endDate) {
            this.endDate = endDate;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}