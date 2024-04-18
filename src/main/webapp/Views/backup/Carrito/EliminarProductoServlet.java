package curso.java.tienda.controller.carrito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.ProductoVO;


/**
 * Servlet implementation class EliminarProductoServlet
 */
@WebServlet("/eliminar")
public class EliminarProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProductoServlet() {
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
		
		HttpSession session = request.getSession(true);
		
		if (request.getParameter("idProd") != null) {
			
			int idProd = Integer.parseInt(request.getParameter("idProd"));
			
			// Obtenemos los productos del carrito de la sesión
			HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
									
			if (carrito != null && !carrito.isEmpty()) {
				
				ProductoVO productoEliminar = null;
				
				for (Map.Entry<ProductoVO, Integer> productoCarrito: carrito.entrySet()) {
					
					ProductoVO producto = productoCarrito.getKey();
					
					if (producto.getId() == idProd) {
                        productoEliminar = producto;
						break;
					}
				}
				
				if (productoEliminar != null) {
					carrito.remove(productoEliminar);
					session.setAttribute("carrito", carrito);
					
				}								
			}			
		}
		
		response.sendRedirect(request.getContextPath() + "/Carrito");
	}

}
