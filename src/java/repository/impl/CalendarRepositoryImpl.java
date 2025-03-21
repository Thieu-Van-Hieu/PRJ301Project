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
    public ArrayList<CalendarResponse> getAllCalendars(int clubId, int memberId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select e.id, e.name, e.startTimeStamp, e.endTimeStamp, ep.status, ep.memberId
                    from events as e
                    join event_participants as ep on e.id = ep.eventId
                    where clubId = ? and memberId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, memberId);
            ResultSet rs = statement.executeQuery();
            ArrayList<CalendarResponse> calendars = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp startDate = rs.getTimestamp("startTimeStamp");
                Timestamp endDate = rs.getTimestamp("endTimeStamp");
                String status = rs.getString("status");
                CalendarResponse calendar = new CalendarResponse(id, name, startDate, endDate, status);
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
    public ArrayList<CalendarResponse> getCalendarByPeriod(int clubId, int memberId, Timestamp startDate,
            Timestamp endDate) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    select e.id, e.name, e.startTimeStamp, e.endTimeStamp, ep.status, ep.memberId
                    from events as e
                    join event_participants as ep on e.id = ep.eventId
                    where clubId = ? and memberId = ? and e.startTimeStamp >= ? and e.endTimeStamp <= ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, clubId);
            statement.setInt(2, memberId);
            statement.setTimestamp(3, startDate);
            statement.setTimestamp(4, endDate);
            ResultSet rs = statement.executeQuery();
            ArrayList<CalendarResponse> calendars = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Timestamp start = rs.getTimestamp("startTimeStamp");
                Timestamp end = rs.getTimestamp("endTimeStamp");
                String status = rs.getString("status");
                CalendarResponse calendar = new CalendarResponse(id, name, start, end, status);
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
    public boolean setStatusCalendar(int id, int status, int clubId, int memberId) {
        DBContext db = new DBContext();
        try {
            String sql = """
                    update event_participants
                    set status = ?
                    where eventId = ? and clubId = ? and memberId = ?
                    """;
            PreparedStatement statement = db.getConnection().prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, id);
            statement.setInt(3, clubId);
            statement.setInt(4, memberId);
            int result = statement.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            if (db.getConnection() == null) {
                System.out.println("Connection is null");
            } else {
                e.printStackTrace();
            }
        }
        return false;
    }

}
