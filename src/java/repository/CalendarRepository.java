package repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import dto.CalendarResponse;

public interface CalendarRepository {
    public ArrayList<CalendarResponse> getAllCalendars(int clubId);

    public ArrayList<CalendarResponse> getCalendarByPeriod(int clubId, Timestamp startDate,
            Timestamp endDate);

    public CalendarResponse getCalendarById(int id);
}
