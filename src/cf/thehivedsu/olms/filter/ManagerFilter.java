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
import cf.thehivedsu.olms.bean.RequestBean;

/**
 * Servlet Filter implementation class ManagerFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }, urlPatterns = { "/api/manager/*",
		"/manager/*" })
public class ManagerFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ManagerFilter() {
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
		RequestBean requestBean = (RequestBean) request.getAttribute("requestBean");
		if (requestBean.isManager()) {
			chain.doFilter(request, response);
			return;
		}

		request.getRequestDispatcher(URLConstants.MANAGER_AUTH_REQUIRED).forward(request, response);
		return;
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
