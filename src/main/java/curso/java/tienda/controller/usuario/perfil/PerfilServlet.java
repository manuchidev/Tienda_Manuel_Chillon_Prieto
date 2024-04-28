package curso.java.tienda.controller.usuario.perfil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class PerfilServlet
 */

@WebServlet("/Perfil")
public class PerfilServlet extends HttpServlet {
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
		
		String nombre = request.getParameter("nombrePerfil");
		String apellido1 = request.getParameter("apellido1Perfil");
		String apellido2 = request.getParameter("apellido2Perfil");
		String password = request.getParameter("passwordPerfil");
		
		if (password == null || password.isEmpty()) {
			password = usuario.getClave();
		}
		
		String telefono = request.getParameter("telefonoPerfil");
		String direccion = request.getParameter("direccionPerfil");
		String provincia = request.getParameter("provinciaPerfil");
		String localidad = request.getParameter("localidadPerfil");

		UsuarioService.actualizarUsuario(usuario, nombre, apellido1, apellido2, password, telefono, direccion, provincia, localidad);
		
		request.getSession().setAttribute("usuario", usuario);
		
		request.getRequestDispatcher(Rutas.PERFIL_JSP).forward(request, response);
	}

}
