package mx.bancosabadell.utils.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPMail {

	public void send(String from, String to, String subject, String body, String host) 
			throws AddressException, MessagingException {

		Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties); // default session
        MimeMessage message = new MimeMessage(session); // email message

        message.setFrom(new InternetAddress(from)); // setting header fields
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject); // subject line
        
        // actual mail body
        message.setText(body);
        
        // Send message
        Transport.send(message);          
	}
}
