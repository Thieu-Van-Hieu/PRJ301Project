/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Timestamp;

/**
 *
 * @author hunggt1572004
 */
public class EventResponse {

    private int eventId;
    private String eventName;
    private int clubId;
    private String clubName;
    private Timestamp startDate;
    private Timestamp endDate;
    private String img;

    public EventResponse(int eventId, String eventName, String clubName, Timestamp startDate,
            Timestamp endDate, String img) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.clubName = clubName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.img = img;
    }

    public EventResponse(int eventId, String eventName, int clubId, String clubName, Timestamp startDate,
            Timestamp endDate, String img) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.clubId = clubId;
        this.clubName = clubName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.img = img;
    }

    public EventResponse() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
