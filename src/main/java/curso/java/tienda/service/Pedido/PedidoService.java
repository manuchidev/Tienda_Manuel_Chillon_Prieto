package curso.java.tienda.service.Pedido;

import java.util.List;

import curso.java.tienda.model.DAO.Pedido.PedidoDAO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;

public class PedidoService {
	
	public static List<PedidoVO> getPedidos() {
		
		List<PedidoVO> detallesPedido = PedidoDAO.findAll();
		
		return detallesPedido;
	}
	
	
	public static PedidoVO getPedidoId(int id) {
		
		PedidoVO detallePedido = PedidoDAO.findById(id);
		
		return detallePedido;
	}

}
