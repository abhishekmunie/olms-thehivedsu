package cf.thehivedsu.olms.resource.email;

import org.apache.commons.mail.EmailException;

public class Email {

	public static void sendSimpleEmailInBackground(String recipients[], String subject, String message) {
		System.out.println("Will send email to <" + recipients + "> with message \"" + message + "\"");
		EmailSender sender = new EmailSender(recipients, subject, message);
		(new Thread(sender)).start();
	}

	public static void sendSimpleEmailInBackground(String recipient, String subject, String message) {
		String recipients[] = { recipient };
		sendSimpleEmailInBackground(recipients, subject, message);
	}
}

class EmailSender implements Runnable {

	private String recipients[];
	private String subject;
	private String message;

	/**
	 * @param recipients
	 * @param subject
	 * @param message
	 */
	public EmailSender(String[] recipients, String subject, String message) {
		super();
		this.recipients = recipients;
		this.subject = subject;
		this.message = message;
	}

	@Override
	public void run() {
		try {
			ApacheEmailFactory.sendSimpleEmail(recipients, subject, message);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
