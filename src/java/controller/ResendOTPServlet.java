/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.EmailService;

/**
 *
 * @author ngoct
 */
public class ResendOTPServlet extends HttpServlet {

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
            out.println("<title>Servlet ResendOTPServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendOTPServlet at " + request.getContextPath() + "</h1>");
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
        String email = (String) request.getSession().getAttribute("email");
        HttpSession session = request.getSession();
        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("errorOTP", "Không tìm thấy email. Vui lòng đăng ký lại.");
            request.getRequestDispatcher("view/otp.jsp").forward(request, response);
            return;
        }
        String otp = EmailService.generateOTP();
        String subject = "Mã OTP mới từ 4Club";
        String body = "<html>"
                + "<head>"
                + "  <meta charset='UTF-8'>"
                + "  <style>"
                + "    body { font-family: Arial, sans-serif; color: #333; }"
                + "    .container { max-width: 600px; margin: auto; padding: 20px; }"
                + "    .header { background-color: #007BFF; color: #fff; padding: 10px; text-align: center; }"
                + "    .content { margin-top: 20px; }"
                + "    .otp { font-size: 24px; font-weight: bold; color: #007BFF; }"
                + "    .footer { margin-top: 30px; font-size: 12px; color: #777; text-align: center; }"
                + "  </style>"
                + "</head>"
                + "<body>"
                + "  <div class='container'>"
                + "    <div class='header'>"
                + "      <h1>4Club</h1>"
                + "    </div>"
                + "    <div class='content'>"
                + "      <p>Chào bạn,</p>"
                + "      <p>Dưới đây là mã OTP mới của bạn:</p>"
                + "      <p>Mã OTP của bạn là: <span class='otp'>" + otp + "</span></p>"
                + "      <p>Vui lòng nhập mã OTP này trên website trong vòng 10 phút để hoàn tất quá trình đăng ký.</p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      <p>Trân trọng,</p>"
                + "      <p>Đội ngũ 4Club</p>"
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";
        EmailService.sendEmail(email, subject, body);
        request.getSession().setAttribute("generatedOTP", otp);
        session.setAttribute("success", "Mã OTP mới đã được gửi tới email của bạn.");
        response.sendRedirect(request.getContextPath() + "/view/otp.jsp");
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
