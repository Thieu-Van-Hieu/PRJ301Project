/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import dto.CalendarResponse;

import java.util.ArrayList;
import java.util.Arrays;
import entity.Event;
import entity.Member;
import jakarta.servlet.http.HttpSession;
import services.CalendarService;

/**
 * @author quann
 */
public class CalendarServlet extends HttpServlet {

    private CalendarService service = new CalendarService();

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        service.init();
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        Map<String, Runnable> map = Map.of(
                "", () -> service.setCurrentWeek(),
                "next", () -> service.setNextWeek(),
                "prev", () -> service.setPreviousWeek());

        map.get(action).run();

        service.getCalendarResponses(member.getClubId());

        Date weekStart = service.getMondayCalendar().getTime();
        Date weekEnd = service.getSundayCalendar().getTime();
        ArrayList<String> colorAreas = service.getColorAreas();
        ArrayList<CalendarResponse> eventAreas = service.getEventAreas();
        int reminingTimeSlots = service.getReminingTimeSlots();
        String gridAreas = service.getGridAreas();

        request.setAttribute("weekStart", weekStart);
        request.setAttribute("weekEnd", weekEnd);
        request.setAttribute("colorAreas", colorAreas);
        request.setAttribute("eventAreas", eventAreas);
        request.setAttribute("reminingTimeSlots", reminingTimeSlots);
        request.setAttribute("gridAreas", gridAreas);

        session.setAttribute("includeWeb", "calendar.jsp");

        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
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
