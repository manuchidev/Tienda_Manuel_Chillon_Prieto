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

}
