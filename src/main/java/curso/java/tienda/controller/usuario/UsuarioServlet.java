package curso.java.tienda.controller.usuario;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class UsuarioServlet
 */

@WebServlet("/Usuario")
public class UsuarioServlet extends BaseServlet {
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
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();		
		List<ProductoVO> productos = ProductoService.getProductos();
				
		// Recuperar las categorias y productos
		request.setAttribute("categorias", categorias);
		request.setAttribute("productos", productos);
		
		if ("perfil".equals(accion)) {			
			request.getRequestDispatcher(Rutas.PERFIL_JSP).forward(request, response);
		
		} else if ("pedidos".equals(accion)){
			response.sendRedirect("Pedidos");
			// request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
		
		} else if ("cerrarSesion".equals(accion)){
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/");
			// request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
		}
	}

}
