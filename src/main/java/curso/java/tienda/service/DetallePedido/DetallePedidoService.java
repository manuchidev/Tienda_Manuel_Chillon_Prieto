package curso.java.tienda.service.DetallePedido;

import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.model.DAO.DetallePedido.DetallePedidoDAO;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;

public class DetallePedidoService {
	
	public static List<DetallePedidoVO> getDetallesPedido() {
		
		List<DetallePedidoVO> detallesPedido = DetallePedidoDAO.findAll();
		
		return detallesPedido;
	}
	
	
	public static DetallePedidoVO getDetallePedidoId(int id) {
		
		DetallePedidoVO detallePedido = DetallePedidoDAO.findById(id);
		
		return detallePedido;
	}
	
	public static List<DetallePedidoVO> getDetallesPedidoIdPedido(int id) {
		
		List<DetallePedidoVO> detallesPedido = DetallePedidoDAO.findByIdPedido(id);
		
		return detallesPedido;
	}
	
	public static void realizarDetallePedido(DetallePedidoVO detallePedido) {
						
		DetallePedidoDAO.insert(detallePedido);		
	}
	
	public static List<ProductoVO> getProductosDetallePedido(int idPedido) {

		List<ProductoVO> productos = new ArrayList<ProductoVO>();

		productos = DetallePedidoDAO.findProducto(idPedido);

		return productos;
	}
	
}
