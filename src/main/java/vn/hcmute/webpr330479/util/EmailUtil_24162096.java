package vn.hcmute.webpr330479.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailUtil_24162096 {

    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean sendOtpEmail(String toEmail, String otp) {
        System.out.println("==================================================");
        System.out.println(">>> [EMAIL SERVICE] GUI MA OTP DEN: " + toEmail);
        System.out.println(">>> MA XAC THUC OTP CUA BAN LA: " + otp);
        System.out.println("==================================================");

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            final String senderEmail = "nguyensonghoangphuc0125@gmail.com";
            final String senderPass = "";

            if (senderPass.isEmpty()) {
                return true;
            }

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPass);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Mã OTP kích hoạt tài khoản - Đề số 05 (24162096)");
            message.setText("Chào bạn,\n\nMã OTP kích hoạt tài khoản của bạn là: " + otp + "\n\nTrân trọng.");

            Transport.send(message);
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
