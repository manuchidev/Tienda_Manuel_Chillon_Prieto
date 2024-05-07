package curso.java.tienda.service.Producto;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.Part;

import curso.java.tienda.model.DAO.Producto.ProductoDAO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;

public class ProductoService {
	
	public static List<ProductoVO> getProductos() {
		
		List<ProductoVO> productos = ProductoDAO.findAll();
		
		return productos;
	}
	
	public static ProductoVO getProductoId(int id) {
		
		ProductoVO producto = ProductoDAO.findById(id);
		
		return producto;
	}
	
	public static List<ProductoVO> getProductosCategoria(int id_categoria) {
		
		List<ProductoVO> productos = ProductoDAO.findByIdCategoria(id_categoria);
		
		return productos;
	}
	
	public static List<ProductoVO> getProductosFiltrados(String precio, String categoria) {
		
		// Si no se ha seleccionado ninguna categoria ni precio
		if (categoria.equals("Todas") && precio.equals("Todos")) {
			List<ProductoVO> productos = ProductoDAO.findAll();
			return productos;
		
		// Si se ha seleccionado un precio, pero no una categoria
		} else if (categoria.equals("Todas") && !precio.equals("Todos")) {
			
			switch (precio) { 
			
				case "min":
					List<ProductoVO> productosMin = ProductoDAO.findByMinPrecio();
					return productosMin;
					
				case "max":
					List<ProductoVO> productosMax = ProductoDAO.findByMaxPrecio();
					return productosMax;					
					
				default:
					int precioInt = Integer.parseInt(precio);
					List<ProductoVO> productosPrecio = ProductoDAO.findByPrecio(precioInt);
					return productosPrecio;
			}

		// Si se ha seleccionado una categoria, pero no un precio
		} else if (precio.equals("Todos") && !categoria.equals("Todas")) {
			int idCategoria = Integer.parseInt(categoria);
			List<ProductoVO> productosCategoria = ProductoDAO.findByIdCategoria(idCategoria);
			return productosCategoria;

		// Si se ha seleccionado una categoria y un precio
		} else {
			
			int idCategoria = Integer.parseInt(categoria);
			
			switch (precio) { 
			
				case "max":
					List<ProductoVO> productosMax = ProductoDAO.findByMaxPrecioCategoria(idCategoria);
					return productosMax;
					
				case "min":
					List<ProductoVO> productosMin = ProductoDAO.findByMinPrecioCategoria(idCategoria);
					return productosMin;
					
				default:
					BigDecimal precioBD = BigDecimal.valueOf(Double.parseDouble(precio));
					List<ProductoVO> productosPrecio = ProductoDAO.findByPrecioCategoria(precioBD, idCategoria);
					return productosPrecio;
			}
		}
		
	}
	
	public static List<ProductoVO> getProductosCategoriaDetalles(int id_producto, int id_categoria) {
		
		List<ProductoVO> productos = ProductoDAO.findByIdCategoriaDetalles(id_producto, id_categoria);
		
		return productos;
	}
	
	public static void altaProducto(ProductoVO producto) {

		ProductoDAO.insertProducto(producto);
	}
	
	public static ProductoVO actualizarProducto(String id, String idCategoria, String nombre, String descripcion, String precio, String stock, String impuesto, String imagen) {
		
		int idProducto = Integer.parseInt(id);
		int idCategoriaInt = Integer.parseInt(idCategoria);
		BigDecimal precioBD = BigDecimal.valueOf(Double.parseDouble(precio));
		int stockInt = Integer.parseInt(stock);
		BigDecimal impuestoBD = BigDecimal.valueOf(Double.parseDouble(impuesto));
		
		ProductoVO producto = ProductoDAO.updateProducto(idProducto, idCategoriaInt, nombre, descripcion, precioBD, stockInt, impuestoBD, imagen);
		
		return producto;		
	}
	
	public static void bajaProducto(int id) {

		ProductoDAO.deleteProducto(id);
	}
	
	public static int obtenerStock(int id) {
		
		int stock = ProductoDAO.findStock(id);
		
		return stock;
	}
	
	public static void reducirStock(int id, int cantidad) {
		
		ProductoDAO.updateStock(id, cantidad);
	}
	
}
