package curso.java.tienda.controller.detalles;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class DetallesServlet
 */
@WebServlet("/detallesEmpleado")
public class DetallesEmpleadoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallesEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
		// Si existe un id, creamos el carrito. La primera vez se crea con una unidad del producto, pero posteriormente habrá que comprobar si el id del producto existe y si es así se aumenta la cantidad
		if (request.getParameter("idProd") != null && request.getParameter("idCat") != null) {
			
			int idProd = Integer.parseInt(request.getParameter("idProd"));
			int idCat = Integer.parseInt(request.getParameter("idCat"));
						
			ProductoVO producto = ProductoService.getProductoId(idProd);
			request.setAttribute("producto", producto);
			
			List<ProductoVO> productosCategoriaDetalles = ProductoService.getProductosCategoriaDetalles(idProd, idCat);
			request.setAttribute("productosCategoriaDetalles", productosCategoriaDetalles);
		}
		
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher(Rutas.DETALLES_EMPLEADO_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
