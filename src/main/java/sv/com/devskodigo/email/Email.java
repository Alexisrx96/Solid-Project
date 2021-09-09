package sv.com.devskodigo.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;

public class Email {
    private Address[] to;
    private final Multipart content;
    private final MimeMessage message;
    public Email(Session session) {
        message = new MimeMessage(session);
        content = new MimeMultipart();
    }

    public void setTo( List<String> emailAddressList) throws MessagingException {
        to = new Address[emailAddressList.size()];
        for (int i = 0; i < emailAddressList.size(); i++) {
            to[i] = new InternetAddress(emailAddressList.get(i));
        }
        message.addRecipients(Message.RecipientType.TO, to);
    }

    public void setFrom(String from) throws MessagingException{
        message.setFrom(new InternetAddress(from));
    }

    public void setSubject(String subject)throws MessagingException {
        message.setSubject(subject);
    }

    public void  addTextMessage(String text) throws MessagingException{
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text);
        content.addBodyPart(messageBodyPart);
    }

    public void  addAttachFile(String filename) throws MessagingException{
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        content.addBodyPart(messageBodyPart);
    }
    public MimeMessage getMessage() throws MessagingException{
        message.setContent(content);
        return message;
    }
}
