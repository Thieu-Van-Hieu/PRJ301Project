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
                height: 100%;
                width: 100%;
                overflow: auto;
            }

            .calendar {
                display: grid;
                gap: 1px;
                grid-template-rows: 2fr 24fr;
                position: relative;
                width: 100%;
                height: 100%;
                overflow-y: auto;
                background-color:#f0f0f0;
                color: #131314;
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
                background-color: #fff;
                font-size: 1.4rem;
            }

            .calendar__controls {
                display: flex;
                flex-direction: column;
                padding: 0.5rem;
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
                padding: 0.5rem;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                border-radius: 1rem;
            }

            /* Responsive cho màn hình nhỏ hơn 768px (điện thoại) */
            @media (max-width: 768px) {
                .calendar {
                    grid-template-rows: auto 1fr; /* Điều chỉnh chiều cao các phần */
                    gap: 5px;
                }

                .calendar__header {
                    grid-template-columns: repeat(7, 1fr); /* Giữ nguyên 7 cột nhưng giảm kích thước */
                    gap: 2px;
                }

                .calendar__day {
                    font-size: 1.2rem; /* Giảm kích thước chữ của ngày */
                    padding: 0.5rem;
                }

                .calendar__body {
                    grid-template-columns: 1fr 7fr; /* Giữ nguyên tỷ lệ cột giờ và sự kiện */
                    gap: 2px;
                }

                .calendar__hours {
                    font-size: 1rem; /* Giảm kích thước chữ của giờ */
                }

                .calendar__events {
                    grid-template-columns: repeat(7, 1fr); /* Giữ nguyên 7 cột */
                    grid-template-rows: repeat(24, 1fr); /* Giữ nguyên 24 hàng */
                    gap: 2px;
                }

                .calendar__event {
                    font-size: 1rem; /* Giảm kích thước chữ trong sự kiện */
                    padding: 0.3rem;
                }
            }

            /* Responsive cho màn hình nhỏ hơn 1024px (máy tính bảng) */
            @media (max-width: 1024px) {
                .calendar {
                    grid-template-rows: auto 1fr;
                    gap: 10px;
                }

                .calendar__header {
                    grid-template-columns: repeat(7, 1fr); /* Giữ nguyên 7 cột */
                    gap: 5px;
                }

                .calendar__day {
                    font-size: 1.6rem; /* Điều chỉnh kích thước chữ của ngày */
                    padding: 0.8rem;
                }

                .calendar__body {
                    grid-template-columns: 1fr 7fr; /* Giữ nguyên tỷ lệ cột giờ và sự kiện */
                    gap: 5px;
                }

                .calendar__hours {
                    font-size: 1.2rem; /* Điều chỉnh kích thước chữ của giờ */
                }

                .calendar__events {
                    grid-template-columns: repeat(7, 1fr); /* Giữ nguyên 7 cột */
                    grid-template-rows: repeat(24, 1fr); /* Giữ nguyên 24 hàng */
                    gap: 5px;
                }

                .calendar__event {
                    font-size: 1.2rem; /* Điều chỉnh kích thước chữ trong sự kiện */
                    padding: 0.5rem;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="contentHeader.jsp" />
        <div class="box">
            <div class="calendar">
                <div class="calendar__header">
                    <div class="calendar__controls">
                        <div class="calendar__week">
                            ${week}
                        </div>
                        <div class="calendar__buttons">
                            <button onclick="location.href='${pageContext.request.contextPath}/CalendarServlet?action=prev'">◀</button>
                            <button onclick="location.href='${pageContext.request.contextPath}/CalendarServlet'">⟳</button>
                            <button onclick="location.href='${pageContext.request.contextPath}/CalendarServlet?action=next'">▶</button>
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
                        <c:if test="${eventAreas.size() > 0}">
                            <c:forEach var="i" begin="0" end="${eventAreas.size() - 1}">
                                <div class="calendar__event" style="grid-area: event-${i}; background-color: ${colorAreas.get(i)}">${eventAreas.get(i).name}</div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
