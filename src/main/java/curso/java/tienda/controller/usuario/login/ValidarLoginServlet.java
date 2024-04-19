package curso.java.tienda.controller.usuario.login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/Login")
public class ValidarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarLoginServlet() {
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
		
		HttpSession session = request.getSession(true);
		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		
		String email = request.getParameter("email");
		String clave = request.getParameter("clave");
		
		UsuarioVO usuario = UsuarioService.validarUsuario(email, clave);
		
		if (usuario != null) {
			
			session.setAttribute("usuario", usuario);
			
			request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
		
		} else {

			response.sendRedirect(Rutas.LOGIN_JSP);
		}
	}

}