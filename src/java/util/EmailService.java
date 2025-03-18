package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.DataHandler;
import java.util.Properties;

public class EmailService {
    public static String generateOTP() {
        int otp = 100000 + (int)(Math.random() * 900000);
        return String.valueOf(otp);
    }

    public static void sendEmail(String toEmail, String subject, String body) {
        final String fromEmail = "hoahvhe186810@fpt.edu.vn";  
        final String password = "qakuxnsvjcigontk";

        // Cấu hình SMTP cho Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject, "UTF-8");
            message.setContent(body, "text/html; charset=UTF-8");
            Transport.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String toEmail = "recipient-email@example.com"; 
        String subject = "Xác nhận đăng ký tài khoản - 4Club";
        String otp = generateOTP();
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
                + "      <p>Cảm ơn bạn đã đăng ký tài khoản tại <strong>4Club</strong> - Hệ thống quản lý câu lạc bộ hàng đầu.</p>"
                + "      <p>Mã OTP của bạn là: <span class='otp'>" + otp + "</span></p>"
                + "      <p>Vui lòng nhập mã OTP này trên website trong vòng 10 phút để hoàn tất quá trình đăng ký.</p>"
                + "      <p>Nếu bạn không yêu cầu đăng ký, vui lòng bỏ qua email này.</p>"
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
}
