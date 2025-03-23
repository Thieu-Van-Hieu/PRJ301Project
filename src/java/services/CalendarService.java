package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import dto.CalendarResponse;
import repository.CalendarRepository;
import repository.impl.CalendarRepositoryImpl;

public class CalendarService {
    private final CalendarRepository calendarRepository;
    private Calendar calendar;
    private ArrayList<Integer> randomIndexColor;
    private ArrayList<String> colors;
    private ArrayList<CalendarResponse> events;

    private ArrayList<String> colorAreas;
    private ArrayList<CalendarResponse> eventAreas;
    private int reminingTimeSlots;

    private String gridAreas;

    public CalendarService() {
        calendarRepository = new CalendarRepositoryImpl();
        calendar = Calendar.getInstance();

        colors = new ArrayList<>(Arrays.asList(
                "#fbf8cc", // Màu vàng nhạt
                "#fde4cf", // Màu cam nhạt
                "#ffcfd2", // Màu hồng phấn
                "#f1c0e8", // Màu hồng tím
                "#cfbaf0", // Màu tím nhạt
                "#a3c4f3", // Màu xanh dương nhạt
                "#90dbf4", // Màu xanh nước biển nhạt
                "#8eecf5", // Màu xanh cyan nhạt
                "#98f5e1", // Màu xanh bạc hà
                "#b9fbc0" // Màu xanh lá nhạt
        ));

        randomIndexColor = getRandomIndexColor();
        colorAreas = new ArrayList<>();
        eventAreas = new ArrayList<>();
        reminingTimeSlots = 0;
        gridAreas = "";
    }

    public void init() {
        randomIndexColor = getRandomIndexColor();
        colorAreas = new ArrayList<>();
        eventAreas = new ArrayList<>();
        reminingTimeSlots = 0;
        gridAreas = "";
    }

    public void setCurrentWeek() {
        calendar = Calendar.getInstance();
    }

    public void setNextWeek() {
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
    }

    public void setPreviousWeek() {
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
    }

    public void setCalendarResponses(ArrayList<CalendarResponse> events) {
        this.events = events;
        setAttribute();
    }

    public void getCalendarResponses(int clubId) {
        events = calendarRepository.getAllCalendars(clubId);
        setAttribute();
    }

    public ArrayList<CalendarResponse> getCalendarResponses() {
        return events;
    }

    private ArrayList<Integer> getRandomIndexColor() {
        ArrayList<Integer> colorNumber = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            colorNumber.add(i);
        }

