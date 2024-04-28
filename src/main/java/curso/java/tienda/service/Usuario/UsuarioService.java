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
	
	
	public static void registrarCliente(UsuarioVO usuario) {
					
		UsuarioDAO.insertarCliente(usuario);

	}
	
	public static void actualizarUsuario(UsuarioVO usuario, String nombre, String apellido1, String apellido2, String password, String telefono, String direccion, String provincia, String localidad) {
			
		usuario.setNombre(nombre);
		usuario.setApellido1(apellido1);
		usuario.setApellido2(apellido2);
		usuario.setClave(password);
		usuario.setTelefono(telefono);
		usuario.setDireccion(direccion);
		usuario.setProvincia(provincia);
		usuario.setLocalidad(localidad);
		
		UsuarioDAO.updateUsuario(usuario);
	}

}
