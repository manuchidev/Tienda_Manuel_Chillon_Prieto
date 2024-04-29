package curso.java.tienda.service.Usuario;

import java.util.Base64;
import java.util.HashMap;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

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
	
	public static UsuarioVO validarUsuario(String email, String password) {
		
		UsuarioVO usuario = UsuarioDAO.findByEmail(email);
		
		if (compararClave(usuario, password)) {
			return usuario;
		}
		
		return null;
	}
	
	public static HashMap<String, String> erroresRegistro(String email, String nombre, String apellido1, String apellido2, String password, String password2, String telefono, String direccion, String provincia, String localidad, String dni, String terminos) {
		
		HashMap<String, String> errores = new HashMap<String, String>();
		
		String emailExp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
		String passwordExp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
		String cadenaExp = "^[a-zA-Z]+$";
		String telefonoExp = "^[0-9]{9}$";
		String dniExp = "^[0-9]{8}[A-Z]$";
		
		boolean emailRegistrado = UsuarioService.getUsuarioByEmail(email) != null;
		
		if (email.equals("")) {
			errores.put("errorEmail", "Debe introducir un email");
		
		} else if (!email.matches(emailExp)) {
            errores.put("errorEmail", "El email no es válido");
            
		} else if (emailRegistrado) {
			errores.put("errorEmail", "El email ya está registrado");
		}		
				
		if (password.equals("")) {
			errores.put("errorPassword", "Debe introducir una contraseña");
			
		} else if (!password.matches(passwordExp)) {
			errores.put("errorPassword", "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número");
		}
		
		if (password2.equals("")) {
			errores.put("errorPassword2", "Debe repetir la contraseña");
			
		} else if (!password.equals(password2)) {
			errores.put("errorPassword2", "Las contraseñas no coinciden");
		}
		
		if (nombre.equals("")) {
			errores.put("errorNombre", "Debe introducir un nombre");	
			
		} else if (!nombre.matches(cadenaExp)) {
			errores.put("errorNombre", "El nombre no puede contener números ni caracteres especiales");
		}
		
		if (apellido1.equals("")) {
            errores.put("errorApellido1", "Debe introducir su primer apellido");
            
		} else if (!apellido1.matches(cadenaExp)) {
			errores.put("errorApellido1", "El apellido no puede contener números ni caracteres especiales");
		}
		
		if (apellido2.equals("")) {
			errores.put("errorApellido2", "Debe introducir su segundo apellido");
			
		} else if (!apellido2.matches(cadenaExp)) {
			errores.put("errorApellido2", "El apellido no puede contener números ni caracteres especiales");
		}
		
		if (direccion.equals("")) {
            errores.put("errorDireccion", "Debe introducir una dirección");
        }
		
		if (provincia.equals("")) {
            errores.put("errorProvincia", "Debe introducir una provincia");
            
		} else if (!provincia.matches(cadenaExp)) {
			errores.put("errorProvincia", "La provincia no puede contener números ni caracteres especiales");
		}
		
		if (localidad.equals("")) {
            errores.put("errorLocalidad", "Debe introducir una localidad");
            
		} else if (!localidad.matches(cadenaExp)) {
			errores.put("errorLocalidad", "La localidad no puede contener números ni caracteres especiales");
		}
		
		if (telefono.equals("")) {
            errores.put("errorTelefono", "Debe introducir un teléfono");
            
		} else if (!telefono.matches(telefonoExp)) {
			errores.put("errorTelefono", "El teléfono no es válido");
		}
		
		if (dni.equals("")) {
            errores.put("errorDni", "Debe introducir un DNI"); 
            
        } else if (!dni.matches(dniExp)) {
            errores.put("errorDni", "El DNI no es válido");
        }
		
		if (terminos == null || !terminos.equals("aceptado")) {
			errores.put("errorTerminos", "Debe aceptar los términos y condiciones");
		}
		
		return errores;

	}
	
	public static String encriptarClave(String password) {
		
		StrongPasswordEncryptor clave = new StrongPasswordEncryptor();
				
		String claveEncriptada = clave.encryptPassword(password);
		System.out.println("Clave encriptada: " + claveEncriptada);
		
		return claveEncriptada;
	}
	
	public static boolean compararClave(UsuarioVO usuario, String password) {
		
		StrongPasswordEncryptor clave = new StrongPasswordEncryptor();	
		
		if (usuario != null && clave.checkPassword(password, usuario.getClave())) {
			return true;
		}
		
		return false;
	}
		
	
	public static void registrarCliente(UsuarioVO usuario) {
					
		UsuarioDAO.insertarCliente(usuario);
	}
	
	public static void actualizarUsuario(UsuarioVO usuario, String nombre, String apellido1, String apellido2, String telefono, String direccion, String provincia, String localidad) {
			
		usuario.setNombre(nombre);
		usuario.setApellido1(apellido1);
		usuario.setApellido2(apellido2);
		usuario.setTelefono(telefono);
		usuario.setDireccion(direccion);
		usuario.setProvincia(provincia);
		usuario.setLocalidad(localidad);
		
		UsuarioDAO.updateUsuario(usuario);
	}

	public static void actualizarClave(UsuarioVO usuario, String claveNueva) {
		
		String claveEncriptada = encriptarClave(claveNueva);
		
		UsuarioDAO.updateClaveUsuario(usuario, claveEncriptada);
	}

}
