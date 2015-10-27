package cf.thehivedsu.olms.filter;

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
 * Servlet Filter implementation class EmployeeFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR }, asyncSupported = true, urlPatterns = { "/api/employee/*",
				"/employee/*", "/api/manager/*", "/manager/*" })
public class EmployeeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public EmployeeFilter() {
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

			if (sessionBean.getEmployeeID() > 0) {
				chain.doFilter(request, response);
				return;
			}
		}

		request.getRequestDispatcher(URLConstants.EMPLOYEE_AUTH_REQUIRED).forward(request, response);
		return;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
