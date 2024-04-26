package curso.java.tienda.controller.carrito;

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
import curso.java.tienda.service.Carrito.CarritoService;

/**
 * Servlet implementation class CantidadCarritoServlet
 */

@WebServlet("/actualizarCantidad")
public class CantidadCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CantidadCarritoServlet() {
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
		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");		
				
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		
		int idProducto = Integer.parseInt(request.getParameter("idProd"));
		
		for (ProductoVO producto : carrito.keySet()) {
			if (producto.getId() == idProducto) {
				carrito.put(producto, cantidad);
			}
		}
		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
						
		request.getRequestDispatcher(Rutas.CARRITO_JSP).forward(request, response);
	}

}
