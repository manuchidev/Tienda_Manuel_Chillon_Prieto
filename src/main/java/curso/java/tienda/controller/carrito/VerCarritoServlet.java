package curso.java.tienda.controller.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.ProductoVO;

/**
 * Servlet implementation class VerCarritoServlet
 */

@WebServlet("/Carrito")
public class VerCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");
		
		List<ProductoVO> productosCarrito = new ArrayList<>();
		
		if (carrito != null && !carrito.isEmpty()) {
			
			for (Map.Entry<ProductoVO, Integer> producto: carrito.entrySet()) {
				
				ProductoVO productoVO = producto.getKey();
				Integer cantidad = producto.getValue();
				
				productoVO.setCantidad(cantidad);
								
				productosCarrito.add(productoVO);
			}
		}
		
		// Si no existe el carrito en la sesión, lo creamos
		if (request.getSession().getAttribute("productosCarrito") == null) {
			request.getSession().setAttribute("productosCarrito", productosCarrito);
		}
		
		request.getRequestDispatcher(Rutas.CARRITO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
