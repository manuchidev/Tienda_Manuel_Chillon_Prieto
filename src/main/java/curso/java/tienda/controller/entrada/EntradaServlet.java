package curso.java.tienda.controller.entrada;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class EntradaServlet
 */
@WebServlet("")
public class EntradaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public EntradaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session = request.getSession(true);
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);

		String idioma = (String) session.getAttribute("idioma");

		if (idioma == null) {
			idioma = "es";
			session.setAttribute("idioma", idioma);
			logger.info("Idioma por defecto: " + idioma);
		
		} else {
			logger.info("Idioma: " + idioma);
		}

		ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(idioma));
		session.setAttribute("bundle", bundle);

		// Si no existe el carrito en la sesión, lo creamos
		if (session.getAttribute("carrito") == null) {
			session.setAttribute("carrito", new HashMap<ProductoVO, Integer>());
			logger.info("Carrito disponible");
		}
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();		
		List<ProductoVO> productos = ProductoService.getProductos();
				
		// Recuperar las categorias y productos
		request.setAttribute("categorias", categorias);
		request.setAttribute("productos", productos);
			
		// Redirigir a la pagina de inicio
		request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
