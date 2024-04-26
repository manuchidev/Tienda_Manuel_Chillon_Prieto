package curso.java.tienda.service.Producto;

import java.util.List;

import curso.java.tienda.model.DAO.Producto.ProductoDAO;
import curso.java.tienda.model.VO.Producto.ProductoVO;

public class ProductoService {
	
	public static List<ProductoVO> getProductos() {
		
		List<ProductoVO> productos = ProductoDAO.findAll();
		
		return productos;
	}
	
	public static ProductoVO getProductoId(int id) {
		
		ProductoVO producto = ProductoDAO.findById(id);
		
		return producto;
	}
	
	public static List<ProductoVO> getProductosCategoria(int id_producto, int id_categoria) {
		
		List<ProductoVO> productos = ProductoDAO.findByIdCategoria(id_producto, id_categoria);
		
		return productos;
	}
	
	public static int obtenerStock(int id) {
		
		int stock = ProductoDAO.findStock(id);
		
		return stock;
	}
	
	public static void reducirStock(int id, int cantidad) {
		
		ProductoDAO.updateStock(id, cantidad);
	}

}
