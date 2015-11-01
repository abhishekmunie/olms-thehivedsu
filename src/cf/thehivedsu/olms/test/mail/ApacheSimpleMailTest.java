package cf.thehivedsu.olms.test.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import cf.thehivedsu.olms.resource.mail.EmailConfigBean;

/**
 * Servlet implementation class ApacheSimpleMailTest
 */
@WebServlet("/_test/mail/ApacheSimpleMail")
public class ApacheSimpleMailTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApacheSimpleMailTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmailConfigBean config = EmailConfigBean.getConfigFromEnvironmentVariables();

		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(config.getUsername(), config.getPassword()));
		email.setSSLCheckServerIdentity(true);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		email.setSocketTimeout(5 * 60 * 1000);
		try {
			email.setFrom(config.getFrom(), "OLMS Bot");
			email.addReplyTo("admin@thehivedsu.cf");
			email.addTo("dsu@abhishekmunie.com");
			email.setSubject("Apache Mail Test");
			email.setMsg("Hello, this mail was sent using apache mail apis. :-)");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
