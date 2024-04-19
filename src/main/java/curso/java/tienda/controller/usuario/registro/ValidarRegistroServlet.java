package curso.java.tienda.controller.usuario.registro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class ValidarRegistroServlet
 */

@WebServlet("/validarRegistro")
public class ValidarRegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarRegistroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String direccion = request.getParameter("direccion");
		String provincia = request.getParameter("provincia");
		String localidad = request.getParameter("localidad");
		String telefono = request.getParameter("telefono");
		String dni = request.getParameter("dni");
				
		HashMap<String, String> errores = new HashMap<String, String>();
		
		UsuarioVO usuario = UsuarioService.getUsuarioByEmail(email);
		
		if (nombre.equals("")) {
			errores.put("errorNombre", "Debe introducir un nombre");		
		} 
		
		if (email.equals("")) {
			errores.put("errorEmail", "Debe introducir un email");
		}
		
		if (usuario.getEmail() != null) {
			errores.put("errorEmail", "El email ya está registrado");
		}
		
		if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            errores.put("errorEmail", "El email no es válido");
        }
		
		if (password.equals("")) {
			errores.put("errorPassword", "Debe introducir una contraseña");
		}
		
		if (password.length() < 8) {
            errores.put("errorPassword", "La contraseña debe tener al menos 8 caracteres");
        }
		
		if (password2.equals("")) {
			errores.put("errorPassword2", "Debe repetir la contraseña");
		}
		
		if (!password.equals(password2)) {
			errores.put("errorPassword2", "Las contraseñas no coinciden");
		}
		
		if (apellido1.equals("")) {
            errores.put("errorApellido1", "Debe introducir un apellido");
        }
		
		if (direccion.equals("")) {
            errores.put("errorDireccion", "Debe introducir una dirección");
        }
		
		if (provincia.equals("")) {
            errores.put("errorProvincia", "Debe introducir una provincia");
        }
		
		if (localidad.equals("")) {
            errores.put("errorLocalidad", "Debe introducir una localidad");
        }
		
		if (telefono.equals("")) {
            errores.put("errorTelefono", "Debe introducir un teléfono");
        }
		
		if (dni.equals("")) {
            errores.put("errorDni", "Debe introducir un DNI");
        }
		
		if (dni.length() != 9) {
            errores.put("errorDni", "El DNI debe tener 9 caracteres");
        }
		
		if (!dni.matches("[0-9]{8}[A-Z]")) {
            errores.put("errorDni", "El DNI no es válido");
        }
		
		if (errores.isEmpty()) {
			
			UsuarioVO nuevoUsuario = new UsuarioVO(0, 0, email, password2, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
			
					
			if (UsuarioService.registrarUsuario(nuevoUsuario)) {
				request.setAttribute("mensaje", "Usuario registrado correctamente");
				
			} else {
				request.setAttribute("mensaje", "Error al registrar el usuario");
			}
				
			request.setAttribute("errores", errores);
			request.setAttribute("usuario", nuevoUsuario);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		
		} else {
			
			request.setAttribute("errores", errores);
			
		    request.getRequestDispatcher("registro.jsp").forward(request, response);
		}
	}

}
