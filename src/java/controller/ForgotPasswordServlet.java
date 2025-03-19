package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import services.UserService;
import controller.OTPService;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserService userService = new UserService();
        boolean isExistEmail = userService.isExistEmail(email);
        if (isExistEmail) {
            String otp = OTPService.generateOTP();
            OTPService.sendOTPEmail(email, otp);
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("otpEmail", email);
            response.sendRedirect(request.getContextPath() + "/view/otpSent.jsp");
        } else {
            request.setAttribute("error", "Email này không tồn tại trong hệ thống!");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "ForgotPasswordServlet gửi OTP cho chức năng quên mật khẩu";
    }
}
