package curso.java.tienda.controller.pedidos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.controller.base.BaseServlet;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Config.ConfigVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Categoria.CategoriaService;
import curso.java.tienda.service.Config.ConfigService;
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
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		String orden = request.getParameter("orden");
		
		if (orden != null) {
			
			if ("ASC".equals(orden)) {
				List<PedidoVO> pedidos = PedidoService.getPedidoUsuarioASC(usuario.getId());
				request.setAttribute("pedidos", pedidos);
				
			} else if ("DESC".equals(orden)) {
				List<PedidoVO> pedidos = PedidoService.getPedidoUsuarioDESC(usuario.getId());
				request.setAttribute("pedidos", pedidos);
			}
		
		} else {
			List<PedidoVO> pedidos = PedidoService.getPedidoUsuarioDESC(usuario.getId());
			request.setAttribute("pedidos", pedidos);
		}
		
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ConfigVO> datosEmpresa = ConfigService.obtenerDatosEmpresa();
		request.setAttribute("datosEmpresa", datosEmpresa);
		
		UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
		List<CategoriaVO> categorias = CategoriaService.getCategorias();
		
		List<PedidoVO> pedidos = PedidoService.getPedidoUsuarioDESC(usuario.getId());
		request.setAttribute("pedidos", pedidos);
				
		String accion = request.getParameter("accion");	
		String id = request.getParameter("idPedido");

		int idPedido = Integer.parseInt(id);
		
		if ("Factura".equals(accion)) {
			
			String ruta = request.getServletContext().getRealPath("/");
			
			ByteArrayOutputStream factura = PedidoService.generarFactura(idPedido, ruta);

			String numFactura = PedidoService.obtenerNumFactura(idPedido);

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename="+numFactura+".pdf");

			ServletOutputStream out = response.getOutputStream();
			factura.writeTo(out);
			out.flush();

			return;

		} else if ("Cancelacion".equals(accion)) {
			
			for (PedidoVO pedido : pedidos) {
				if (pedido.getId() == idPedido) {
					PedidoService.cambiarEstado(pedido.getId(), "PC");
					break;
				}
			}
			
		}
				
		pedidos = PedidoService.getPedidoUsuarioASC(usuario.getId());
		request.setAttribute("pedidos", pedidos);
		request.setAttribute("categorias", categorias);
		request.getRequestDispatcher(Rutas.PEDIDOS_JSP).forward(request, response);
	}

}
