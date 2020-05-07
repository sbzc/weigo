package com.weigo.mail.email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmail {
	public static void send_email(String toEmail,String subjects,String contents) throws IOException, AddressException, MessagingException{
        String to = toEmail;
        String subject = subjects;
        String content = contents;        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");   
        properties.setProperty("mail.smtp.ssl.enable", "true");
        Authenticator authenticator = new Email_Authenticator("1262681219@qq.com", "kzafhsilqktlhfja");
        javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
        MimeMessage mailMessage = new MimeMessage(sendMailSession);
        mailMessage.setFrom(new InternetAddress("1262681219@qq.com"));      
        // Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO
        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        mailMessage.setSubject(subject, "UTF-8");
        mailMessage.setSentDate(new Date());     
        // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���
        Multipart mainPart = new MimeMultipart();      
        // ����һ������HTML���ݵ�MimeBodyPart
        BodyPart html = new MimeBodyPart();
        html.setContent(content.trim(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        mailMessage.setContent(mainPart);
        Transport.send(mailMessage);
    }
}