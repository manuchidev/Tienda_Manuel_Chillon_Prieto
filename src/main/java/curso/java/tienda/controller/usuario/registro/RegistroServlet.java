package curso.java.tienda.controller.usuario.registro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class RegistroServlet
 */

@WebServlet("/Registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(Rutas.REGISTRO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("emailRegistro");
		String password = request.getParameter("passwordRegistro");
		String password2 = request.getParameter("password2Registro");
		String nombre = request.getParameter("nombreRegistro");
		String apellido1 = request.getParameter("apellido1Registro");
		String apellido2 = request.getParameter("apellido2Registro");
		String direccion = request.getParameter("direccionRegistro");
		String provincia = request.getParameter("provinciaRegistro");
		String localidad = request.getParameter("localidadRegistro");
		String telefono = request.getParameter("telefonoRegistro");
		String dni = request.getParameter("dniRegistro");
		String terminos = request.getParameter("terminosRegistro");
				
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
		
		
		if (errores.isEmpty()) {

			UsuarioVO nuevoUsuario = new UsuarioVO(0, 0, email, password, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
								
			UsuarioService.registrarCliente(nuevoUsuario);
			request.getSession().setAttribute("usuario", nuevoUsuario);
							
			limpiarAtributosRegistro(request);
				
			request.setAttribute("errores", errores);

			request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);
		
		} else {
			
			guardarAtributosRegistro(request, email, password, password2, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni, terminos);
			
			request.setAttribute("errores", errores);
			
		    request.getRequestDispatcher(Rutas.REGISTRO_JSP).forward(request, response);
		}
				
	}
	
	private void limpiarAtributosRegistro(HttpServletRequest request) {
		
		request.setAttribute("emailRegistro", "");
		request.setAttribute("passwordRegistro", "");
		request.setAttribute("password2Registro", "");
		request.setAttribute("nombreRegistro", "");
		request.setAttribute("apellido1Registro", "");
		request.setAttribute("apellido2Registro", "");
		request.setAttribute("direccionRegistro", "");
		request.setAttribute("provinciaRegistro", "");
		request.setAttribute("localidadRegistro", "");
		request.setAttribute("telefonoRegistro", "");
		request.setAttribute("dniRegistro", "");
		request.setAttribute("terminosRegistro", "");
	}

	private void guardarAtributosRegistro(HttpServletRequest request, String email, String password, String password2, String nombre,
			String apellido1, String apellido2, String direccion, String provincia, String localidad, String telefono,
			String dni, String terminos) {

		request.setAttribute("emailRegistro", email);
		request.setAttribute("passwordRegistro", password);
		request.setAttribute("password2Registro", password2);
		request.setAttribute("nombreRegistro", nombre);
		request.setAttribute("apellido1Registro", apellido1);
		request.setAttribute("apellido2Registro", apellido2);
		request.setAttribute("direccionRegistro", direccion);
		request.setAttribute("provinciaRegistro", provincia);
		request.setAttribute("localidadRegistro", localidad);
		request.setAttribute("telefonoRegistro", telefono);
		request.setAttribute("dniRegistro", dni);
		request.setAttribute("terminosRegistro", terminos);
	}
}
