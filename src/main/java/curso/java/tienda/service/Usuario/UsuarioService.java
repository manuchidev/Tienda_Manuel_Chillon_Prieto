package curso.java.tienda.service.Usuario;

import curso.java.tienda.model.DAO.Usuario.UsuarioDAO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;

public class UsuarioService {
	
	public static UsuarioVO getUsuario(int id) {
		
		UsuarioVO usuario = UsuarioDAO.findById(id);
		
		return usuario;
	}
	
	public static UsuarioVO getUsuarioByEmail(String email) {

		UsuarioVO usuario = UsuarioDAO.findByEmail(email);

		return usuario;
	}
	
	public static UsuarioVO validarUsuario(String email, String clave) {
		
		UsuarioVO usuario = UsuarioDAO.findByEmail(email);
		
		if (usuario != null && usuario.getClave().equals(clave)) {
			return usuario;
		}
		
		return null;
	}
	
	
	public static boolean registrarCliente(UsuarioVO usuario) {

		boolean registrado = UsuarioDAO.insertarCliente(usuario);

		return registrado;
	}

}
