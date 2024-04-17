package curso.java.tienda.service;

import curso.java.tienda.model.DAO.UsuarioDAO;
import curso.java.tienda.model.VO.UsuarioVO;

public class UsuarioService {
	
	public static UsuarioVO getUsuario(int id) {
		
		UsuarioVO usuario = UsuarioDAO.findById(id);
		
		return usuario;
	}

}
