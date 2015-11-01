package cf.thehivedsu.olms.test.mail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import cf.thehivedsu.olms.resource.mail.ApacheEmailFactory;

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
		try {
			String recipients[] = {"dsu@abhishekmunie.com"};
			ApacheEmailFactory.sendSimpleEmail(recipients, "Apache Mail Test", "Hello, this mail was sent using apache mail apis. :-)");
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
