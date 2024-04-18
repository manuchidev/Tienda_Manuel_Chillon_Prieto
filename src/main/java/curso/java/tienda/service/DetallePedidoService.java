package curso.java.tienda.service;

import java.util.List;

import curso.java.tienda.model.DAO.DetallePedidoDAO;
import curso.java.tienda.model.VO.DetallePedidoVO;

public class DetallePedidoService {
	
	public static List<DetallePedidoVO> getDetallesPedido() {
		
		List<DetallePedidoVO> detallesPedido = DetallePedidoDAO.findAll();
		
		return detallesPedido;
	}
	
	
	public static DetallePedidoVO getDetallePedidoId(int id) {
		
		DetallePedidoVO detallePedido = DetallePedidoDAO.findById(id);
		
		return detallePedido;
	}

}
