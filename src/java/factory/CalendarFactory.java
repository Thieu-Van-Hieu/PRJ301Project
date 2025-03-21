package factory;

import repository.CalendarRepository;
import repository.impl.CalendarRepositoryImpl;

public class CalendarFactory {
    public final static CalendarRepository calendarRepository = new CalendarRepositoryImpl();
    
    public static CalendarRepository getCalendarRepository() {
        return calendarRepository;
    }
}
