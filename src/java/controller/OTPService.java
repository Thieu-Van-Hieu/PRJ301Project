package controller;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import services.UserService;

public class OTPService {

    public static String generateOTP() {
        int otp = 100000 + new Random().nextInt(900000);
        return String.valueOf(otp);
    }

    public static void sendOTPEmail(String toEmail, String otp) {
        final String fromEmail = "hoahvhe186810@fpt.edu.vn"; 
        final String password = "qakuxnsvjcigontk";            
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác nhận yêu cầu quên mật khẩu - 4Club", "UTF-8");
            UserService userService = new UserService();
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
                    + "      <p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản " + userService.getUsernameByEmail() +  " tại <strong>4Club</strong>.</p>"
                    + "      <p>Mã OTP của bạn là: <span class='otp'>" + otp + "</span></p>"
                    + "      <p>Vui lòng nhập mã OTP này trên website trong vòng 10 phút để hoàn tất quá trình đặt lại mật khẩu.</p>"
                    + "      <p>Nếu bạn không yêu cầu thay đổi mật khẩu, vui lòng bỏ qua email này.</p>"
                    + "    </div>"
                    + "    <div class='footer'>"
                    + "      <p>Trân trọng,</p>"
                    + "      <p>Đội ngũ 4Club</p>"
                    + "    </div>"
                    + "  </div>"
                    + "</body>"
                    + "</html>";
            
            message.setContent(body, "text/html; charset=UTF-8");
            Transport.send(message);
            System.out.println("OTP đã được gửi thành công tới " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
