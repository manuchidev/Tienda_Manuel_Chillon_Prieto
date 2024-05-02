package curso.java.tienda.controller.usuario.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/Login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();		
		request.setAttribute("categorias", categorias);
		
		request.getRequestDispatcher(Rutas.LOGIN_JSP).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();		
		List<ProductoVO> productos = ProductoService.getProductos();
				
		// Recuperar las categorias y productos
		request.setAttribute("categorias", categorias);
		request.setAttribute("productos", productos);

		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
						
		if (carrito != null) {
			request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		}
		
		String email = request.getParameter("emailLogin");
		String clave = request.getParameter("passwordLogin");
				
		if (email != null && !email.isEmpty() && clave != null && !clave.isEmpty()) {
			
			UsuarioVO usuario = UsuarioService.validarUsuario(email, clave);
			
			if (usuario != null) {				
				session.setAttribute("usuario", usuario);
				logger.info("Usuario " + email + " ha iniciado sesión");
				request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
				
			} else {
				logger.warn("Intento de inicio de sesión fallido con email " + email);
				request.getRequestDispatcher(Rutas.LOGIN_JSP).forward(request, response);
			}
		
		} else {
			request.getRequestDispatcher(Rutas.LOGIN_JSP).forward(request, response);
		}
				
	}

}
