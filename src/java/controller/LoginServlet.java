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
 * @author ngoct
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("username") == null) {
            session.setAttribute("error", "Bạn không có quyền vào đây");
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs * @param request servlet
     * request
     *
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        boolean isCheck = userService.checkLogin(username, password);
        int userId = userService.getUsername(username, password).getId();
        String userRole = userService.getUserRole(userId);
        if (isCheck && "member".equals(userRole)) {
            session.setAttribute("userId", userId);
            session.setAttribute("success", "Đăng nhập thành công");
            session.setAttribute("username", username);
            session.setAttribute("member", userRole);
            response.sendRedirect(request.getContextPath() + "/DiscoveryServlet");
        } else if (isCheck && "admin".equals(userRole)) {
            ClubService clubService = new ClubService();
            ArrayList<User> userList = userService.getUserListForAdmin();
            ArrayList<Club> clubList = clubService.getAllClubForAdmin();
            session.setAttribute("userList", userList);
            session.setAttribute("userId", userId);
            session.setAttribute("admin", userRole);
            session.setAttribute("clubList", clubList);
            response.sendRedirect(request.getContextPath() + "/view/admin.jsp");
        } else {
            session.setAttribute("error", "Tài khoản hoặc mật khẩu sai!");
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
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
