package curso.java.tienda.controller.detallesPedido;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.DetallePedido.DetallePedidoService;

/**
 * Servlet implementation class DetallesPedidoServlet
 */

@WebServlet("/DetallesPedido")
public class DetallesPedidoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetallesPedidoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idPedido");
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		if (id != null) {
			
			int idPedido = Integer.parseInt(id);
			
			List<DetallePedidoVO> detallesPedido = DetallePedidoService.getDetallesPedidoIdPedido(idPedido);			
			request.setAttribute("detallesPedido", detallesPedido);
			
			List<ProductoVO> productosDetallePedido = DetallePedidoService.getProductosDetallePedido(idPedido);
			request.setAttribute("productosDetallePedido", productosDetallePedido);
			
			request.getRequestDispatcher(Rutas.DETALLES_PEDIDO_JSP).forward(request, response);
		
		} else {
			request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
