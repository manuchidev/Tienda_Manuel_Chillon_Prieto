package curso.java.tienda.service.Pedido;

import java.util.List;

import curso.java.tienda.model.DAO.Pedido.PedidoDAO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;

public class PedidoService {
	
	public static PedidoVO getPedido(int id) {

		PedidoVO pedido = PedidoDAO.findById(id);

		return pedido;
	}
	
	
	public static List<PedidoVO> getPedidos() {
		
		List<PedidoVO> detallesPedido = PedidoDAO.findAll();
		
		return detallesPedido;
	}
	
		
	public static int realizarPedido(PedidoVO pedido) {
		
		int idPedido = PedidoDAO.insert(pedido);
		
		return idPedido;
	}
	
	public static List<PedidoVO> getPedidoUsuarioASC(int id) {

		List<PedidoVO> pedidos = PedidoDAO.findByIdUsuarioASC(id);

		return pedidos;
	}
	
	public static List<PedidoVO> getPedidoUsuarioDESC(int id) {
		
		List<PedidoVO> pedidos = PedidoDAO.findByIdUsuarioDESC(id);
		
		return pedidos;
	}
	
	public static void cambiarEstado(int id, String estado) {
		
		PedidoDAO.updateEstado(id, estado);

	}

}
