package curso.java.tienda.controller.entrada;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.model.VO.ProductoVO;
import curso.java.tienda.service.ProductoService;

/**
 * Servlet implementation class EntradaServlet
 */
public class EntradaServlet extends HttpServlet {
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
		
		// Si no existe el carrito en la sesión, lo creamos
		if (request.getSession().getAttribute("carrito") == null) {
			request.getSession().setAttribute("carrito", new HashMap<ProductoVO, Integer>());
			System.out.println("Carrito disponible");
		}
		
		// Recuperar los productos
		request.setAttribute("catalogo", ProductoService.getProductos());
		
		// Redirigir a la pagina de inicio
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
