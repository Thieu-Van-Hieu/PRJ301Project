/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

public class ResetPasswordService {

    // Cấu hình email gửi (thay đổi theo tài khoản của bạn)
    private static final String FROM_EMAIL = "hoahvhe186810@fpt.edu.vn";
    private static final String PASSWORD = "qakuxnsvjcigontk"; // Mật khẩu ứng dụng (nếu cần)

    /**
     * Tạo token đặt lại mật khẩu.
     *
     * @return Token reset dạng String.
     */
    public static String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gửi email đặt lại mật khẩu đến người dùng.
     *
     * @param toEmail Địa chỉ email nhận.
     * @param resetToken Token reset được tạo.
     */
    public static void sendResetPasswordEmail(String toEmail, String resetToken) {
        // Tạo đường link reset (ở local, đường link sẽ trỏ đến địa chỉ localhost)
        String resetLink = "http://localhost:8080/PRJ301Project/ResetPasswordServlet?token=" + resetToken;

        String subject = "Đặt lại mật khẩu - 4Club";
        String body = "<html>"
                + "<head>"
                + "  <meta charset='UTF-8'>"
                + "  <style>"
                + "    body { font-family: Arial, sans-serif; color: #333; }"
                + "    .container { max-width: 600px; margin: auto; padding: 20px; }"
                + "    .header { background-color: #007BFF; color: #fff; padding: 10px; text-align: center; }"
                + "    .content { margin-top: 20px; }"
                + "    .reset-link { font-size: 18px; color: #007BFF; }"
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
                + "      <p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản tại 4Club.</p>"
                + "      <p>Vui lòng click vào đường link bên dưới để đặt lại mật khẩu:</p>"
                + "      <p><a class='reset-link' href='" + resetLink + "'>Đặt lại mật khẩu</a></p>"
                + "      <p>Lưu ý: Đường link này có thời hạn sử dụng trong 10 phút.</p>"
                + "      <p>Nếu bạn không yêu cầu thay đổi mật khẩu, vui lòng bỏ qua email này.</p>"
                + "    </div>"
                + "    <div class='footer'>"
                + "      <p>Trân trọng,</p>"
                + "      <p>Đội ngũ 4Club</p>"
                + "    </div>"
                + "  </div>"
                + "</body>"
                + "</html>";

        sendEmail(toEmail, subject, body);
    }

    /**
     * Hàm gửi email sử dụng Jakarta Mail API.
     *
     * @param toEmail Địa chỉ email nhận.
     * @param subject Chủ đề email.
     * @param body Nội dung email (HTML).
     */
    public static void sendEmail(String toEmail, String subject, String body) {
        // Cấu hình SMTP cho Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Tạo session với xác thực
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            // Tạo MimeMessage
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");
            message.setContent(body, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Phương thức main để kiểm tra chức năng gửi email reset mật khẩu.
    public static void main(String[] args) {
        String toEmail = "recipient-email@example.com"; // Thay bằng email thực tế cần test
        String token = generateResetToken();
        sendResetPasswordEmail(toEmail, token);
    }
}
