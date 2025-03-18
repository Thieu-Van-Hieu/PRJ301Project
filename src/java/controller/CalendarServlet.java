/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import model.Event;

/**
 * @author quann
 */
public class CalendarServlet extends HttpServlet {

    private Calendar calendar = Calendar.getInstance();
    private final ArrayList<String> colors = new ArrayList<>(Arrays.asList(
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

    private Calendar getMondayCalendar() {
        Calendar startOfWeekCalendar = (Calendar) calendar.clone();
        startOfWeekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        startOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startOfWeekCalendar.set(Calendar.MINUTE, 0);
        startOfWeekCalendar.set(Calendar.SECOND, 0);
        startOfWeekCalendar.set(Calendar.MILLISECOND, 0);

        return startOfWeekCalendar;
    }

    private Calendar getSundayCalendar() {
        Calendar endOfWeekCalendar = (Calendar) calendar.clone();
        endOfWeekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        endOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endOfWeekCalendar.set(Calendar.MINUTE, 59);
        endOfWeekCalendar.set(Calendar.SECOND, 59);
        endOfWeekCalendar.set(Calendar.MILLISECOND, 999);

        return endOfWeekCalendar;
    }

    private String getWeek() {
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

    private boolean isEventInWeek(Event event) {
        Calendar startOfWeekCalendar = getMondayCalendar();
        Calendar endOfWeekCalendar = getSundayCalendar();

        boolean isStartInWeek = event.getStartDateCalendar().after(startOfWeekCalendar)
                && event.getStartDateCalendar().before(endOfWeekCalendar);

        boolean isEndInWeek = event.getEndDateCalendar().after(startOfWeekCalendar)
                && event.getEndDateCalendar().before(endOfWeekCalendar);

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

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            Map<String, Runnable> map = Map.of(
                    "", () -> calendar = Calendar.getInstance(),
                    "next", () -> calendar.add(Calendar.WEEK_OF_YEAR, 1),
                    "prev", () -> calendar.add(Calendar.WEEK_OF_YEAR, -1));

            map.get(action).run();

            // Lấy danh sách các index màu ngẫu nhiên
            ArrayList<Integer> randomIndexColor = getRandomIndexColor();

            // Tạo một ArrayList để lưu các sự kiện
            ArrayList<String> colorAreas = new ArrayList<>();

            ArrayList<Event> events = new ArrayList<>();

            // Đặt tuần làm việc bắt đầu từ thứ 2
            calendar.setFirstDayOfWeek(Calendar.MONDAY);

            // Lấy ngày đầu tuần và
            Calendar startOfWeekCalendar = getMondayCalendar();
            Calendar endOfWeekCalendar = getSundayCalendar();

            // Lấy chuỗi thể hiện tuần hiện tại
            String week = getWeek();
            request.setAttribute("week", week);

            // Lấy danh sách các sự kiện trong tuần
            ArrayList<Event> eventsInWeek = new ArrayList<>();
            for (Event event : events) {
                if (isEventInWeek(event)) {
                    eventsInWeek.add(event);
                }
            }

            ArrayList<Event> eventAreas = new ArrayList<>();

            // Tạo một mảng 2 chiều để lưu thông tin các sự kiện trong tuần
            final int HOURS = 24;
            final int DAYS = 7;
            String eventSlots[][] = new String[HOURS][DAYS];
            for (Event event : eventsInWeek) {
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

            request.setAttribute("colorAreas", colorAreas);
            request.setAttribute("eventAreas", eventAreas);

            int reminingTimeSlots = 0;
            // Tính số lượng ô trống còn lại
            for (int i = 0; i < HOURS; i++) {
                for (int j = 0; j < DAYS; j++) {
                    if (eventSlots[i][j] == null) {
                        eventSlots[i][j] = ".";
                        reminingTimeSlots++;
                    }
                }
            }

            request.setAttribute("reminingTimeSlots", reminingTimeSlots);

            // Từ mảng 2 chiều eventSlots, tạo ra template-areas cho grid layout

            String gridAreas = getGridAreas(eventSlots);
            request.setAttribute("gridAreas", gridAreas);

            request.setAttribute("contentHeader", "Lịch");
        } catch (Exception e) {
        }
        request.getRequestDispatcher("/view/calendar.jsp").forward(request, response);
    }

    /** Handles the HTTP POST method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /** Returns a short description of the servlet. */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
