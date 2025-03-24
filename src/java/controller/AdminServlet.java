/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Club;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import services.ClubService;
import services.UserService;

/**
 *
 * @author hunggt1572004
 */
public class AdminServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        ClubService clubService = new ClubService();
        UserService userService = new UserService();

        if ("updateRole".equals(action)) {
            try {
                int userUpdateId = Integer.parseInt(request.getParameter("userUpdateId"));

                String userRole = userService.getUserRole(userUpdateId);
                if ("admin".equals(userRole)) {
                    userService.updateUserRole(userUpdateId, "member");
                } else {
                    userService.updateUserRole(userUpdateId, "admin");
                }
                session.setAttribute("success", "Update Successfully!");
            } catch (Exception e) {
                session.setAttribute("error", "Fail To Update!");
            }
        } else if ("deleteClub".equals(action)) {
            try {
                int clubDeleteId = Integer.parseInt(request.getParameter("clubDeleteId"));
                clubService.deleteClubFromAdmin(clubDeleteId);
                session.setAttribute("success", "Delete Successfully!");
            } catch (Exception e) {
                session.setAttribute("error", "Fail To Delete!");
            }
        } else if ("logout".equals(action)) {
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        ArrayList<User> userList = userService.getUserListForAdmin();
        ArrayList<Club> clubList = clubService.getAllClubForAdmin();
        session.setAttribute("userList", userList);
        session.setAttribute("clubList", clubList);
        response.sendRedirect(request.getContextPath() + "/view/admin.jsp");
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
        processRequest(request, response);
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
