package curso.java.tienda.service;

import java.util.List;

import curso.java.tienda.model.DAO.ProductoDAO;
import curso.java.tienda.model.VO.ProductoVO;

public class ProductoService {
	
	public static List<ProductoVO> getProductos() {
		
		List<ProductoVO> productos = ProductoDAO.findAll();
		
		return productos;
	}

}
