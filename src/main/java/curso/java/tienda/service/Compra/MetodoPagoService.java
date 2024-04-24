package curso.java.tienda.service.Compra;

import java.util.List;

import curso.java.tienda.model.DAO.Compra.MetodoPagoDAO;
import curso.java.tienda.model.VO.Compra.MetodoPagoVO;

public class MetodoPagoService {
	
	public static List<MetodoPagoVO> getMetodosPago() {
		
		List<MetodoPagoVO> metodos_pago = MetodoPagoDAO.findAll();
		
		return metodos_pago;
	}
	
	public static MetodoPagoVO getMetodoPagoId(int id) {
		
		MetodoPagoVO metodo_pago = MetodoPagoDAO.findById(id);
		
		return metodo_pago;
	}

}
