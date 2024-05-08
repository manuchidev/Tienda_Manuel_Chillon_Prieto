package curso.java.tienda.controller.administrador;

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
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class EmpleadoAdminServlet
 */

@WebServlet("/EmpleadoAdmin")
public class EmpleadoAdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadoAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		String accion = request.getParameter("accion");
		String id = request.getParameter("idEmpleado");
		
		switch (accion) {
		
			case "view":
				List<UsuarioVO> empleados = UsuarioService.getEmpleados();
				request.setAttribute("empleados", empleados);
				
				request.getRequestDispatcher(Rutas.EMPLEADOS_ADMIN_JSP).forward(request, response);
				break;
				
			case "add":
				request.getRequestDispatcher(Rutas.ALTA_EMPLEADO_ADMIN_JSP).forward(request, response);
				break;
				
			case "edit":				
				int idEmpleado = Integer.parseInt(id);
				
				UsuarioVO empleadoModif = UsuarioService.getUsuario(idEmpleado);
				request.setAttribute("empleadoModif", empleadoModif);

				request.getRequestDispatcher(Rutas.MODIFICAR_EMPLEADO_ADMIN_JSP).forward(request, response);
				break;
				
			case "delete":
				idEmpleado = Integer.parseInt(id);
				UsuarioService.bajaUsuario(idEmpleado);

				empleados = UsuarioService.getEmpleados();
				request.setAttribute("empleados", empleados);
				
				request.getRequestDispatcher(Rutas.EMPLEADOS_ADMIN_JSP).forward(request, response);
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		String accion = request.getParameter("accion");
		String id = request.getParameter("idUsuario");
		
		switch (accion) {
		
			case "add":
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
						
				HashMap<String, String> errores = UsuarioService.erroresAltaUsuario(email, nombre, apellido1, apellido2, password, password2, telefono, direccion, provincia, localidad, dni);
											
				if (errores.isEmpty()) {
					
					String passwordEncriptada = UsuarioService.encriptarClave(password);

					UsuarioVO nuevoUsuario = new UsuarioVO(email, passwordEncriptada, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
										
					UsuarioService.registrarEmpleado(nuevoUsuario);			
									
					limpiarAtributosRegistro(request);
						
					request.setAttribute("errores", errores);
					
					request.setAttribute("mensajeExito", "Empleado registrado correctamente");
					logger.info("Empleado registrado correctamente");

					request.getRequestDispatcher(Rutas.ALTA_EMPLEADO_ADMIN_JSP).forward(request, response);
				
				} else {
					
					guardarAtributosRegistro(request, email, password, password2, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni);
					
					request.setAttribute("errores", errores);
					
					request.setAttribute("mensajeError", "Error al registrar el empleado");
					logger.error("Error al registrar el empleado");
					
				    request.getRequestDispatcher(Rutas.ALTA_EMPLEADO_ADMIN_JSP).forward(request, response);
				}
				
			    break;
			   
		  case "guardarPerfil":
			  
			  UsuarioVO usuario = UsuarioService.getUsuario(Integer.parseInt(id));
	
				nombre = request.getParameter("nombrePerfil");
				apellido1 = request.getParameter("apellido1Perfil");
				apellido2 = request.getParameter("apellido2Perfil");		
				telefono = request.getParameter("telefonoPerfil");
				direccion = request.getParameter("direccionPerfil");
				provincia = request.getParameter("provinciaPerfil");
				localidad = request.getParameter("localidadPerfil");
				
				usuario.setNombre(nombre);
				usuario.setApellido1(apellido1);
				usuario.setApellido2(apellido2);
				usuario.setTelefono(telefono);
				usuario.setDireccion(direccion);
				usuario.setProvincia(provincia);
				usuario.setLocalidad(localidad);
				
				
				UsuarioVO usuarioActualizado = UsuarioService.actualizarUsuario(usuario);
				request.setAttribute("empleadoModif", usuarioActualizado);
				
				request.getRequestDispatcher(Rutas.MODIFICAR_EMPLEADO_ADMIN_JSP).forward(request, response);
				
			    break;
			    
		   case "cambioClave":
			   
			   usuario = UsuarioService.getUsuario(Integer.parseInt(id));
				
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
				
				usuario = UsuarioService.getUsuario(Integer.parseInt(id));
				request.setAttribute("empleadoModif", usuario);
				request.getRequestDispatcher(Rutas.MODIFICAR_EMPLEADO_ADMIN_JSP).forward(request, response);
				
				break;				
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
