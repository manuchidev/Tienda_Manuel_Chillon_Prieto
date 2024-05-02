package curso.java.tienda.controller.producto;

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
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class ProductoServlet
 */

@WebServlet("/productos")
public class ProductoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String precio = request.getParameter("precio");
		String categoria = request.getParameter("categoria");
						
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
		if (!categoria.equals("Todas")) {
			Integer idCategoria = Integer.parseInt(categoria);
			
			request.setAttribute("categoria", idCategoria);
		}
		
		if ( (!precio.equals("Todos") && !categoria.equals("Todas")) || (!precio.equals("Todos") && categoria.equals("Todas")) 
				|| (precio.equals("Todos") && !categoria.equals("Todas")) ){
			
			List<ProductoVO> productosFiltrados = ProductoService.getProductosFiltrados(precio, categoria);		
			
			if (productosFiltrados.size() == 0) {
				logger.error("No hay productos con esas características");
				request.setAttribute("mensajeError", "No hay productos con esas características");
			}
			
			request.setAttribute("productosFiltrados", productosFiltrados);	
		
		} else {
			List<ProductoVO> productos = ProductoService.getProductos();
			request.setAttribute("productos", productos);
		}
						
		request.getRequestDispatcher(Rutas.INDEX_JSP).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
