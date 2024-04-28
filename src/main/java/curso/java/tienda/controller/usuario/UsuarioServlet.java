package curso.java.tienda.controller.usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;

/**
 * Servlet implementation class UsuarioServlet
 */

@WebServlet("/Usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		if ("perfil".equals(accion)) {			
			request.getRequestDispatcher(Rutas.PERFIL_JSP).forward(request, response);
		
		} else if ("pedidos".equals(accion)){
			response.sendRedirect("Pedidos");
			// request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
		
		} else if ("cerrarSesion".equals(accion)){
			request.getSession().invalidate();
			request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
		
		}
	}

}
