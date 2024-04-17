//package curso.java.tienda.controller.usuario.login;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpFilter;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
//
///**
// * Servlet Filter implementation class LoginFilter
// */
//public class LoginFilter extends HttpFilter implements Filter {
//       
//    /**
//     * @see HttpFilter#HttpFilter()
//     */
//    public LoginFilter() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		
////		System.out.println("Procesando la solicitud");
////		
////		HttpServletRequest httpRequest = (HttpServletRequest) request;
////		HttpSession session = httpRequest.getSession();
////		
////		if (session.getAttribute("usuario") != null) {
////			((HttpServletResponse) response).sendRedirect("login.jsp");
////			return;
////		}
////
////		// pass the request along the filter chain
////		chain.doFilter(request, response);
////		
////		System.out.println("Saliendo del filtro");
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
