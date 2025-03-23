package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Member;
import dto.RequestClubResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.RequestClubService;

/**
 * @author quann
 */
public class RequestClubServlet extends HttpServlet {

    /**
     * Handles requests for both HTTP GET and POST methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClubRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClubRequestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestClubService service = new RequestClubService();
        String action = request.getParameter("action");

        Member member = (Member) session.getAttribute("member");

        ArrayList<RequestClubResponse> requests = service.getAllRequest(member.getClubId());

        if (session.getAttribute("error") != null) {
            request.setAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }

        if (session.getAttribute("requestId") != null) {
            request.setAttribute("requestId", session.getAttribute("requestId"));
            session.removeAttribute("requestId");
        }
        request.setAttribute("requests", requests);
        session.setAttribute("includeWeb", "requestClub.jsp");
        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
    }

    /** Handles the HTTP POST method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestClubService service = new RequestClubService();

        String action = request.getParameter("action");
        if (action.equals("createRequest")) {
            
        }

        if (action.equals("updateRequest")) {
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            String status = request.getParameter("status");

            if (!service.updateRequest(requestId, status)) {
                session.setAttribute("error", "Xảy ra lỗi khi cập nhật yêu cầu");
                session.setAttribute("requestId", requestId);
            }
        }

        response.sendRedirect(request.getContextPath() + "/RequestClubServlet");
    }

    /** Returns a short description of the servlet. */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
