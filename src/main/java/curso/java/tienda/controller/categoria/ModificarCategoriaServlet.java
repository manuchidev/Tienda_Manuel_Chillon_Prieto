package curso.java.tienda.controller.categoria;

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
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;

/**
 * Servlet implementation class ModificarCategoriaServlet
 */

@WebServlet("/ModificarCategoria")
public class ModificarCategoriaServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCategoriaServlet() {
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
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		
	 	String id = request.getParameter("idCat");
	    String nombre = request.getParameter("nombreCatModif");
	    String descripcion = request.getParameter("descripcionCatModif");

	    CategoriaVO categoria = new CategoriaVO();
            categoria.setId(Integer.parseInt(id));
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            
        CategoriaVO categoriaActualizada = CategoriaService.modificarCategoria(categoria);
	    request.setAttribute("categoria", categoriaActualizada);	   	    	    
	    	    
	    List<CategoriaVO> categorias = CategoriaService.getCategorias();					
			request.setAttribute("categorias", categorias);	      
	    
		if (usuario.esEmpleado()) {
			request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_JSP).forward(request, response);
		
		} else if (usuario.esAdmin()) {
			request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_ADMIN_JSP).forward(request, response);
		}
	}

}