        ArrayList<Integer> randomIndexColor = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            int randomIndex = (int) (Math.random() * colorNumber.size());
            randomIndexColor.add(colorNumber.get(randomIndex));
            colorNumber.remove(randomIndex);
        }

        return randomIndexColor;
    }

    public Calendar getMondayCalendar() {
        Calendar startOfWeekCalendar = (Calendar) calendar.clone();
        startOfWeekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        startOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeekCalendar.set(Calendar.MINUTE, 0);
        startOfWeekCalendar.set(Calendar.SECOND, 0);
        startOfWeekCalendar.set(Calendar.MILLISECOND, 0);

        return startOfWeekCalendar;
    }

    public Calendar getSundayCalendar() {
        Calendar endOfWeekCalendar = (Calendar) calendar.clone();
        endOfWeekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        endOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endOfWeekCalendar.set(Calendar.MINUTE, 59);
        endOfWeekCalendar.set(Calendar.SECOND, 59);
        endOfWeekCalendar.set(Calendar.MILLISECOND, 999);

        return endOfWeekCalendar;
    }

    private boolean isEventInWeek(CalendarResponse event) {
        Calendar startOfWeekCalendar = getMondayCalendar();
        Calendar endOfWeekCalendar = getSundayCalendar();

        boolean isStartInWeek = event.getStartDate().after(startOfWeekCalendar.getTime())
                && event.getStartDate().before(endOfWeekCalendar.getTime());

        boolean isEndInWeek = event.getEndDate().after(startOfWeekCalendar.getTime())
                && event.getEndDate().before(endOfWeekCalendar.getTime());

        return isStartInWeek || isEndInWeek;
    }

    private String getGridAreas(String[][] eventSlots) {
        StringBuilder gridAreas = new StringBuilder();
        for (int i = 0; i < eventSlots.length; i++) {
            gridAreas.append("'");
            for (int j = 0; j < eventSlots[i].length; j++) {
                gridAreas.append(eventSlots[i][j]);
                gridAreas.append(" ");
            }

            gridAreas.append("'\n");
        }

        return gridAreas.toString();
    }

    private void setAttribute() {
        // Đặt tuần làm việc bắt đầu từ thứ 2
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        // Lấy ngày đầu tuần và
        Calendar startOfWeekCalendar = getMondayCalendar();
        Calendar endOfWeekCalendar = getSundayCalendar();

        // Lấy chuỗi thể hiện tuần hiện tại
        String week = getWeek();

        // Lấy danh sách các sự kiện trong tuần
        ArrayList<CalendarResponse> eventsInWeek = new ArrayList<>();
        for (CalendarResponse event : events) {
            if (isEventInWeek(event)) {
                eventsInWeek.add(event);
            }
        }

        // Tạo một mảng 2 chiều để lưu thông tin các sự kiện trong tuần
        final int HOURS = 24;
        final int DAYS = 7;
        String eventSlots[][] = new String[HOURS][DAYS];
        for (CalendarResponse event : eventsInWeek) {
            Calendar eventCalendar = Calendar.getInstance();
            eventCalendar.setTime(event.getStartDate());

            int startDayOfWeek = eventCalendar.get(Calendar.DAY_OF_WEEK);
            if (startDayOfWeek == 1) {
                startDayOfWeek = 7;
            } else {
                startDayOfWeek--;
            }
            int startHourOfDay = eventCalendar.get(Calendar.HOUR_OF_DAY);
            if (eventCalendar.before(startOfWeekCalendar)) {
                startDayOfWeek = 1;
                startHourOfDay = 0;
            }

            eventCalendar.setTime(event.getEndDate());
            int endDayOfWeek = eventCalendar.get(Calendar.DAY_OF_WEEK);
            if (endDayOfWeek == 1) {
                endDayOfWeek = 7;
            } else {
                endDayOfWeek--;
            }
            int endHourOfDay = eventCalendar.get(Calendar.HOUR_OF_DAY);

            if (eventCalendar.after(endOfWeekCalendar)) {
                endDayOfWeek = 7;
                endHourOfDay = 24;
            }

            for (int i = startDayOfWeek - 1; i < endDayOfWeek; i++) {
                int startHour = startHourOfDay;
                if (i != startDayOfWeek - 1) {
                    startHour = 0;
                }

                int endHour = endHourOfDay;
                if (i != endDayOfWeek - 1) {
                    endHour = 24;
                }

                boolean hasMoreEventAreas = false;
                for (int j = startHour; j < endHour; j++) {
                    hasMoreEventAreas = true;
                    eventSlots[j][i] = "event-" + eventAreas.size();
                }

                if (hasMoreEventAreas) {
                    colorAreas.add(colors.get(randomIndexColor.get(eventsInWeek.indexOf(event) % colors.size())));
                    eventAreas.add(event);
                }
            }
        }

        // Tính số lượng ô trống còn lại
        for (int i = 0; i < HOURS; i++) {
            for (int j = 0; j < DAYS; j++) {
                if (eventSlots[i][j] == null) {
                    eventSlots[i][j] = ".";
                    reminingTimeSlots++;
                }
            }
        }

        gridAreas = getGridAreas(eventSlots);
    }

    public String getWeek() {
        Calendar startOfWeekCalendar = getMondayCalendar();
        Calendar endOfWeekCalendar = getSundayCalendar();

        String startDate = startOfWeekCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (startOfWeekCalendar.get(Calendar.MONTH) + 1) + "/" +
                startOfWeekCalendar.get(Calendar.YEAR);

        String endDate = endOfWeekCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (endOfWeekCalendar.get(Calendar.MONTH) + 1) + "/" +
                endOfWeekCalendar.get(Calendar.YEAR);

        return startDate + " - " + endDate;
    }

    public int getReminingTimeSlots() {
        return reminingTimeSlots;
    }

    public ArrayList<String> getColorAreas() {
        return colorAreas;
    }

    public ArrayList<CalendarResponse> getEventAreas() {
        return eventAreas;
    }

    public String getGridAreas() {
        return gridAreas;
    }

}
