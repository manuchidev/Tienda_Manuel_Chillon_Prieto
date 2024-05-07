package curso.java.tienda.controller.administrador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.service.Categoria.CategoriaService;

/**
 * Servlet implementation class CategoriaAdminServlet
 */

@WebServlet("/CategoriaAdmin")
public class CategoriaAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		
		switch (accion) {
		
			case "view":
				List<CategoriaVO> categorias = CategoriaService.getCategorias();
				request.setAttribute("categorias", categorias);
				
				request.getRequestDispatcher(Rutas.CATEGORIAS_ADMIN_JSP).forward(request, response);
				break;
				
			case "add":				
				request.getRequestDispatcher(Rutas.ALTA_CATEGORIA_ADMIN_JSP).forward(request, response);
				break;
				
			case "edit":
				String id = request.getParameter("idCat");
				Integer idCategoria = Integer.parseInt(id);
				
				CategoriaVO categoria = CategoriaService.getCategoria(idCategoria);				
				request.setAttribute("categoria", categoria);
				
				request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_ADMIN_JSP).forward(request, response);
				break;
				
			case "delete":
				String idDelete = request.getParameter("id");
				int idCategoriaDelete = Integer.parseInt(idDelete);
				
				CategoriaService.bajaCategoria(idCategoriaDelete);
				
				request.getRequestDispatcher(Rutas.CATEGORIAS_ADMIN_JSP).forward(request, response);
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		switch (accion) {
		
			case "add":
	        	String nombre = request.getParameter("nombreCatAlta");
	        	String descripcion = request.getParameter("descripcionCatAlta");

	        	
	        	CategoriaVO nuevaCategoria = new CategoriaVO();
		        	nuevaCategoria.setNombre(nombre);
		        	nuevaCategoria.setDescripcion(descripcion);
		        	
		        CategoriaService.altaCategoria(nuevaCategoria);
		        	
				request.getRequestDispatcher(Rutas.ALTA_CATEGORIA_ADMIN_JSP).forward(request, response);
				break;
			
			case "edit":
		      	String id = request.getParameter("idCatModif");
	        	Integer idCategoria = Integer.parseInt(id);
	        	
				String nombreModif = request.getParameter("nombreCatModif");
				String descripcionModif = request.getParameter("descripcionCatModif");
				
				CategoriaVO categoriaActualizada = new CategoriaVO();
					categoriaActualizada.setId(idCategoria);
					categoriaActualizada.setNombre(nombreModif);
					categoriaActualizada.setDescripcion(descripcionModif);

				CategoriaService.modificarCategoria(categoriaActualizada);
				
				CategoriaVO categoria = CategoriaService.getCategoria(categoriaActualizada.getId());
	        	request.setAttribute("categoria", categoria);
				
				request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_JSP).forward(request, response);
				break;
				
			case "delete":
				String idCat = request.getParameter("idCat");
				Integer idCategoriaDelete = Integer.parseInt(idCat);
				
				CategoriaService.bajaCategoria(idCategoriaDelete);
				
				request.getRequestDispatcher(Rutas.CATEGORIAS_ADMIN_JSP).forward(request, response);
				break;
				
			default:
				break;
				
		}
	}

}
