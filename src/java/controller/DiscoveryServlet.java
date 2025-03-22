/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.ClubResponse;
import dto.UserInformationResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import services.ClubService;
import java.sql.Timestamp;
import services.UserService;

/**
 *
 * @author hunggt1572004
 */
public class DiscoveryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DiscoveryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DiscoveryServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ClubService clubService = new ClubService();
        UserService userService = new UserService();
        int userId = (Integer) session.getAttribute("userId");
        UserInformationResponse userInformation = userService.getUserInfor(userId);
        if ("open".equals(action)) {
            int clubId = Integer.parseInt(request.getParameter("clubId"));
            session.setAttribute("clubId", clubId);
            session.setAttribute("userId", userId);
            response.sendRedirect(request.getContextPath() + "/ForumServlet");
        } else if ("setting".equals(action)) {
            UserInformationResponse userInfor = userService.getUserInforSetting(userId);
            session.setAttribute("userInformation", userInformation);
            session.setAttribute("userInfor", userInfor);
            response.sendRedirect(request.getContextPath() + "/view/settingUserInformation.jsp");
        } else if ("createClub".equals(action)) {

        } else if (action == null) {
            ArrayList<ClubResponse> clubListItems = clubService.selectAllClubItems(userId);
            ArrayList<ClubResponse> clubList = clubService.selectAllClubInformations();
            session.setAttribute("userInformation", userInformation);
            session.setAttribute("clubListItems", clubListItems);
            session.setAttribute("clubList", clubList);
            response.sendRedirect(request.getContextPath() + "/view/discovery.jsp");
        }

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
        int userId = (Integer) session.getAttribute("userId");
        if (action.equals("joinClub")) {
            String createTimeStr = request.getParameter("createTime");
            Timestamp createTime = Timestamp.valueOf(createTimeStr);

        }

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
