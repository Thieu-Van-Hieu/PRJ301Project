/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.ClubResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import entity.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import util.FileService;
import services.EventService;
import dto.EventResponse;
import dto.SearchEventDTO;
import services.ClubService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50)    //50MB
public class EventServlet extends HttpServlet {

    private static final String IMG_DIR = "D:\\Study\\PRJ301\\NB_workplace\\PRJ301Project\\web\\assets\\img\\img-download";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        EventService eventService = new EventService();
        ClubService clubService = new ClubService();
        HttpSession session = request.getSession();
        ArrayList<EventType> eventTypes = eventService.selectAllEventType();
        ArrayList<EventResponse> eventDescriptions = null;
        ArrayList<ClubResponse> clubIdAndNames = clubService.selectAllClubIdAndClubName();
        int userId = (Integer) session.getAttribute("userId");
        if (action == null) {
            eventDescriptions = eventService.getAllEventDescription();
        } else if (action.equals("filter")) {
            String nameEvent = request.getParameter("search");
            String typeId = request.getParameter("type");
            String dateStr = request.getParameter("date");
            String status = request.getParameter("status");
            int clubId = Integer.valueOf(request.getParameter("clubId"));
            SearchEventDTO searchEventDTO = new SearchEventDTO(dateStr, typeId, nameEvent, status, clubId);
            eventDescriptions = eventService.getSearchEvent(searchEventDTO);
        }
        session.setAttribute("userId", userId);
        request.setAttribute("clubDescriptions", clubIdAndNames);
        request.setAttribute("eventDescriptions", eventDescriptions);
        request.setAttribute("eventTypes", eventTypes);
        request.setAttribute("includeWeb", "event.jsp");
        request.setAttribute("action", session.getAttribute("action"));
        session.removeAttribute("action");
        request.setAttribute("eventId", session.getAttribute("eventId"));
        System.out.println(session.getAttribute("eventId"));
        session.removeAttribute("eventId");
        request.setAttribute("clubId", session.getAttribute("clubId"));
        request.setAttribute("startDate", session.getAttribute("startDate"));
        session.removeAttribute("startDate");
        request.setAttribute("endDate", session.getAttribute("endDate"));
        session.removeAttribute("endDate");

        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        EventService eventService = new EventService();
        if (action.equals("add")) {
            try {
                String name = request.getParameter("nameEvent");
                int userId = Integer.parseInt(request.getParameter("userId"));
                int clubId = Integer.parseInt(request.getParameter("clubId"));
                int typeId = Integer.parseInt(request.getParameter("type"));
                String province = request.getParameter("province");
                String district = request.getParameter("district");
                String ward = request.getParameter("ward");
                String address = request.getParameter("locationInfor");
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");
                String description = request.getParameter("description");
                Timestamp startDate = null;
                Timestamp endDate = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                    startDate = new Timestamp(dateFormat.parse(startDateStr).getTime());
                    endDate = new Timestamp(dateFormat.parse(endDateStr).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Part filePart = request.getPart("file");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String fileExtension = FileService.getFileExtension(fileName);
                String uploadDir = "";

                if (FileService.isImage(fileExtension)) {
                    uploadDir = IMG_DIR;
                } else {
                    String error = "Invalid file format! Only JPG, PNG are allowed.";
                }

                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }
                String imgName = name + "_" + clubId + "." + fileExtension;
                String normalized = FileService.normalizeFileName(imgName);

                String filePath = uploadDir + File.separator + normalized;
                filePart.write(filePath);

                eventService.addEventLocation(province, district, ward, address);

                int locationId = eventService.getLocationId(province, district, ward, address);

                eventService.addEvent(clubId, userId, name, description, typeId, startDate, endDate, locationId, normalized);
                request.setAttribute("action", null);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
