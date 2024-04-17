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
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.ProductoVO;
import curso.java.tienda.service.ProductoService;

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
		
		HttpSession session = request.getSession(true);
		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
						
		if (carrito != null && !carrito.isEmpty()) {
			
			// Creamos un mapa de productos con la cantidad de cada uno
			Map<Integer, Integer> cantidadProducto = new HashMap<>();
			
			for (Map.Entry<ProductoVO, Integer> producto: carrito.entrySet()) {
				
				ProductoVO productoVO = producto.getKey();
				int cantidad = producto.getValue();
				int idProducto = productoVO.getId();
				
				if (cantidadProducto.containsKey(idProducto)) {
					
					int cantidadActual = cantidadProducto.get(idProducto);
					cantidadProducto.put(idProducto, cantidadActual + cantidad);
				
				} else {
					cantidadProducto.put(idProducto, cantidad);
				}
										
			}
			
			List<ProductoVO> productosCarrito = new ArrayList<>();
			
			for (Map.Entry<Integer, Integer> producto: cantidadProducto.entrySet()) {
				
				int idProducto = producto.getKey();
				int cantidad = producto.getValue();
				
				ProductoVO productoLista = ProductoService.getProductoId(idProducto);
				
				if (productoLista != null) {
					
					productoLista.setCantidad(cantidad);
					productoLista.setTotal(cantidad * productoLista.getPrecio());
					
					productosCarrito.add(productoLista);
				}
			}
			
			request.setAttribute("productosCarrito", productosCarrito);
		
		} else {
			request.removeAttribute("productosCarrito");
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
