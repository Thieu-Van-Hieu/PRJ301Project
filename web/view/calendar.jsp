<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Calendar Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <style>
            .box {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .calendar {
                display: grid;
                gap: 1px;
                grid-template-rows: 2fr 24fr;
                position: relative;
                width: 100%;
                height: 100%;
                overflow-y: auto;
                background-color: #f0f0f0;
                opacity: 0.6;
            }

            .calendar__header {
                display: grid;
                gap: 1px;
                grid-template-columns: repeat(8, 1fr);
                position: sticky;
                top: 0;
                border-bottom: 1px solid #ccc;
            }

            .calendar__controls, .calendar__day, .calendar__hour, .calendar__cell, .calendar__event {
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #ccc;
                font-size: 1.4rem;
            }

            .calendar__controls {
                flex-direction: column;
            }

            .calendar__buttons {
                display: flex;
                justify-content: space-evenly;
                width: 100%;              
            }

            .calendar__buttons button {
                padding: 0.5rem 1rem;
                font-size: 1.4rem;
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 0.5rem;
                cursor: pointer;
            }

            .calendar__buttons button:hover {
                background-color: #f0f0f0;
            }

            .calendar__day {
                font-size: 2.4rem;
                font-weight: bold;
            }

            .calendar__body {
                display: grid;
                gap: 1px;
                grid-template-columns: 1fr 7fr;
            }

            .calendar__hours {
                display: grid;
                gap: 1px;
                grid-template-rows: repeat(24, 1fr);
            }

            .calendar__events {
                display: grid;
                gap: 1px;
                grid-template-rows: repeat(24, 1fr);
                grid-template-columns: repeat(7, 1fr);
            }

            .calendar__event {
                background-color: green;
                padding: 0.5rem;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
            }

        </style>
    </head>
    <body>
        <%
            // Tạo một ArrayList để lưu các sự kiện
            ArrayList<Event> events = new ArrayList<>();

            // Thêm một vài sự kiện với ngày và giờ cụ thể
            events.add(new Event(1, "Meeting", "Team meeting for project", Timestamp.valueOf("2025-03-10 10:00:00"), Timestamp.valueOf("2025-03-12 11:00:00")));
            events.add(new Event(2, "Workshop", "Java Web Development workshop", Timestamp.valueOf("2025-03-14 14:00:00"), Timestamp.valueOf("2025-03-14 17:00:00")));
            events.add(new Event(3, "Conference", "Annual tech conference", Timestamp.valueOf("2025-03-15 09:00:00"), Timestamp.valueOf("2025-03-16 18:00:00")));
            events.add(new Event(4, "Deadline", "Submit project report", Timestamp.valueOf("2025-03-25 22:00:00"), Timestamp.valueOf("2025-03-26 00:00:00")));
            events.add(new Event(5, "Hackathon", "Coding competition", Timestamp.valueOf("2025-04-10 08:00:00"), Timestamp.valueOf("2025-04-11 20:00:00")));

            // Lấy ngày hiện tại
            Date currentDate = new Date(System.currentTimeMillis());

            // Lấy ngày hiện tại trong tuần
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // Ngày bắt đầu của tuần
            Calendar startOfWeekCalendar = Calendar.getInstance();
            startOfWeekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startOfWeekCalendar.set(Calendar.MINUTE, 0);
            startOfWeekCalendar.set(Calendar.SECOND, 0);
            startOfWeekCalendar.set(Calendar.MILLISECOND, 0);

            // Ngày kết thúc của tuần
            Calendar endOfWeekCalendar = (Calendar) startOfWeekCalendar.clone();
            endOfWeekCalendar.add(Calendar.DAY_OF_WEEK, 6);
            endOfWeekCalendar.set(Calendar.HOUR_OF_DAY, 23);
            endOfWeekCalendar.set(Calendar.MINUTE, 59);
            endOfWeekCalendar.set(Calendar.SECOND, 59);
            endOfWeekCalendar.set(Calendar.MILLISECOND, 999);

            String startDate = startOfWeekCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            (startOfWeekCalendar.get(Calendar.MONTH) + 1) + "/" +
                            startOfWeekCalendar.get(Calendar.YEAR);

            String endDate = endOfWeekCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            (endOfWeekCalendar.get(Calendar.MONTH) + 1) + "/" +
                            endOfWeekCalendar.get(Calendar.YEAR);

            String week = startDate + " - " + endDate;
            pageContext.setAttribute("week", week);

            // Lấy danh sách các sự kiện trong tuần
            ArrayList<Event> eventsInWeek = new ArrayList<>();
            for (Event event : events) {
                if ((event.getStartDateCalendar().after(startOfWeekCalendar) && event.getStartDateCalendar().before(endOfWeekCalendar)) || (event.getEndDateCalendar().after(startOfWeekCalendar) && event.getEndDateCalendar().before(endOfWeekCalendar))) {
                    eventsInWeek.add(event);
                }
            }
            pageContext.setAttribute("eventsInWeek", eventsInWeek);

            int reminingTimeSlots = 0;

            ArrayList<Event> eventAreas = new ArrayList<>();

            // Tạo một mảng 2 chiều để lưu thông tin các sự kiện trong tuần
            final int HOURS = 24;
            final int DAYS = 7;
            String eventSlots[][] = new String[HOURS][DAYS];
            for (Event event : eventsInWeek) {
                calendar.setTime(event.getStartDate());
                int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (startDayOfWeek == 1) {
                    startDayOfWeek = 7;
                } else {
                    startDayOfWeek--;
                }

                int startHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

                if (calendar.before(startOfWeekCalendar)) {
                    startDayOfWeek = 1;
                    startHourOfDay = 0;
                }

                calendar.setTime(event.getEndDate());
                int endDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if (endDayOfWeek == 1) {
                    endDayOfWeek = 7;
                } else {
                    endDayOfWeek--;
                }
                int endHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

                if (calendar.after(endOfWeekCalendar)) {
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

                    for (int j = startHour; j < endHour; j++) {
                        eventSlots[j][i] = "event-" + eventAreas.size();
                    }
                    
                    eventAreas.add(event);
                }
            }

            pageContext.setAttribute("eventAreas", eventAreas);

            // Tính số lượng ô trống còn lại
            for (int i = 0; i < HOURS; i++) {
                for (int j = 0; j < DAYS; j++) {
                    if (eventSlots[i][j] == null) {
                        eventSlots[i][j] = ".";
                        reminingTimeSlots++;
                    }
                }
            }

            pageContext.setAttribute("reminingTimeSlots", reminingTimeSlots);

            // Từ mảng 2 chiều eventSlots, tạo ra template-areas cho grid layout
            StringBuilder gridAreas = new StringBuilder();
            for (int i = 0; i < HOURS; i++) {
                gridAreas.append("'");
                for (int j = 0; j < DAYS; j++) {
                    gridAreas.append(eventSlots[i][j]);
                    gridAreas.append(" ");
                }

                gridAreas.append("'\n");
            }

            pageContext.setAttribute("gridAreas", gridAreas.toString());

        %>
        <jsp:include page="background.jsp" />
        <div class="box">
            <div class="calendar">
                <div class="calendar__header">
                    <div class="calendar__controls">
                        <div class="calendar__week">
                            ${week}
                        </div>
                        <div class="calendar__buttons">
                            <button onclick="location.href='#'">Previous</button>
                            <button onclick="location.href='#'">Next</button>
                        </div>
                    </div>
                    <div class="calendar__day">Thứ 2</div>
                    <div class="calendar__day">Thứ 3</div>
                    <div class="calendar__day">Thứ 4</div>
                    <div class="calendar__day">Thứ 5</div>
                    <div class="calendar__day">Thứ 6</div>
                    <div class="calendar__day">Thứ 7</div>
                    <div class="calendar__day">Chủ nhật</div>
                </div>
                <div class="calendar__body">
                    <div class="calendar__hours">
                        <c:forEach var="i" begin="0" end="23">
                            <div class="calendar__hour">${i}:00</div>
                        </c:forEach>
                    </div>
                    <div class="calendar__events" style="grid-template-areas: ${gridAreas}">
                        <c:forEach var="i" begin="1" end="${reminingTimeSlots}">
                            <div class="calendar__cell"></div>
                        </c:forEach>
                        <c:forEach var="i" begin="0" end="${eventAreas.size() - 1}">
                            <div class="calendar__event" style="grid-area: event-${i}">${eventAreas.get(i).name}</div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
