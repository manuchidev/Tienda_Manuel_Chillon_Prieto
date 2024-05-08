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

		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		String accion = request.getParameter("accion");
		
		switch (accion) {
		
			case "view":
				List<CategoriaVO> categorias = CategoriaService.getCategorias();
				request.setAttribute("categorias", categorias);
				request.getRequestDispatcher(Rutas.CATEGORIAS_EMPLEADO_JSP).forward(request, response);
				break;
				
			case "add":				
				request.getRequestDispatcher(Rutas.ALTA_CATEGORIA_JSP).forward(request, response);
				break;
				
			case "edit":
				String id = request.getParameter("idCat");
				Integer idCategoria = Integer.parseInt(id);
				
				CategoriaVO categoria = CategoriaService.getCategoria(idCategoria);
				request.setAttribute("categoria", categoria);
				
				request.getRequestDispatcher(Rutas.MODIFICAR_CATEGORIA_JSP).forward(request, response);
				break;
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

			CategoriaVO nuevoCategoria = new CategoriaVO();
			
			if (imagen != null) {
				File f = new File(absolutePath + Rutas.IMAGENES_CAT + imagen.getName());
				
				try {
					imagen.write(f);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				nuevoCategoria.setImagen(imagen.getName());		
			} 
    		
    		nuevoCategoria.setNombre(campos.get("nombreCatAlta"));
    		nuevoCategoria.setDescripcion(campos.get("descripcionCatAlta"));
    		
    		CategoriaService.altaCategoria(nuevoCategoria);
    		
			categorias = CategoriaService.getCategorias();
        	request.setAttribute("categorias", categorias);
        			        
			request.getRequestDispatcher(Rutas.ALTA_CATEGORIA_JSP).forward(request, response);
				
		}
		
	}

}
