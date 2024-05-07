package curso.java.tienda.controller.empleado;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class ClienteEmpleadoServlet
 */

@WebServlet("/ClienteEmpleado")
public class ClienteEmpleadoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		String id = request.getParameter("idUsuario");
		
		System.out.println("accion: " + accion);
		System.out.println("id: " + id);

		if ("view".equals(accion)) {
			List<UsuarioVO> usuarios = UsuarioService.getUsuarios();
			request.setAttribute("usuarios", usuarios);
			
			request.getRequestDispatcher(Rutas.CLIENTES_EMPLEADO_JSP).forward(request, response);
			return;
		
		} else if ("add".equals(accion)) {			
			request.getRequestDispatcher(Rutas.ALTA_CLIENTE_JSP).forward(request, response);
			return;
		
		} else if ("edit".equals(accion)) {
			int idUsuario = Integer.parseInt(id);
			System.out.println("idUsuario: " + idUsuario);
			UsuarioVO usuarioModif = UsuarioService.getUsuario(idUsuario);
			
			request.setAttribute("usuarioModif", usuarioModif);
			
			request.getRequestDispatcher(Rutas.MODIFICAR_CLIENTE_JSP).forward(request, response);
			return;
		
		} else {
			// request.getRequestDispatcher(Rutas.ERROR_JSP).forward(request, response);
			return;
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		Part filePart = request.getPart("imagen"); // Recoge el archivo de imagen del formulario
//	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el nombre del archivo
	    
		String accion = request.getParameter("accion");
		String id = request.getParameter("idUsuario");

		if ("add".equals(accion)) {
						
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
					
			HashMap<String, String> errores = UsuarioService.erroresAltaCliente(email, nombre, apellido1, apellido2, password, password2, telefono, direccion, provincia, localidad, dni);
										
			if (errores.isEmpty()) {
				
				String passwordEncriptada = UsuarioService.encriptarClave(password);

				UsuarioVO nuevoUsuario = new UsuarioVO(email, passwordEncriptada, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
									
				UsuarioService.registrarCliente(nuevoUsuario);			
								
				limpiarAtributosRegistro(request);
					
				request.setAttribute("errores", errores);
				
				request.setAttribute("mensajeExito", "Cliente registrado correctamente");
				logger.info("Cliente registrado correctamente");

				request.getRequestDispatcher(Rutas.ALTA_CLIENTE_JSP).forward(request, response);
			
			} else {
				
				guardarAtributosRegistro(request, email, password, password2, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
				
				request.setAttribute("errores", errores);
				
				request.setAttribute("mensajeError", "Error al registrar el cliente");
				logger.error("Error al registrar el cliente");
				
			    request.getRequestDispatcher(Rutas.ALTA_CLIENTE_JSP).forward(request, response);
			}
			

		} else if ("guardarPerfil".equals(accion)) {
			
			UsuarioVO usuario = UsuarioService.getUsuario(Integer.parseInt(id));

			String nombre = request.getParameter("nombrePerfil");
			String apellido1 = request.getParameter("apellido1Perfil");
			String apellido2 = request.getParameter("apellido2Perfil");		
			String telefono = request.getParameter("telefonoPerfil");
			String direccion = request.getParameter("direccionPerfil");
			String provincia = request.getParameter("provinciaPerfil");
			String localidad = request.getParameter("localidadPerfil");
			
			UsuarioService.actualizarUsuario(usuario, nombre, apellido1, apellido2, telefono, direccion, provincia, localidad);
			request.setAttribute("usuarioModif", usuario);
			request.getRequestDispatcher(Rutas.MODIFICAR_CLIENTE_JSP).forward(request, response);
							
		} else if ("cambioClave".equals(accion)) {
			
			UsuarioVO usuario = UsuarioService.getUsuario(Integer.parseInt(id));
				
			String claveActual = request.getParameter("claveActual");
			String claveNueva = request.getParameter("claveNueva");
			
			if (claveActual != null && !claveActual.isEmpty()) {
								
				if (UsuarioService.compararClave(usuario, claveActual)) {
					
					if (claveNueva != null && !claveNueva.isEmpty()) {		
						System.out.println("claveNueva: " + claveNueva);
						UsuarioService.actualizarClave(usuario, claveNueva);
					}
				}
			}
			
			request.setAttribute("usuarioModif", usuario);
			request.getRequestDispatcher(Rutas.MODIFICAR_CLIENTE_JSP).forward(request, response);
	    	
	    } else if ("delete".equals(accion)) {
	    	
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
	}

	private void guardarAtributosRegistro(HttpServletRequest request, String email, String password, String password2, String nombre,
			String apellido1, String apellido2, String direccion, String provincia, String localidad, String telefono, String dni) {

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
	}
	
}
