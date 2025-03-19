/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import dto.EventResponse;
import java.sql.Timestamp;
import repository.EventRepository;
import java.util.*;
import java.sql.*;
import util.DBContext;

/**
 *
 * @author hunggt1572004
 */
public class EventRepositoryImpl implements EventRepository {

    @Override
    public void addEvent(int clubId, int userId, String name, String description, int typeId, Timestamp startDate, Timestamp endDate, int locationId, String img) {
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
                     SELECT e.id, e.name, c.name AS clubName, 
                            e.startTimestamp, e.endTimestamp, e.img 
                     FROM events e
                     JOIN clubs c ON e.clubId = c.id
                     """;

            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int eventId = rs.getInt("id");
                String eventName = rs.getNString("name");
                String clubName = rs.getNString("clubName");
                Timestamp startDate = rs.getTimestamp("startTimestamp");
                Timestamp endDate = rs.getTimestamp("endTimestamp");
                String img = rs.getString("img");

                EventResponse event = new EventResponse(eventId, eventName, clubName, startDate, endDate, img);
                eventList.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventList;
    }

}
