package curso.java.tienda.controller.contacto;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Contacto.ContactoService;

/**
 * Servlet implementation class ContactoServlet
 */

@WebServlet("/Contacto")
public class ContactoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
		request.getRequestDispatcher(Rutas.CONTACTO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();

		String idioma = (String) session.getAttribute("idioma");

		if (idioma == null) {
			idioma = "es";
			session.setAttribute("idioma", idioma);
			logger.info("Idioma por defecto: " + idioma);
		
		} else {
			logger.info("Idioma: " + idioma);
		}

		ResourceBundle bundle = ResourceBundle.getBundle("messages", new Locale(idioma));
		session.setAttribute("bundle", bundle);
		
		String remitente = request.getParameter("emailContacto");
		String asunto = request.getParameter("asuntoContacto");
		String mensaje = request.getParameter("mensajeContacto");

		try {
			ContactoService.enviarEmail(asunto, mensaje);			
			request.setAttribute("mensajeExito", "Correo enviado correctamente");
			logger.info("Correo enviado correctamente");
		
		} catch (Exception e) {
			request.setAttribute("mensajeError", "Error al enviar el correo");
			logger.error("Error al enviar el correo", e);
		}
		
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher(Rutas.CONTACTO_JSP).forward(request, response);
	}

}
