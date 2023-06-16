package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static void send(String from, String to, String subject, String content) {
 
        // 1. 발신자의 메일 계정과 비밀번호 설정
        final String user = "withsky999";
        final String password = "tjalsrn99!Q";
 
        // 2. Property에 SMTP 서버 정보 설정
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.naver.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 네이버인경우 (No appioriate... 에러)
 
        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        // debug mode
        session.setDebug(true);
 
        // 4. Message 클래스의 객체를 사용하여 수신자와 내용, 제목의 메시지를 작성한다.
        // 5. Transport 클래스를 사용하여 작성한 메세지를 전달한다.
 
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
 
            // 수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
 
            // Subject
            message.setSubject(subject);
 
            // Text
            //message.setText(content);
            // html로 메일 발송
            message.setContent(content, "text/html;charset=utf-8");
 
            Transport.send(message);    // send message
 
 
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
 
    }
	public static void main(String[] args) {
		send("withsky999@naver.com", "withsky999@gmail.com", "제목", "내용<b>안녕</b>내용");
	}
}
