package curso.java.tienda.controller.usuario.registro;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
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
				
		HashMap<String, String> errores = UsuarioService.erroresRegistro(email, nombre, apellido1, apellido2, password, password2, telefono, direccion, provincia, localidad, dni, terminos);
									
		if (errores.isEmpty()) {
			
			String passwordEncriptada = UsuarioService.encriptarClave(password);

			UsuarioVO nuevoUsuario = new UsuarioVO(0, 0, email, passwordEncriptada, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
								
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
