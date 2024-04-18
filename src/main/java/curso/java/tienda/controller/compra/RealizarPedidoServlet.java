package curso.java.tienda.controller.compra;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PagarServlet
 */

@WebServlet("/pedido")
public class RealizarPedidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RealizarPedidoServlet() {
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
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String direccion = request.getParameter("direccion");
		String direccion2 = request.getParameter("direccion2");
		String zona = request.getParameter("zona");
		String comunidad = request.getParameter("comunidad");
		String cp = request.getParameter("cp");
		
		String metodo_pago = request.getParameter("metodo_pago");
		String nombre_titular = request.getParameter("nombre_titular");
		String iban = request.getParameter("iban");
		String expedicion = request.getParameter("expedicion");
		String cvv = request.getParameter("cvv");
		
		HashMap<String, String> errores = new HashMap<String, String>();
		
		if (nombre.equals("")) {
			errores.put("errorNombre", "Debe introducir un nombre");		
		} 
		
		if (apellidos.equals("")) {
			errores.put("errorApellidos", "Debe introducir un apellidos");
		}
		
		if (email.equals("")) {
			errores.put("errorEmail", "Debe introducir un email");
		}
		
		if (direccion.equals("")) {
			errores.put("errorDireccion", "Debe introducir una direccion");
		}
		
		if (zona.equals("")) {
			errores.put("errorZona", "Debe introducir una zona");
		}
		
		if (comunidad.equals("")) {
			errores.put("errorComunidad", "Debe introducir una comunidad");
		}
		
		if (cp.equals("")) {
			errores.put("errorCp", "Debe introducir un codigo postal");
		}
		
		if (metodo_pago.equals("")) {
			errores.put("errorMetodoPago", "Debe introducir un metodo de pago");
		}
		
		if (nombre_titular.equals("")) {
			errores.put("errorNombreTitular", "Debe introducir un nombre de titular");
		}
		
		if (iban.equals("")) {
			errores.put("errorIban", "Debe introducir un iban");
		}
		
		if (expedicion.equals("")) {
			errores.put("errorExpedicion", "Debe introducir una fecha de expedicion");
		}
		
		if (cvv.equals("")) {
			errores.put("errorCvv", "Debe introducir un cvv");
		}
	}

}
