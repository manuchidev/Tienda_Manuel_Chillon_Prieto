package curso.java.tienda.controller.carrito;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.ProductoVO;
import curso.java.tienda.service.ProductoService;

/**
 * Servlet implementation class AñadirCarritoServlet
 */

@WebServlet("/añadirCarrito")
public class AñadirCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AñadirCarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		// Si existe un id, creamos el carrito. La primera vez se crea con una unidad del producto, pero posteriormente habrá que comprobar si el id del producto existe y si es así se aumenta la cantidad
		if (request.getParameter("idProd") != null) {
			
			int id = Integer.parseInt(request.getParameter("idProd"));
			
			// Contador para la cantidad de productos
			int contador = 1;
			
			// Obtenemos el carrito de la sesión
			HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
			
			// Si no existe el carrito en la sesión, lo creamos
			if (carrito == null) {
				carrito = new HashMap<>();
				request.getSession().setAttribute("carrito", carrito);
			}
			
			// Obtenemos el producto por su id
			ProductoVO producto = ProductoService.getProductoId(id);
			
			if (producto != null) {
				
				// Si el producto existe, comprobamos si ya está en el carrito
				if (carrito.containsKey(producto)) {
					
					// Si está en el carrito, aumentamos la cantidad
					contador += carrito.get(producto);
				}
				
				carrito.put(producto, contador);
			}
			
//			System.out.println(carrito.size());
		}
		
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
