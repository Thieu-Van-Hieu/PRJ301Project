package repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import dto.CalendarResponse;

public interface CalendarRepository {
    public ArrayList<CalendarResponse> getAllCalendars(int clubId, int memberId);

    public ArrayList<CalendarResponse> getCalendarByPeriod(int clubId, int memberId, Timestamp startDate,
            Timestamp endDate);

    public CalendarResponse getCalendarById(int id);

    public boolean setStatusCalendar(int id, int status, int clubId, int memberId);
}
