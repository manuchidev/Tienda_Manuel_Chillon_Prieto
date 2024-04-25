package curso.java.tienda.controller.compra;

import java.io.IOException;
import java.sql.Date;
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
import curso.java.tienda.model.VO.Compra.MetodoPagoVO;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.DetallePedido.DetallePedidoService;
import curso.java.tienda.service.Pedido.PedidoService;
import curso.java.tienda.service.Producto.ProductoService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class PagarServlet
 */

@WebServlet("/pedido")
public class RealizarPedidoServlet extends HttpServlet {
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

		UsuarioVO usuario = (UsuarioVO)session.getAttribute("usuario");
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
	
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
			
			if (nombre.equals("")) {
				errores.put("errorNombre", "Debe introducir un nombre");		
			} 
			
			if (apellidos.equals("")) {
				errores.put("errorApellidos", "Debe introducir un apellidos");
			}
			
			if (email.equals("")) {
				errores.put("errorEmail", "Debe introducir un email");
			}
			
			if (direccion.equals("")) {
				errores.put("errorDireccion", "Debe introducir una direccion");
			}
			
			if (telefono.equals("")) {
				errores.put("errorTelefono", "Debe introducir un telefono");
			}
			
			if (provincia.equals("")) {
				errores.put("errorProvincia", "Debe introducir una comunidad");
			}
			
			if (localidad.equals("")) {
				errores.put("errorLocalidad", "Debe introducir una zona");
			}
			
			
			if (cp.equals("")) {
				errores.put("errorCp", "Debe introducir un codigo postal");
			}
			
			if (metodo_pago.equals("")) {
				errores.put("errorMetodoPago", "Debe introducir un metodo de pago");
			}
			
			if (nombre_titular.equals("")) {
				errores.put("errorNombreTitular", "Debe introducir un nombre de titular");
			}
			
			if (iban.equals("")) {
				errores.put("errorIban", "Debe introducir un iban");
			}
			
			if (expedicion.equals("")) {
				errores.put("errorExpedicion", "Debe introducir una fecha de expedicion");
			}
			
			if (cvv.equals("")) {
				errores.put("errorCvv", "Debe introducir un cvv");
			}
			
			if (errores.isEmpty()) {
				
				request.setAttribute("errores", errores);

				boolean stockSuficiente = true;

				for (Map.Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
					ProductoVO producto = entry.getKey();
					Integer cantidad = entry.getValue();
					
					if (ProductoService.obtenerStock(producto.getId()) < cantidad ) {
						stockSuficiente = false;
						break;
					}
				}

				if (stockSuficiente) {
					
					PedidoVO pedido = new PedidoVO(usuario.getId(), Timestamp.valueOf(LocalDateTime.now()), metodo_pago);								
					
					int idPedido = PedidoService.realizarPedido(pedido);
						
					for (Map.Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
						ProductoVO producto = entry.getKey();
						Integer cantidad = entry.getValue();
						
						System.out.println("Producto:" + producto.toString());
	
						int id_producto = producto.getId();
						float precio_unidad = (float)producto.getPrecio();
						float impuesto = producto.getImpuesto();
						double totalSinImpuesto = (precio_unidad * cantidad);
						double total = totalSinImpuesto + (totalSinImpuesto * impuesto);
						
						System.out.println("Impuesto: " + impuesto);
						
						DetallePedidoVO detallePedido = new DetallePedidoVO(idPedido, id_producto, precio_unidad, cantidad, impuesto, total);
						DetallePedidoService.realizarDetallePedido(detallePedido);
						
						ProductoService.reducirStock(id_producto, cantidad);												
					}
					
										
					request.getRequestDispatcher(Rutas.RESULTADO_JSP).forward(request, response);
				
				} else {
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
