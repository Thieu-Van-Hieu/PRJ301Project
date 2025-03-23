package entity;

import java.sql.Timestamp;
import java.util.Calendar;

public class Event {

    private int id;
    private String name;
    private String clubName;
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

    public String getClubName() {
        return clubName;
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

    public Event(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.typeId = builder.typeId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.userId = builder.userId;
        this.clubId = builder.clubId;
        this.locationId = builder.locationId;
        this.img = builder.img;
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

    public static class Builder {

        private int id;
        private String name;
        private String clubName;
        private String description;
        private int typeId;
        private Timestamp startDate;
        private Timestamp endDate;
        private int userId;
        private int clubId;
        private int locationId;
        private String img;

        public Builder() {
        }

        public Builder setClubName(String clubName) {
            this.clubName = clubName;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setTypeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        public Builder setStartDate(Timestamp startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Timestamp endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setClubId(int clubId) {
            this.clubId = clubId;
            return this;
        }

        public Builder setLocationId(int locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder setImg(String img) {
            this.img = img;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
