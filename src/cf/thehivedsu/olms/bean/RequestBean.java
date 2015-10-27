package cf.thehivedsu.olms.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.manager.Manager;

public class RequestBean {

	private String originalURI;
	private String originalURL;
	private String originalQueryString;
	private boolean manager;

	/**
	 * 
	 */
	public RequestBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public RequestBean(HttpServletRequest request) {
		this.originalURI = request.getRequestURI();
		this.originalURL = request.getRequestURL().toString();
		this.originalQueryString = request.getQueryString();

		HttpSession session = request.getSession();
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		if (sessionBean.getEmployeeID() > 0) {
			this.manager = Manager.isManager(sessionBean.getEmployeeID());
		} else {
			this.manager = false;
		}
	}

	/**
	 * @return the originalURI
	 */
	public String getOriginalURI() {
		return originalURI;
	}

	/**
	 * @param originalURI
	 *            the originalURI to set
	 */
	public void setOriginalURI(String originalURI) {
		this.originalURI = originalURI;
	}

	/**
	 * @return the originalURL
	 */
	public String getOriginalURL() {
		return originalURL;
	}

	/**
	 * @param originalURL
	 *            the originalURL to set
	 */
	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	/**
	 * @return the originalQueryString
	 */
	public String getOriginalQueryString() {
		return originalQueryString;
	}

	/**
	 * @param originalQueryString
	 *            the originalQueryString to set
	 */
	public void setOriginalQueryString(String originalQueryString) {
		this.originalQueryString = originalQueryString;
	}

	/**
	 * 
	 * @return
	 */
	public String getHostRelativeURL() {
		String query = this.originalQueryString;
		return this.originalURI + ((query != null) ? "?" + query : "");
	}

	/**
	 * @return the manager
	 */
	public boolean isManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
	}

}
