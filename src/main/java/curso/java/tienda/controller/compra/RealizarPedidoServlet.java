package curso.java.tienda.controller.compra;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Compra.CompraService;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.DetallePedido.DetallePedidoService;
import curso.java.tienda.service.Pedido.PedidoService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class PagarServlet
 */

@WebServlet("/pedido")
public class RealizarPedidoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);

		UsuarioVO usuario = (UsuarioVO)session.getAttribute("usuario");
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		request.setAttribute("totalCarritoIVA", CarritoService.calcularTotalIVA(CarritoService.calcularTotal(carrito)));
	
		if (usuario != null) {
			            
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			String provincia = request.getParameter("provincia");
			String localidad = request.getParameter("localidad");
			String cp = request.getParameter("cp");		
					
			String metodo_pago = request.getParameter("metodo_pago");
			String nombre_titular = request.getParameter("nombre_titular");
			String iban = request.getParameter("iban");
			String expedicion = request.getParameter("expedicion");
			String cvv = request.getParameter("cvv");
			
			HashMap<String, String> errores = new HashMap<String, String>();
			
			errores = CompraService.validarErrores(nombre, apellidos, email, direccion, telefono, provincia, localidad, cp, metodo_pago, nombre_titular, iban, expedicion, cvv); 
			
			if (errores.isEmpty()) {
				
				request.setAttribute("errores", errores);

				boolean stockSuficiente = CompraService.validarStock(carrito);

				if (stockSuficiente) {
					logger.info("Stock suficiente");
					CompraService.completarPedido(carrito, usuario, metodo_pago);
																							
					request.getRequestDispatcher(Rutas.RESULTADO_JSP).forward(request, response);
					
					// Borrar los datos del carrito
					session.removeAttribute("carrito");
				
				} else {
					logger.error("Stock insuficiente");
					request.setAttribute("mensajeError", "Stock insuficiente");
					request.getRequestDispatcher(Rutas.COMPRA_JSP).forward(request, response);
				}												
				
			} else {
				request.setAttribute("errores", errores);
				request.getRequestDispatcher(Rutas.COMPRA_JSP).forward(request, response);
			}
		
		} else {
			request.getRequestDispatcher(Rutas.LOGIN_JSP).forward(request, response);
		}

	}

}
