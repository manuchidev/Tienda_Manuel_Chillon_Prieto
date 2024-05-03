package curso.java.tienda.controller.empleado;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;
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
			UsuarioVO usuarioModif = UsuarioService.getUsuario(idUsuario);
			request.setAttribute("usuarioModif", usuarioModif);
			
			request.getRequestDispatcher(Rutas.MODIFICAR_CLIENTE_JSP).forward(request, response);
			return;
		
		} else if ("delete".equals(accion)) {
//			ClienteService.eliminarCliente(request);
			request.getRequestDispatcher(Rutas.CLIENTES_EMPLEADO_JSP).forward(request, response);
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
		
		UsuarioVO usuario = UsuarioService.getUsuario(Integer.parseInt(id));
		System.out.println("Usuario antes de la actualización: " + usuario);
		
		if ("add".equals(accion)) {
			

		} else if ("guardarPerfil".equals(accion)) {

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

}
