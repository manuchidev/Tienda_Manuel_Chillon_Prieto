package curso.java.tienda.controller.empleado;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
				
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
        String accion = request.getParameter("accion");
        
        switch (accion) {
        
        	case "add":
    			
        		ArrayList<String> lista = new ArrayList<>();
				String absolutePath = getServletContext().getRealPath("");
				ProductoVO nuevoProducto = new ProductoVO();
        		
        		try {
        			FileItemFactory file = new DiskFileItemFactory();
        			ServletFileUpload flieUpload = new ServletFileUpload(file);
        			List items = flieUpload.parseRequest(request);
        			
					for (Object item : items) {
						FileItem fileItem = (FileItem) item;
						
						if (!fileItem.isFormField()) {
							File f = new File(absolutePath + Rutas.IMAGENES_PROD + fileItem.getName());
							fileItem.write(f);
							nuevoProducto.setImagen(fileItem.getName());
							
						} else {
							lista.add(fileItem.getString());
						}
					}
					
					BigDecimal precio = new BigDecimal(lista.get(1));
					BigDecimal impuesto = new BigDecimal(lista.get(3));
					int stock = Integer.parseInt(lista.get(4));
					int idCategoria = Integer.parseInt(lista.get(5));
					
					nuevoProducto.setNombre(lista.get(0));
					nuevoProducto.setPrecio(precio);
					nuevoProducto.setDescripcion(lista.get(2));
					nuevoProducto.setImpuesto(impuesto);
					nuevoProducto.setStock(stock);
					nuevoProducto.setId_categoria(idCategoria);
										
					ProductoService.altaProducto(nuevoProducto);
					
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		  
    			request.getRequestDispatcher("ProductoEmpleado?accion=listar").forward(request, response);    			
    			break;
    			
			case "listar":
				List<ProductoVO> productos = ProductoService.getProductos();
				request.setAttribute("productos", productos);
				request.getRequestDispatcher(Rutas.ALTA_PRODUCTO_JSP).forward(request, response);
				break;
    			
        	case "edit":
        		String idProdModif = request.getParameter("idProdModif");
    	    	String idCategoriaModif = request.getParameter("categoriaProdModif");
    	    	String nombreModif = request.getParameter("nombreProdModif");
    	    	String precioModif = request.getParameter("precioProdModif").replaceAll("[^\\d.]", "").trim();
    	    	String descripcionModif = request.getParameter("descripcionProdModif");
    	    	String impuestoModif = request.getParameter("impuestoProdModif").replace("%", "").trim();
    	    	String stockModif = request.getParameter("stockProdModif");
    	    	String imagenModif = request.getParameter("imagenProdModif");
    	    	
    	    	ProductoVO producto = ProductoService.actualizarProducto(idProdModif, idCategoriaModif, nombreModif, precioModif, descripcionModif, impuestoModif, stockModif, imagenModif);	    	    
    	    	categorias = CategoriaService.getCategorias();
    	    	
    	    	if (idProdModif != null && idCategoriaModif != null) {
    	    		
    	    		producto = ProductoService.getProductoId(producto.getId());
    	    		request.setAttribute("producto", producto);
    	    		
    	    		List<ProductoVO> productosCategoriaDetalles = ProductoService.getProductosCategoriaDetalles(producto.getId(), producto.getId_categoria());
    	    		request.setAttribute("productosCategoriaDetalles", productosCategoriaDetalles);
    	    	}
    	    	
    	    	request.setAttribute("categorias", categorias);
    	    	request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_JSP).forward(request, response);
    	    	
    	    	break;
    	    	
        	case "delete":
        		String idProd = request.getParameter("idProd");
    	    	int idProducto = Integer.parseInt(idProd);
    	    	// ProductoService.eliminarProducto(request);
    	    	request.getRequestDispatcher(Rutas.PRODUCTOS_EMPLEADO_JSP).forward(request, response);
        }

	}


}
