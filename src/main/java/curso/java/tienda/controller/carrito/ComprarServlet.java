package curso.java.tienda.controller.carrito;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Compra.MetodoPagoVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Carrito.CarritoService;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Compra.CompraService;
import curso.java.tienda.service.Compra.MetodoPagoService;
import curso.java.tienda.service.Config.ConfigService;

/**
 * Servlet implementation class ComprarServlet
 */

@WebServlet("/comprar")
public class ComprarServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarServlet() {
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
		
		HttpSession session = request.getSession(true);
		
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		request.setAttribute("categorias", categorias);
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");		
		request.setAttribute("totalCarrito", CarritoService.calcularTotal(carrito));
		request.setAttribute("totalCarritoIVA", CarritoService.calcularTotalIVA(CarritoService.calcularTotal(carrito)));
		
		UsuarioVO usuario = (UsuarioVO)session.getAttribute("usuario");
		
		if (carrito != null && !carrito.isEmpty()) {
			
			if (usuario != null) {
				
				List<MetodoPagoVO> metodos_pago = MetodoPagoService.getMetodosPago();
				
				request.setAttribute("metodos_pago", metodos_pago);
				
				boolean stockSuficiente = CompraService.validarStock(carrito);
				
				if (stockSuficiente) {
					logger.info("Stock suficiente");
					request.getRequestDispatcher(Rutas.COMPRA_JSP).forward(request, response);
				
				} else {
					logger.error("Stock insuficiente");
					request.setAttribute("mensajeError", "Stock insuficiente");
					request.getRequestDispatcher(Rutas.CARRITO_JSP).forward(request, response);
				}
								
			} else {
				logger.error("Usuario no autenticado");
				request.getRequestDispatcher(Rutas.LOGIN_JSP).forward(request, response);
			}
		
		} else {
			logger.error("Carrito vacío");
			request.getRequestDispatcher(Rutas.CARRITO_JSP).forward(request, response);
		}
		
	}

}
