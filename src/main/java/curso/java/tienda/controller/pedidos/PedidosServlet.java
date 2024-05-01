package curso.java.tienda.controller.pedidos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Pedido.PedidoService;

/**
 * Servlet implementation class PedidosServlet
 */

@WebServlet("/Pedidos")
public class PedidosServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		
		List<PedidoVO> pedidos = PedidoService.getPedidoUsuario(usuario.getId());	
		request.setAttribute("pedidos", pedidos);

		request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		
		List<PedidoVO> pedidos = PedidoService.getPedidoUsuario(usuario.getId());	
		request.setAttribute("pedidos", pedidos);
		
		String accion = request.getParameter("accion");	
		String id = request.getParameter("idPedido");
		
		int idPedido = Integer.parseInt(id);
		
		if ("Factura".equals(accion)) {
			

		} else if ("Cancelacion".equals(accion)) {
			
			for (PedidoVO pedido : pedidos) {
				if (pedido.getId() == idPedido) {
					pedido.setEstado("PC");
				}
			}
			
			request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
		}
				
	}

}
