package curso.java.tienda.controller.usuario.perfil;

import java.io.IOException;
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
 * Servlet implementation class PerfilServlet
 */

@WebServlet("/Perfil")
public class PerfilServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfilServlet() {
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
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		String accion = request.getParameter("accion");	
		System.out.println("accion: " + accion);
		
		if ("guardarPerfil".equals(accion)) {
			String nombre = request.getParameter("nombrePerfil");
			String apellido1 = request.getParameter("apellido1Perfil");
			String apellido2 = request.getParameter("apellido2Perfil");		
			String telefono = request.getParameter("telefonoPerfil");
			String direccion = request.getParameter("direccionPerfil");
			String provincia = request.getParameter("provinciaPerfil");
			String localidad = request.getParameter("localidadPerfil");
			
			UsuarioService.actualizarUsuario(usuario, nombre, apellido1, apellido2, telefono, direccion, provincia, localidad);
						
		} else if ("cambioClave".equals(accion)) {
			
			String claveActual = request.getParameter("claveActual");
			String claveNueva = request.getParameter("claveNueva");
			
			if (claveActual != null && !claveActual.isEmpty()) {
				
				System.out.println("claveActual: " + claveActual);
								
				if (UsuarioService.compararClave(usuario, claveActual)) {
					
					System.out.println("claveActual: " + claveActual);
					
					if (claveNueva != null && !claveNueva.isEmpty()) {		
						System.out.println("claveNueva: " + claveNueva);
						UsuarioService.actualizarClave(usuario, claveNueva);
					}
				}
			}
		}
				
		request.getSession().setAttribute("usuario", usuario);
		request.getRequestDispatcher(Rutas.PERFIL_JSP).forward(request, response);
	}

}
