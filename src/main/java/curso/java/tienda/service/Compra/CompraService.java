package curso.java.tienda.service.Compra;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.DetallePedido.DetallePedidoService;
import curso.java.tienda.service.Pedido.PedidoService;
import curso.java.tienda.service.Producto.ProductoService;


public class CompraService {
	
	public static HashMap<String, String> validarErrores(String nombre, String apellidos, String email, String direccion, String telefono, String provincia, String localidad, String cp, String metodo_pago, String nombre_titular, String iban, String expedicion, String cvv) {
		
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
		
		return errores;
	}
	
	public static boolean validarStock(HashMap<ProductoVO, Integer> carrito ) {
		
		boolean stockSuficiente = true;
		
		for (Map.Entry<ProductoVO, Integer> entry : carrito.entrySet()) {
			ProductoVO producto = entry.getKey();
			Integer cantidad = entry.getValue();
			
			if (ProductoService.obtenerStock(producto.getId()) < cantidad ) {
				stockSuficiente = false;
				break;
			}
		}
		
		return stockSuficiente;
	}
	
	public static void completarPedido(HashMap<ProductoVO, Integer> carrito, UsuarioVO usuario, String metodo_pago) {
		
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
	}

}
