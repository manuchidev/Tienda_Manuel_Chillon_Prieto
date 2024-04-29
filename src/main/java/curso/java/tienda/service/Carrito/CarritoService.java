package curso.java.tienda.service.Carrito;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Producto.ProductoService;

public class CarritoService {
	
	public static void agregarProducto(HashMap<ProductoVO, Integer> carrito, int id) {
		
		// Obtenemos el producto por su id
		ProductoVO producto = ProductoService.getProductoId(id);
		
		if (producto != null) {
			
			// Si el producto existe, comprobamos si ya está en el carrito
			if (carrito.containsKey(producto)) {
				
				// Si está en el carrito, aumentamos la cantidad
				carrito.put(producto, carrito.get(producto) + 1);
				
			} else {
				
				carrito.put(producto, 1);
			}	
		}
	}
	
	public static void agregarProductoCantidad(HashMap<ProductoVO, Integer> carrito, int id, int cantidad) {
		
		// Obtenemos el producto por su id
		ProductoVO producto = ProductoService.getProductoId(id);
		
		if (producto != null) {
			
			// Si el producto existe, comprobamos si ya está en el carrito
			if (carrito.containsKey(producto)) {
				
				// Si está en el carrito, aumentamos la cantidad
				carrito.put(producto, carrito.get(producto) + 1);
				
			} else {
				
				carrito.put(producto, cantidad);
			}	
		}
	}
	
	public static double calcularTotal(HashMap<ProductoVO, Integer> carrito) {
		
		double total = 0;
		
		if (carrito != null) {

			for (Map.Entry<ProductoVO, Integer> productoCarrito : carrito.entrySet()) {
				
				ProductoVO producto = productoCarrito.getKey();
				int cantidad = productoCarrito.getValue();
				
				total += producto.getPrecio() * cantidad;
			}			
		}
		
		// Redondeamos el total a dos decimales
		total = Math.round(total * 100.0) / 100.0;

		return total;
	}
	
	
	public static double calcularTotalIVA(double totalCarrito) {
		
		double IVA = 0.21;
		
		double totalCarritoIVA = (totalCarrito * IVA) + totalCarrito;
		
		// Redondeamos el total a dos decimales
		totalCarritoIVA = Math.round(totalCarritoIVA * 100.0) / 100.0;

		return totalCarritoIVA;
	}
	
	public static void eliminarProducto(HashMap<ProductoVO, Integer> carrito, int id) {
		
		if (carrito != null && !carrito.isEmpty()) {
			
			for (Map.Entry<ProductoVO, Integer> productoCarrito : carrito.entrySet()) {
				
				ProductoVO producto = productoCarrito.getKey();
				
				if (producto.getId() == id) {
					
					carrito.remove(producto);
					break;
				
				} else {
					System.out.println("No se ha encontrado el producto en el carrito");
				}									
			}
			
		}
	}
	
	public static void vaciarCarrito(HashMap<ProductoVO, Integer> carrito) {
		
		if (carrito != null && !carrito.isEmpty()) {
			carrito.clear();
		}
	}

}
