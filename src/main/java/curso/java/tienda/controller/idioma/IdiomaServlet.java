package curso.java.tienda.controller.idioma;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Locale;

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
 * Servlet implementation class IdiomaServlet
 */

@WebServlet("/CambiarIdioma")
public class IdiomaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdiomaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();		

		String idioma = request.getParameter("idioma");		

		System.out.println("Idioma: " + idioma);

		session.setAttribute("idioma", idioma);

		ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(idioma));
		session.setAttribute("bundle", bundle);
		
	
		List<CategoriaVO> categorias = CategoriaService.getCategorias();		
		List<ProductoVO> productos = ProductoService.getProductos();
				
		// Recuperar las categorias y productos
		request.setAttribute("categorias", categorias);
		request.setAttribute("productos", productos);
		
		request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
	}

}
