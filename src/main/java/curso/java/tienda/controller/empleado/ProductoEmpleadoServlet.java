package curso.java.tienda.controller.empleado;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
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

		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
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
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
        String accion = null;
        String id = null;
		Map<String, String> campos = new HashMap<String, String>();
		String absolutePath = getServletContext().getRealPath("");
		
		FileItemFactory file = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(file);
		
		List items = null;

		try {
			items = fileUpload.parseRequest(request);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		FileItem imagen = null;
        
		for (Object item : items) {
			FileItem fileItem = (FileItem) item;
		
			if (fileItem.isFormField()) {

				if (fileItem.getFieldName().equals("accion")) {
					accion = fileItem.getString();

				} else {
					campos.put(fileItem.getFieldName(), fileItem.getString());
				}

			} else {
				imagen = fileItem;
			}
		}        

        
        if (accion.equals("add")) {

			ProductoVO nuevoProducto = new ProductoVO();
    		
			if (imagen != null) {
				File f = new File(absolutePath + Rutas.IMAGENES_PROD + imagen.getName());
				
				try {
                    imagen.write(f);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
				
				nuevoProducto.setImagen(imagen.getName());	
			}
			
			nuevoProducto.setNombre(campos.get("nombreProdAlta"));
			
			String precioStr = campos.get("precioProdAlta").replaceAll("[^\\d.]", "").trim();
			BigDecimal precio = new BigDecimal(precioStr);
			nuevoProducto.setPrecio(precio);
			
			nuevoProducto.setDescripcion(campos.get("descripcionProdAlta"));
			
			String impuestoStr = campos.get("impuestoProdAlta").replace("%", "").trim();
			BigDecimal impuesto = new BigDecimal(impuestoStr);
			nuevoProducto.setImpuesto(impuesto);
			
			int stock = Integer.parseInt(campos.get("stockProdAlta"));
			nuevoProducto.setStock(stock);
			
			int idCategoria = Integer.parseInt(campos.get("categoriaProdAlta"));
			nuevoProducto.setId_categoria(idCategoria);
			
			ProductoService.altaProducto(nuevoProducto);			
    			    		  
			request.getRequestDispatcher(Rutas.ALTA_PRODUCTO_JSP).forward(request, response);    					
        }

	}


}