package cf.thehivedsu.olms.resource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MailSessionFactory {

	private static volatile Session sharedMailSession;

	private static void createSharedMailSession() {
		String jndiname = "mail/SystemMail";
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			sharedMailSession = (Session) envContext.lookup(jndiname);
		} catch (NamingException e) {
			// Handle error that it's not configured in JNDI.
			throw new RuntimeException(jndiname + " is missing in JNDI!", e);
		}
	}

	/**
	 * @return the sharedMailSession
	 */
	public static Session getSharedMailSession() {
		if (sharedMailSession == null) {
			synchronized (MailSessionFactory.class) {
				if (sharedMailSession == null) {
					createSharedMailSession();
				}
			}
		}
		return sharedMailSession;
	}

	public static void sendMailPlaintText(String[] receivers, String subject, String content)
			throws MessagingException {
		Session session = getSharedMailSession();

		Message message = new MimeMessage(session);
		InternetAddress to[] = new InternetAddress[receivers.length];
		for (int i = 0; i < receivers.length; i++) {
			to[i] = new InternetAddress(receivers[i]);
		}
		message.setRecipients(Message.RecipientType.TO, to);
		message.setSubject(subject);
		message.setContent(content, "text/plain");
		Transport.send(message);
	}

}
