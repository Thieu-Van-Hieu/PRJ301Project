package entity;

import java.sql.Timestamp;
import java.util.Calendar;

public class Event {

    private int id;
    private String name;
    private String description;
    private int typeId;
    private Timestamp startDate;
    private Timestamp endDate;
    private int userId;
    private int clubId;
    private int locationId;
    private String img;

    public Event() {
    }

    public Event(int id, String name, String description, int typeId, Timestamp startDate, Timestamp endDate, int userId, int clubId, int locationId, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeId = typeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.clubId = clubId;
        this.locationId = locationId;
        this.img = img;
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

    public int getTypeId() {
        return typeId;
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

    public int getUserId() {
        return userId;
    }

    public int getClubId() {
        return clubId;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getImg() {
        return img;
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

        public Builder setType(int typeID) {
            event.typeId = typeID;
            return this;
        }

        public Builder setUserId(int userId) {
            event.userId = userId;
            return this;
        }

        public Builder setClubId(int clubId) {
            event.clubId = clubId;
            return this;
        }

        public Builder setLocationId(int locationId) {
            event.locationId = locationId;
            return this;
        }

        public Builder setImg(String img) {
            event.img = img;
            return this;
        }

        public Event build() {
            return event;
        }
    }
}
