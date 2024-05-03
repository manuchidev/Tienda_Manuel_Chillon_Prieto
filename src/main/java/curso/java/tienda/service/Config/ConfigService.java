package curso.java.tienda.service.Config;

import java.util.List;

import curso.java.tienda.model.DAO.Config.ConfigDAO;
import curso.java.tienda.model.VO.Config.ConfigVO;

public class ConfigService {
	
	public static List<ConfigVO> obtenerDatosEmpresa() {

		List<ConfigVO> datos = ConfigDAO.getDatosEmpresa();
		
		return datos;
	}
	
	public static int obtenerContador() {
		
		int contador = ConfigDAO.getValor();
		return contador;
	}
	
	
	public static void actualizarContador() {
		
		ConfigDAO.updateValor();
	}

}
