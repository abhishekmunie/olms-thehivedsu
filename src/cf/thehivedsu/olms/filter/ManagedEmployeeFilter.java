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

import cf.thehivedsu.olms.URLConstants;
import cf.thehivedsu.olms.bean.EmployeeBean;

/**
 * Servlet Filter implementation class ManagedEmployeeFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE,
		DispatcherType.ERROR }, urlPatterns = { "/employe/new-applicatio/", "/employe/status/" })
public class ManagedEmployeeFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ManagedEmployeeFilter() {
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
		EmployeeBean employeeBean = (EmployeeBean) request.getAttribute("employeeBean");
		if (employeeBean.hasManager()) {
			chain.doFilter(request, response);
			return;
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
