package curso.java.tienda.controller.producto;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

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

//		Part filePart = request.getPart("imagen"); // Recoge el archivo de imagen del formulario
//	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el nombre del archivo
	    
	    String id = request.getParameter("idProdModif");
	    String idCategoria = request.getParameter("categoriaProdModif");
	    String nombre = request.getParameter("nombreProdModif");
	    String precio = request.getParameter("precioProdModif").replaceAll("[^\\d.]", "").trim();
	    String descripcion = request.getParameter("descripcionProdModif");
	    String impuesto = request.getParameter("impuestoProdModif").replace("%", "").trim();
	    String stock = request.getParameter("stockProdModif");
	    String imagen = request.getParameter("imagenProdModif");
	   	    	    
	    ProductoVO producto = ProductoService.actualizarProducto(id, idCategoria, nombre, descripcion, precio, stock, impuesto, imagen);
	    	    
	    List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
		// Si existe un id, creamos el carrito. La primera vez se crea con una unidad del producto, pero posteriormente habrá que comprobar si el id del producto existe y si es así se aumenta la cantidad
		if (request.getParameter("idProdModif") != null && request.getParameter("idCatModif") != null) {
									
			producto = ProductoService.getProductoId(producto.getId());
			request.setAttribute("producto", producto);
			
			List<ProductoVO> productosCategoriaDetalles = ProductoService.getProductosCategoriaDetalles(producto.getId(), producto.getId_categoria());
			request.setAttribute("productosCategoriaDetalles", productosCategoriaDetalles);
		}
		
		request.setAttribute("categorias", categorias);
	    
	    request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_JSP).forward(request, response);
	}

}
