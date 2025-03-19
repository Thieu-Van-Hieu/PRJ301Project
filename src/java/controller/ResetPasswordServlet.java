package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPasswordServlet extends HttpServlet {

    // Xử lý GET: Hiển thị form reset password nếu token hợp lệ
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        HttpSession session = request.getSession(false);

        // Nếu session không tồn tại, nghĩa là session đã hết hạn
        if (session == null) {
            response.getWriter().println("Session đã hết hạn. Vui lòng yêu cầu đặt lại mật khẩu mới.");
            return;
        }

        // Lấy token đã lưu trong session
        String sessionToken = (String) session.getAttribute("resetToken");

        // Kiểm tra token trong URL có khớp với token trong session không
        if (token != null && token.equals(sessionToken)) {
            // Token hợp lệ, chuyển hướng đến form reset password và chuyển token qua JSP
            request.setAttribute("token", token);
            request.getRequestDispatcher("view/reset_password.jsp").forward(request, response);
        } else {
            response.getWriter().println("Token không hợp lệ hoặc đã hết hạn.");
        }
    }

    // Xử lý POST: Nhận mật khẩu mới từ form và cập nhật mật khẩu cho người dùng
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu có khớp không
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu không khớp hoặc không hợp lệ.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("view/reset_password.jsp").forward(request, response);
            return;
        }

        // Lấy session hiện tại để kiểm tra token
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.getWriter().println("Session đã hết hạn. Vui lòng yêu cầu đặt lại mật khẩu mới.");
            return;
        }

        String sessionToken = (String) session.getAttribute("resetToken");
        if (token == null || !token.equals(sessionToken)) {
            response.getWriter().println("Token không hợp lệ hoặc đã hết hạn.");
            return;
        }

        // Nếu token hợp lệ, cập nhật mật khẩu (ở đây chỉ demo, cần tích hợp với DB trong thực tế)
        boolean updated = updatePassword(token, newPassword);
        if (updated) {
            // Sau khi cập nhật thành công, xóa token khỏi session để tránh dùng lại
            session.removeAttribute("resetToken");
            session.removeAttribute("resetEmail");
            response.sendRedirect("reset_success.jsp");
        } else {
            request.setAttribute("error", "Có lỗi xảy ra khi cập nhật mật khẩu. Vui lòng thử lại.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("view/reset_password.jsp").forward(request, response);
        }
    }

    // Hàm giả lập cập nhật mật khẩu (thực tế, bạn cần cập nhật vào DB)
    private boolean updatePassword(String token, String newPassword) {
        System.out.println("Cập nhật mật khẩu cho token: " + token + " thành: " + newPassword);
        // Giả sử việc cập nhật thành công
        return true;
    }

    @Override
    public String getServletInfo() {
        return "ResetPasswordServlet xử lý form reset mật khẩu";
    }
}
