/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.UserInformationResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import services.UserService;

/**
 *
 * @author ngoct
 */
public class InformationServlet extends HttpServlet {

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
            out.println("<title>Servlet InformationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformationServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        UserService service = new UserService();
        String hoTen = request.getParameter("hoTen");
        String lastName = request.getParameter("ten");
        String studentId = request.getParameter("maSV");
        String gender = request.getParameter("gioiTinh");
        boolean isExistStudentId = service.isExistStudentId(studentId);
        if (isExistStudentId) {
            session.setAttribute("error", "Mã sinh viên đã tồn tại");
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
            return;
        }
        String temp[] = hoTen.split(" ");

        String firstName = temp[0];
        int ngay = Integer.valueOf(request.getParameter("ngaySinh"));
        int thang = Integer.valueOf(request.getParameter("thangSinh"));
        int nam = Integer.valueOf(request.getParameter("namSinh"));

        String city = request.getParameter("province");
        String district = request.getParameter("district");
        String ward = request.getParameter("ward");

        LocalDate ngaySinh = LocalDate.of(nam, thang, ngay);
        String formattedDate = ngaySinh.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = ward + ", " + district + ", " + city;

        UserInformationResponse infor = new UserInformationResponse(firstName, lastName, studentId, address, gender, formattedDate);

        service.addByParamIntoResponse(infor);
        service.addUserRoleMember();
        service.addInformationOfUser();

        UserInformationResponse result = service.getResults();
        session.setAttribute("user", result);
        session.setAttribute("success", "Đăng ký thành công!");
        response.sendRedirect(request.getContextPath() + "/view/login.jsp");
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
