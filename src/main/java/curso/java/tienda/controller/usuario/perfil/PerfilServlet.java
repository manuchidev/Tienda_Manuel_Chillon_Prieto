package curso.java.tienda.controller.usuario.perfil;

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
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
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
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
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
			
			usuario.setNombre(nombre);
			usuario.setApellido1(apellido1);
			usuario.setApellido2(apellido2);
			usuario.setTelefono(telefono);
			usuario.setDireccion(direccion);
			usuario.setProvincia(provincia);
			usuario.setLocalidad(localidad);
						
			UsuarioVO usuarioActualizado = UsuarioService.actualizarUsuario(usuario);
						
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
		
		request.getAttribute("categorias");
		request.getSession().setAttribute("usuario", usuario);
		request.getRequestDispatcher(Rutas.PERFIL_JSP).forward(request, response);
	}

}
