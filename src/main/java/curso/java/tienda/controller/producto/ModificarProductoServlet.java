package curso.java.tienda.controller.producto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
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
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
	    
	    String id = request.getParameter("idProdModif");
	    String idCategoria = request.getParameter("categoriaProdModif");
	    String nombre = request.getParameter("nombreProdModif");
	    String precio = request.getParameter("precioProdModif").replaceAll("[^\\d.]", "").trim();
	    String descripcion = request.getParameter("descripcionProdModif");
	    String impuesto = request.getParameter("impuestoProdModif").replace("%", "").trim();
	    String stock = request.getParameter("stockProdModif");
	    String imagen = request.getParameter("imagenProdModif");
	    
	    BigDecimal precioBD = new BigDecimal(precio);
	    BigDecimal impuestoBD = new BigDecimal(impuesto);
	    
	    ProductoVO producto = new ProductoVO();
		    producto.setId(Integer.parseInt(id));
		    producto.setId_categoria(Integer.parseInt(idCategoria));
		    producto.setNombre(nombre);
		    producto.setPrecio(precioBD);
		    producto.setDescripcion(descripcion);
		    producto.setImpuesto(impuestoBD);
		    producto.setStock(Integer.parseInt(stock));
		    producto.setImagen(imagen);
	    	   	    	    
	    ProductoVO productoActualizado = ProductoService.actualizarProducto(producto);
			request.setAttribute("producto", productoActualizado);
	    	    
		List<ProductoVO> productos = ProductoService.getProductos();
		    request.setAttribute("productos", productos);
			
	    List<CategoriaVO> categorias = CategoriaService.getCategorias();				
			request.setAttribute("categorias", categorias);
	    
		if (usuario.esEmpleado()) {
			request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_JSP).forward(request, response);
		
		} else if (usuario.esAdmin()) {
			request.getRequestDispatcher(Rutas.MODIFICAR_PRODUCTO_ADMIN_JSP).forward(request, response);
		}
	}

}
