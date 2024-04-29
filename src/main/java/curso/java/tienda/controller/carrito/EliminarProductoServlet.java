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
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Producto.ProductoService;


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
		
		// Obtenemos el carrito de la sesión
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		request.setAttribute("totalCarritoIVA", CarritoService.calcularTotalIVA(CarritoService.calcularTotal(carrito)));
		
		// Si existe un id, creamos el carrito. La primera vez se crea con una unidad del producto, pero posteriormente habrá que comprobar si el id del producto existe y si es así se aumenta la cantidad
		if (request.getParameter("idProd") != null) {
			
			int id = Integer.parseInt(request.getParameter("idProd"));
						
			CarritoService.eliminarProducto(carrito, id);
						
			System.out.println(carrito.size());
		}
		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		request.getRequestDispatcher(Rutas.CARRITO_JSP).forward(request, response);
	}

}
