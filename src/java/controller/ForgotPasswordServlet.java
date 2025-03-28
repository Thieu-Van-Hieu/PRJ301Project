package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import services.UserService;
import controller.OTPService;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class ForgotPasswordServlet extends HttpServlet {

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
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserService userService = new UserService();
        boolean isExistEmail = userService.isExistEmail(email);
        HttpSession session = request.getSession();
        if (isExistEmail) {
            String otp = OTPService.generateOTP();
            OTPService.sendOTPEmail(email, otp);
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("success", "Chúng tôi đã gửi OTP");
            response.sendRedirect(request.getContextPath() + "/view/otpSent.jsp?email=" + URLEncoder.encode(email, "UTF-8"));
        } else {
            session.setAttribute("error", "Email này không tồn tại trong hệ thống vui lòng kiểm tra lại");
            response.sendRedirect(request.getContextPath() + "/view/forgotPassword.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "ForgotPasswordServlet gửi OTP cho chức năng quên mật khẩu";
    }
}
