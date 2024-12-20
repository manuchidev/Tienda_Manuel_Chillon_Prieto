package curso.java.tienda.controller.carrito;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class AñadirCarritoServlet
 */

@WebServlet("/añadirCarrito")
public class AñadirCarritoServlet extends BaseServlet {
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
			
			// Obtenemos el carrito de la sesión
			HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
			
			// Si no existe el carrito en la sesión, lo creamos
			if (carrito == null) {
				carrito = new HashMap<>();
				request.getSession().setAttribute("carrito", carrito);
			}	
			
			if (request.getParameter("cantidad") != null) {
				
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				
				CarritoService.agregarProductoCantidad(carrito, id, cantidad);
				
			} else {
				CarritoService.agregarProducto(carrito, id);			
			}			
		}
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		List<ProductoVO> productos = ProductoService.getProductos();	
		
		request.setAttribute("categorias", categorias);
		request.setAttribute("productos", productos);
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
				
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
