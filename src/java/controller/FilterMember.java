/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.FilterMemberDTO;
import dto.FilterMemberResponseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import services.MemberService;

/**
 *
 * @author ngoct
 */
public class FilterMember extends HttpServlet {

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
            out.println("<title>Servlet FilterMember</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterMember at " + request.getContextPath() + "</h1>");
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
        int departmentId = Integer.valueOf(request.getParameter("department"));
        String gender = request.getParameter("gender");
        HttpSession session = request.getSession();
        Integer ageFrom;
        if (!request.getParameter("ageFrom").isBlank()) {
            ageFrom = Integer.valueOf(request.getParameter("ageFrom"));
        } else {
            ageFrom = null;
        }
        Integer ageTo;
        if (!request.getParameter("ageTo").isBlank()) {
            ageTo = Integer.valueOf(request.getParameter("ageTo"));
        } else {
            ageTo = null;
        }
        String nameSearch = request.getParameter("search");
        String dateJoin = request.getParameter("dateJoin");
        FilterMemberDTO filterMemberDTO = new FilterMemberDTO(departmentId, gender, ageFrom, ageTo, dateJoin, nameSearch);
        
        MemberService memberService = new MemberService();
        
        ArrayList<FilterMemberResponseDTO> filterMemberResponseDTO = memberService.filterMember(filterMemberDTO);
        
        request.setAttribute("filterMemberResponseDTO", filterMemberResponseDTO);
        session.setAttribute("success", "filter thành công!");
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
