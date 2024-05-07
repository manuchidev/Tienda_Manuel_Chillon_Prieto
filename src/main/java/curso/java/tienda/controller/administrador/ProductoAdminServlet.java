package curso.java.tienda.controller.administrador;

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
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Producto.ProductoService;

/**
 * Servlet implementation class ProductoAdminServlet
 */

@WebServlet("/ProductoAdmin")
public class ProductoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoAdminServlet() {
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
	    
	    switch (accion) {
	    	
			case "view":
				request.getRequestDispatcher(Rutas.PRODUCTOS_ADMIN_JSP).forward(request, response);
				break;
				
			case "add":
				request.getRequestDispatcher(Rutas.ALTA_PRODUCTO_ADMIN_JSP).forward(request, response);
				break;
				
			case "edit":
				ProductoVO producto = ProductoService.getProductoId(idProducto);
				request.setAttribute("producto", producto);
				
				request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_ADMIN_JSP).forward(request, response);
				break;
				
			case "delete":
				ProductoVO productoBaja = ProductoService.getProductoId(idProducto);
				ProductoService.bajaProducto(productoBaja.getId());
				
				request.getRequestDispatcher(Rutas.PRODUCTOS_ADMIN_JSP).forward(request, response);
				break;
				
			default:
				break;
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
        String accion = request.getParameter("accion");
		ArrayList<String> lista = new ArrayList<>();
		String absolutePath = getServletContext().getRealPath("");
        
        switch (accion) {
        
        	case "add":
    			
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
				request.getRequestDispatcher(Rutas.ALTA_PRODUCTO_ADMIN_JSP).forward(request, response);
				break;
				
			case "delete":
				String id = request.getParameter("idProducto");
				Integer idProducto = Integer.parseInt(id);
				
				ProductoVO productoBaja = ProductoService.getProductoId(idProducto);
				
				ProductoService.bajaProducto(productoBaja.getId());
				
				request.getRequestDispatcher(Rutas.PRODUCTOS_ADMIN_JSP).forward(request, response);
				break;
        }
	}

}
