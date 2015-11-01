package cf.thehivedsu.olms.resource.mail;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

public class ApacheEmailFactory {

	private static void configureEmail(Email email) throws EmailException {
		EmailConfigBean config = EmailConfigBean.getConfigFromEnvironmentVariables();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthentication(config.getUsername(), config.getPassword());
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		email.setSSLCheckServerIdentity(true);
		email.setSocketTimeout(5 * 60 * 1000);
		email.setFrom(config.getFrom(), "OLMS Bot");
		email.addReplyTo("admin@thehivedsu.cf");
	}

	public static SimpleEmail createSimpleEmail() throws EmailException {
		SimpleEmail email = new SimpleEmail();
		configureEmail(email);
		return email;
	}

	public static void sendSimpleEmail(String recipients[], String subject, String message) throws EmailException {
		SimpleEmail email = createSimpleEmail();
		email.setSubject(subject);
		email.setMsg(message);
		for (String to : recipients) {
			email.addTo(to);
		}
		email.send();
	}

	public static MultiPartEmail createMultiPartEmail() throws EmailException {
		MultiPartEmail email = new MultiPartEmail();
		configureEmail(email);

		return email;
	}

	public static HtmlEmail createHtmlEmail() throws EmailException {
		HtmlEmail email = new HtmlEmail();
		configureEmail(email);
		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		return email;
	}

	public static void sendHtmlEmail(String recipients[], String subject, String message) throws EmailException {
		HtmlEmail email = createHtmlEmail();
		email.setSubject(subject);
		email.setHtmlMsg(message);
		for (String to : recipients) {
			email.addTo(to);
		}
		email.send();
	}

	public static ImageHtmlEmail createImageHtmlEmaill() throws EmailException {
		ImageHtmlEmail email = new ImageHtmlEmail();
		configureEmail(email);
		// define you base URL to resolve relative resource locations
		URL url = null;
		try {
			url = new URL("https://olms.thehivedsu.cf");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");

		return email;
	}
}
