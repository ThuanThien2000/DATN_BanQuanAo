package poly.quanlyquanao.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String token) {
        String subject = "Xác minh tài khoản của bạn tại Shop Vintage Online";

        String verificationLink = "http://localhost:8080/api/auth/verify?token=" + token;

        String htmlContent = """
            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto;">
                <h2 style="color: #2e86de;">Xác minh tài khoản của bạn</h2>
                <p>Xin chào,</p>
                <p>Cảm ơn bạn đã đăng ký tài khoản tại <strong>Shop Vintage</strong>.</p>
                <p>Vui lòng nhấn vào nút bên dưới để xác minh email và kích hoạt tài khoản của bạn:</p>
                <div style="margin: 30px 0; text-align: center;">
                    <a href="%s" style="background-color: #2e86de; color: white; padding: 12px 20px; text-decoration: none; border-radius: 5px;">Xác minh tài khoản</a>
                </div>
                <p>Nếu bạn không đăng ký tài khoản, vui lòng bỏ qua email này.</p>
                <p>Trân trọng,<br>Shop Quản Lý Quần Áo</p>
            </div>
        """.formatted(verificationLink);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true để gửi HTML

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Gửi email xác minh thất bại: " + e.getMessage());
        }
    }
}
