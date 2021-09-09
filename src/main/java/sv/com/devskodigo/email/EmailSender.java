package sv.com.devskodigo.email;


import lombok.Getter;

import java.util.*;
import javax.mail.*;

public class EmailSender extends PrivateEmailInformation{
    @Getter
    private final String ownEmail;
    private final Properties properties;
    private final String host;

    public EmailSender() {
        // Sender's email ID needs to be mentioned
        ownEmail = "myfakeemailfortesting@gmail.com";
        // Assuming you are sending email from localhost
        host = "smtp.gmail.com";
        // Get system properties
        properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    public Session getSession() {
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ownEmail, PASSWORD);
            }
        });
    }

    public static void main(String[] args) {
        // Recipient's email ID needs to be mentioned.
        EmailSender emailSender = new EmailSender();
        Session session = emailSender.getSession();
        try {
            Email email = new Email(session);
            email.setFrom(emailSender.ownEmail);
            List<String> emailList =new ArrayList<>();
            emailList.add("irvinrx1996@hotmail.com");
            emailList.add("irvinrexx@gmail.com");
            email.setTo(emailList);
            email.addTextMessage("it's an example");
            email.setSubject("Testing");
            email.addAttachFile("ReportDetails.xlsx");

            // Send message
            Transport.send(email.getMessage());
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}