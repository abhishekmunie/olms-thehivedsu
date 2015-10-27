package cf.thehivedsu.olms.admin;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.bean.SessionBean;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR,
		DispatcherType.ASYNC }, asyncSupported = true, urlPatterns = { "/api/admin/*", "/admin/*" })
public class AdminFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AdminFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) request).getSession();
			SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");

			if (sessionBean.getEmployeeID() == 0) {
				chain.doFilter(request, response);
				return;
			}
		}

		request.getRequestDispatcher(URLConstants.ADMIN_AUTH_REQUIRED).forward(request, response);
		return;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
