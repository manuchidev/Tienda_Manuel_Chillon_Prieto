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
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class ProductoEmpleadoServlet
 */

@WebServlet("/ProductoEmpleado")
public class ProductoEmpleadoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
		String idProd = request.getParameter("idProd");
	    String idCat = request.getParameter("idCat");
	    
	    int idProducto = 0;
	    int idCategoria = 0;

		if (idProd != null ) {
			idProducto = Integer.parseInt(idProd);
		}

		if (idCat != null ) {
			idCategoria = Integer.parseInt(idCat);
		}
	    
	    List<ProductoVO> productos = ProductoService.getProductos();
	    List<CategoriaVO> categorias = CategoriaService.getCategorias();

	    request.setAttribute("productos", productos);	
	    request.setAttribute("categorias", categorias);

		if ("view".equals(accion)) {
			request.getRequestDispatcher(Rutas.PRODUCTOS_EMPLEADO_JSP).forward(request, response);
			return;
		
		} else if ("add".equals(accion)) {
			request.getRequestDispatcher(Rutas.ALTA_PRODUCTO_JSP).forward(request, response);
			return;
		
		} else if ("edit".equals(accion)) {
			ProductoVO producto = ProductoService.getProductoId(idProducto);			
			request.setAttribute("producto", producto);
			request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_JSP).forward(request, response);
			return;
		
		} else if ("delete".equals(accion)) {
//			ProductoService.eliminarProducto(request);
			request.getRequestDispatcher(Rutas.PRODUCTOS_EMPLEADO_JSP).forward(request, response);
			return;
		
		} else {
			// request.getRequestDispatcher(Rutas.ERROR_JSP).forward(request, response);
			return;
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
//		Part filePart = request.getPart("imagen"); // Recoge el archivo de imagen del formulario
//	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el nombre del archivo
	    
		String accion = request.getParameter("accion");
		
		if ("add".equals(accion)) {
			
			String idCategoria = request.getParameter("categoriaProd");
            String nombre = request.getParameter("nombreProd");
            String precio = request.getParameter("precioProd").replaceAll("[^\\d.]", "").trim();
            String descripcion = request.getParameter("descripcionProd");
            String impuesto = request.getParameter("impuestoProd").replace("%", "").trim();
            String stock = request.getParameter("stockProd");
            String imagen = request.getParameter("imagenProd");
            
//          ProductoVO productoAlta = ProductoService.altaProducto(idCategoria, nombre, descripcion, precio, stock, impuesto, imagen);
//          request.setAttribute("productoAlta", productoAlta);
            request.getRequestDispatcher(Rutas.PRODUCTOS_EMPLEADO_JSP).forward(request, response);
            
	   	    	    
		} else if ("edit".equals(accion)) {
	    	
	    	String idProd = request.getParameter("idProdModif");
	    	String idCategoria = request.getParameter("categoriaProdModif");
	    	String nombre = request.getParameter("nombreProdModif");
	    	String precio = request.getParameter("precioProdModif").replaceAll("[^\\d.]", "").trim();
	    	String descripcion = request.getParameter("descripcionProdModif");
	    	String impuesto = request.getParameter("impuestoProdModif").replace("%", "").trim();
	    	String stock = request.getParameter("stockProdModif");
	    	String imagen = request.getParameter("imagenProdModif");
	    	
	    	ProductoVO producto = ProductoService.actualizarProducto(idProd, idCategoria, nombre, descripcion, precio, stock, impuesto, imagen);	    	    
	    	List<CategoriaVO> categorias = CategoriaService.getCategorias();
	    	
	    	if (idProd != null && idCategoria != null) {
	    		
	    		producto = ProductoService.getProductoId(producto.getId());
	    		request.setAttribute("producto", producto);
	    		
	    		List<ProductoVO> productosCategoriaDetalles = ProductoService.getProductosCategoriaDetalles(producto.getId(), producto.getId_categoria());
	    		request.setAttribute("productosCategoriaDetalles", productosCategoriaDetalles);
	    	}
	    	
	    	request.setAttribute("categorias", categorias);
	    	request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_JSP).forward(request, response);
	    	
	    } else if ("delete".equals(accion)) {
	    	
	    	String idProd = request.getParameter("idProdModif");
	    	int idProducto = Integer.parseInt(idProd);
	    	// ProductoService.eliminarProducto(request);
	    	request.getRequestDispatcher(Rutas.PRODUCTOS_EMPLEADO_JSP).forward(request, response);
	    }
				
	}

}
