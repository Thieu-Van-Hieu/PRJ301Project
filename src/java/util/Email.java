package util;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    public static void main(String[] args) {
        final String from = "hoahvhe186810@fpt.edu.vn"; // Email của bạn
        final String password = "rbun mtpv hieg xyxi"; // Mật khẩu ứng dụng

        // Cấu hình SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Sửa lỗi "smtl" → "smtp"
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Sửa lỗi "smtl.starttls.enable"

        // Xác thực tài khoản gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Tạo email
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("hauhoa784@gmail.com"));
            msg.setSubject("Thử nghiệm gửi email");
            msg.setText("Đây là nội dung email gửi từ Java.");

            // Gửi email
            Transport.send(msg);
            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
