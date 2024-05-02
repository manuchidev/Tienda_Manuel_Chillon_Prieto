package curso.java.tienda.service.Config;

import curso.java.tienda.model.DAO.Config.ConfigDAO;

public class ConfigService {
	
	public static int obtenerContador() {
		
		int contador = ConfigDAO.getValor();
		return contador;
	}
	
	
	public static void actualizarContador() {
		
		ConfigDAO.updateValor();
	}

}
