package curso.java.tienda.controller.pedidos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.Pedido.PedidoService;
import curso.java.tienda.service.Usuario.UsuarioService;

/**
 * Servlet implementation class PedidosUsuariosServlet
 */
@WebServlet("/PedidosUsuarios")
public class PedidosUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidosUsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		List<UsuarioVO> usuarios = UsuarioService.getUsuarios();
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
		String orden = request.getParameter("orden");
		
		if (orden != null) {
			
			if ("ASC".equals(orden)) {
				List<PedidoVO> pedidos = PedidoService.getPedidosUsuariosASC();
				request.setAttribute("pedidos", pedidos);
				
			} else if ("DESC".equals(orden)) {
				List<PedidoVO> pedidos = PedidoService.getPedidosUsuariosDESC();
				request.setAttribute("pedidos", pedidos);
			}
		
		} else {
			List<PedidoVO> pedidos = PedidoService.getPedidosUsuariosDESC();
			request.setAttribute("pedidos", pedidos);
		}
		
		request.setAttribute("usuarios", usuarios);
		request.setAttribute("categorias", categorias);
		
		if (usuario.esEmpleado()) {
			request.getRequestDispatcher(Rutas.PEDIDOS_USUARIOS_EMPLEADO_JSP).forward(request, response);
			
		} else {
            request.getRequestDispatcher(Rutas.PEDIDOS_USUARIOS_ADMIN_JSP).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		List<UsuarioVO> usuarios = UsuarioService.getUsuarios();
		List<PedidoVO> pedidos = PedidoService.getPedidosUsuariosDESC();
		request.setAttribute("pedidos", pedidos);
				
		String accion = request.getParameter("accion");	
		String id = request.getParameter("idPedido");
		
		int idPedido = Integer.parseInt(id);
		
		if ("Enviar".equals(accion)) {
			
			for (PedidoVO pedido : pedidos) {
				
				if (pedido.getId() == idPedido) {
					PedidoService.cambiarEstado(pedido.getId(), "E");
					ConfigService.actualizarContador();
					PedidoService.crearFactura(idPedido);
					
					break;
				}
			}
			

		} else if ("Cancelar".equals(accion)) {
			
			for (PedidoVO pedido : pedidos) {
				
				if (pedido.getId() == idPedido) {
					PedidoService.cambiarEstado(pedido.getId(), "C");
					break;
				}
			}			
		}
		
		pedidos = PedidoService.getPedidosUsuariosDESC();
		request.setAttribute("pedidos", pedidos);
		request.setAttribute("categorias", categorias);
		request.setAttribute("usuarios", usuarios);
		
		
		if (usuario.esEmpleado()) {
			request.getRequestDispatcher(Rutas.PEDIDOS_USUARIOS_EMPLEADO_JSP).forward(request, response);
			
		} else {
            request.getRequestDispatcher(Rutas.PEDIDOS_USUARIOS_ADMIN_JSP).forward(request, response);
		}
	}

}
