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
import curso.java.tienda.service.Categoria.CategoriaService;

/**
 * Servlet implementation class CategoriaEmpleadoServlet
 */

@WebServlet("/CategoriaEmpleado")
public class CategoriaEmpleadoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		String id = request.getParameter("idCat");

        if ("view".equals(accion)) {
        	List<CategoriaVO> categorias = CategoriaService.getCategorias();
        	request.setAttribute("categorias", categorias);
            request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
            return;
        
        } else if ("add".equals(accion)) {
            request.getRequestDispatcher(Rutas.ALTA_CATEGORIA_JSP).forward(request, response);
            return;
        
        } else if ("edit".equals(accion)) {
        	Integer idCategoria = Integer.parseInt(id);
        	CategoriaVO categoria = CategoriaService.getCategoria(idCategoria);
        	request.setAttribute("categoria", categoria);
            request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_JSP).forward(request, response);
            return;
        
        } else if ("delete".equals(accion)) {
//			CategoriService.eliminarCategoria(request);
        	request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
            return;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		
		String id = request.getParameter("idCatModif");

		
		if ("add".equals(accion)) {
			String nombre = request.getParameter("nombreCat");
			String descripcion = request.getParameter("descripcionCat");
//			CategoriaService.altaCategoria(request);
			request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
			return;

		} else if ("edit".equals(accion)) {
			String nombre = request.getParameter("nombreCatModif");
			String descripcion = request.getParameter("descripcionCatModif");
			Integer idCategoria = Integer.parseInt(id);
//			CategoriaService.modificarCategoria(idCategoria, nombre, descripcion);
			request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
			return;
		
		} else if ("delete".equals(accion)) {
			Integer idCategoria = Integer.parseInt(id);
//			CategoriaService.eliminarCategoria(idCategoria);
			request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
			return;
		}
		
	}

}
