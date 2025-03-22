package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import services.UserService;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.getWriter().println("Session đã hết hạn. Vui lòng yêu cầu đặt lại mật khẩu mới.");
            return;
        }
        String sessionToken = (String) session.getAttribute("resetToken");
        if (token != null && token.equals(sessionToken)) {
            request.setAttribute("token", token);
            request.getRequestDispatcher("view/reset_password.jsp").forward(request, response);
        } else {
            response.getWriter().println("Token không hợp lệ hoặc đã hết hạn.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        UserService userService = new UserService();
        userService.resetPassword(newPassword);
        HttpSession session = request.getSession();
        boolean updated = updatePassword(newPassword);
        if (updated) {
            session.setAttribute("success", "Đổi mật khẩu thành công!");
            response.sendRedirect(request.getContextPath() + "/view/resetSuccess.jsp");
        } else {
            session.setAttribute("error", "Có lỗi xảy ra khi cập nhật mật khẩu. Vui lòng thử lại.");
            request.getRequestDispatcher("view/resetPassword.jsp").forward(request, response);
        }
    }

    private boolean updatePassword(String newPassword) {
        System.out.println("Cập nhật mật khẩu thành: " + newPassword);
        return true;
    }

    @Override
    public String getServletInfo() {
        return "ResetPasswordServlet xử lý cập nhật mật khẩu (không sử dụng session để lấy email)";
    }
}
