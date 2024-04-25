package curso.java.tienda.service.Config;

import curso.java.tienda.model.DAO.Config.ConfigDAO;

public class ConfigService {
	
	public static void actualizarNumFactur() {
		
		ConfigDAO.updateFactura();
	}

}
