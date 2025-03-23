package repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.CalendarResponse;
import repository.CalendarRepository;
import util.DBContext;

public class CalendarRepositoryImpl implements CalendarRepository {

    @Override
    public ArrayList<CalendarResponse> getAllCalendars(int clubId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select *
                    from events
                    where clubId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            ResultSet rs = statement.executeQuery();
            ArrayList<CalendarResponse> calendars = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp startDate = rs.getTimestamp("startTimeStamp");
                Timestamp endDate = rs.getTimestamp("endTimeStamp");
                CalendarResponse calendar = new CalendarResponse(id, name, startDate, endDate);
                calendars.add(calendar);
            }
            return calendars;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public CalendarResponse getCalendarById(int id) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select id, name, startTimeStamp, endTimeStamp
                    from events
                    where id = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Timestamp startDate = rs.getTimestamp("startTimeStamp");
                Timestamp endDate = rs.getTimestamp("endTimeStamp");
                CalendarResponse calendar = new CalendarResponse();
                calendar.setId(id);
                calendar.setName(name);
                calendar.setStartDate(startDate);
                calendar.setEndDate(endDate);
                return calendar;
            }
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<CalendarResponse> getCalendarByPeriod(int clubId, Timestamp startDate,
            Timestamp endDate) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select *
                    from events
                    where clubId = ? and startTimeStamp >= ? and endTimeStamp <= ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setTimestamp(2, startDate);
            statement.setTimestamp(3, endDate);
            ResultSet rs = statement.executeQuery();
            ArrayList<CalendarResponse> calendars = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp start = rs.getTimestamp("startTimeStamp");
                Timestamp end = rs.getTimestamp("endTimeStamp");
                CalendarResponse calendar = new CalendarResponse(id, name, start, end);
                calendars.add(calendar);
            }
            return calendars;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

}
