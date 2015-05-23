import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailObserver implements Observer {
	String from = "rekha.anem12@gmail.com";
	final String username = "rekha.anem12@gmail.com";
	final String password = "*********";
	String to = "rekha.anem12@gmail.com";
	Properties properties = new Properties();
	ObservableURL urlconnection = null;

	// Get the default Session object.
	Session session = Session.getInstance(properties,
			new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}});

	MailObserver(ObservableURL ov) {
		this.urlconnection = ov;
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}

	public void update(Observable obs, Object arg) {
		if (obs == urlconnection) {
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				message.setSubject("Web Page Modified");
				message.setText(urlconnection.getValue());
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}
}
