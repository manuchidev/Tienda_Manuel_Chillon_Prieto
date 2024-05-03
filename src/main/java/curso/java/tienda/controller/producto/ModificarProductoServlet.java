package curso.java.tienda.controller.producto;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import curso.java.tienda.controller.base.BaseServlet;

/**
 * Servlet implementation class ModificarProductoServlet
 */

@WebServlet("/modificarProducto")
public class ModificarProductoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarProductoServlet() {
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
	
		Part filePart = request.getPart("imagen"); // Recoge el archivo de imagen del formulario
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el nombre del archivo
	    
	    String nombre = request.getParameter("nombreProd");
	    String precio = request.getParameter("precioProd").replace("€", "").trim();
	    String descripcion = request.getParameter("descripcionProd");
	    String impuesto = request.getParameter("impuestoProd").replace("%", "").trim();
	    String stock = request.getParameter("stockProd");
	}

}
