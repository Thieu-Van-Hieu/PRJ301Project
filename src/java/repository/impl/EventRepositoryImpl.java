/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.EventResponse;
import dto.SearchEventDTO;
import java.sql.Timestamp;
import repository.EventRepository;
import java.util.*;
import java.sql.*;
import util.DBContext;
import entity.Event;

/**
 *
 * @author hunggt1572004
 */
public class EventRepositoryImpl implements EventRepository {

    @Override
    public void addEvent(int clubId, int userId, String name, String description, int typeId, Timestamp startDate,
            Timestamp endDate, int locationId, String img) {
        DBContext db = DBContext.getInstance();
        int rs = 0;
        try {
            String sql = """
                    insert into events (clubId, userId, name, description, typeId, startTimestamp, endTimestamp, locationId, img)
                    values (?, ?, ?, ?, ?, ?, ? , ?, ?)
                    """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, userId);
            statement.setNString(3, name);
            statement.setNString(4, description);
            statement.setInt(5, typeId);
            statement.setTimestamp(6, startDate);
            statement.setTimestamp(7, endDate);
            statement.setInt(8, locationId);
            statement.setString(9, img);
            rs = statement.executeUpdate();

            if (rs == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<EventResponse> getEventDescription() {
        DBContext db = DBContext.getInstance();
        ArrayList<EventResponse> eventList = new ArrayList<>();

        try {
            String sql = """
                    SELECT e.id, e.name, c.name AS clubName, c.id as clubId,
                           e.startTimestamp, e.endTimestamp, e.img
                    FROM events e
                    JOIN clubs c ON e.clubId = c.id
                    order by e.id desc
                    """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("id");
                String eventName = rs.getNString("name");
                int clubId = rs.getInt("clubId");
                String clubName = rs.getNString("clubName");
                Timestamp startDate = rs.getTimestamp("startTimestamp");
                Timestamp endDate = rs.getTimestamp("endTimestamp");
                String img = rs.getString("img");

                EventResponse event = new EventResponse(eventId, eventName, clubId, clubName, startDate, endDate, img);
                eventList.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

    @Override
    public ArrayList<Event> searchEvent(SearchEventDTO event) {
        DBContext db = DBContext.getInstance();
        ArrayList<Event> result = new ArrayList<>();
        try {
            String sql = """
                    select e.*, c.name as [clubName] from events e
                    """;
            sql += joinSearchEvent(event);
            sql += whereSearchEvent(event);
            PreparedStatement st = db.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event searchEntity = new Event.Builder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setClubName(rs.getString("clubName"))
                        .setDescription(rs.getString("description"))
                        .setTypeId(rs.getInt("typeId"))
                        .setStartDate(rs.getTimestamp("startTimestamp"))
                        .setEndDate(rs.getTimestamp("endTimestamp"))
                        .setUserId(rs.getInt("userId"))
                        .setClubId(rs.getInt("clubId"))
                        .setLocationId(rs.getInt("locationId"))
                        .setImg(rs.getString("img"))
                        .build();
                result.add(searchEntity);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private String joinSearchEvent(SearchEventDTO event) {
        StringBuilder sql = new StringBuilder();
        sql.append(" JOIN clubs  c ON c.id = e.clubId ");
        return sql.toString();
    }

    private String whereSearchEvent(SearchEventDTO event) {
        StringBuilder sql = new StringBuilder(" Where 1 = 1 ");

        if (event.getSearch() != null && !event.getSearch().isEmpty()) {
            sql.append(" and e.[name] like '%").append(event.getSearch()).append("%' ");
        }
        if (event.getDate() != null && !event.getDate().isEmpty()) {
            sql.append(" and CAST(startTimestamp AS DATE) = '").append(event.getDate()).append("' ");
        }
        if (event.getType() != null && !event.getType().isEmpty()) {
            sql.append(" and typeId = '").append(event.getType()).append("' ");
        }
        if ((Integer) event.getClubId() != null) {
            sql.append(" and clubId = ").append(event.getClubId());
        }
        return sql.toString();
    }

    @Override
    public boolean deleteEvent(int eventId) {
        DBContext db = DBContext.getInstance();
        try {
            // Delete corresponding rows in event_participants first
            String deleteParticipantsSql = """
                    delete from event_participants where eventId = ?
                    """;
            PreparedStatement deleteParticipantsStmt = db.getConnection().prepareStatement(deleteParticipantsSql);
            deleteParticipantsStmt.setInt(1, eventId);
            deleteParticipantsStmt.executeUpdate();

            // Delete the event itself
            String deleteEventSql = """
                    delete from events where id = ?
                    """;
            PreparedStatement deleteEventStmt = db.getConnection().prepareStatement(deleteEventSql);
            deleteEventStmt.setInt(1, eventId);
            int rs = deleteEventStmt.executeUpdate();

            if (rs == 0) {
                throw new Exception();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
