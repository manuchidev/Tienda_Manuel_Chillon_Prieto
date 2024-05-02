package curso.java.tienda.service.Pedido;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import curso.java.tienda.config.Rutas;
import curso.java.tienda.model.DAO.DetallePedido.DetallePedidoDAO;
import curso.java.tienda.model.DAO.Pedido.PedidoDAO;
import curso.java.tienda.model.DAO.Usuario.UsuarioDAO;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Pedido.PedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;
import curso.java.tienda.service.Config.ConfigService;
import curso.java.tienda.service.Producto.ProductoService;

public class PedidoService {
	
	public static PedidoVO getPedido(int id) {

		PedidoVO pedido = PedidoDAO.findById(id);

		return pedido;
	}
	
	
//	public static List<PedidoVO> getPedidos() {
//		
//		List<PedidoVO> detallesPedido = PedidoDAO.findAll();
//		
//		return detallesPedido;
//	}
	
		
	public static int realizarPedido(PedidoVO pedido) {
		
		int idPedido = PedidoDAO.insert(pedido);
		
		return idPedido;
	}
	
	public static List<PedidoVO> getPedidoUsuarioASC(int id) {

		List<PedidoVO> pedidos = PedidoDAO.findByIdUsuarioASC(id);

		return pedidos;
	}
	
	public static List<PedidoVO> getPedidoUsuarioDESC(int id) {
		
		List<PedidoVO> pedidos = PedidoDAO.findByIdUsuarioDESC(id);
		
		return pedidos;
	}
	
	public static List<PedidoVO> getPedidosUsuariosASC() {

		List<PedidoVO> pedidos = PedidoDAO.findAllASC();

		return pedidos;
	}
	
	public static List<PedidoVO> getPedidosUsuariosDESC() {
		
		List<PedidoVO> pedidos = PedidoDAO.findAllDESC();
		
		return pedidos;
	}
	
	public static void cambiarEstado(int id, String estado) {
		
		PedidoDAO.updateEstado(id, estado);
	}


	public static void crearFactura(int id) {
		
		List<DetallePedidoVO> detallesPedido  = DetallePedidoDAO.findByIdPedido(id);
		
		BigDecimal total = BigDecimal.ZERO;
		
		for (DetallePedidoVO detallePedido : detallesPedido) {

			total = detallePedido.getTotal().add(total).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		
		int contador = ConfigService.obtenerContador();
		
		String numFactura = "FAC_00" + contador + "_" + id;
		
		PedidoDAO.insertFactura(id, numFactura, total);
	}
	
	public static String obtenerNumFactura(int idPedido) {

		String numFactura = PedidoDAO.getNumFactura(idPedido);
		
		return numFactura;		
	}


	public static ByteArrayOutputStream generarFactura(int idPedido) {

	    ByteArrayOutputStream baos = escribirPDF(idPedido);		

		return baos;	
	}
	
	public static ByteArrayOutputStream escribirPDF(int idPedido) {
			
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter writer = PdfWriter.getInstance(documento, baos);			
//			writer.setPageEvent(new PDFHeaderFooter());
			
			documento.addAuthor("Riders Shop");			
			documento.addTitle("Factura");
			documento.addCreationDate();

			documento.open();
			
			Paragraph titulo = new Paragraph();
				titulo.add("RIDERS SHOP\n\n");
				titulo.setAlignment(Paragraph.ALIGN_RIGHT);
				
			documento.add(titulo);
			
			// Añadir Imagen
			Image imagen = Image.getInstance("C:\\Users\\mchillon\\Desktop\\ProyectoSerbatic\\Tienda_Manuel_Chillon_Prieto\\src\\main\\webapp" + Rutas.IMAGENES_LOGO);
				imagen.scaleAbsolute(150, 150);
				imagen.setAlignment(Image.ALIGN_LEFT);
				imagen.setAbsolutePosition(50, 750);
				
			documento.add(imagen);
			
			Paragraph numFactura = new Paragraph();
				numFactura.add("Factura nº: " + obtenerNumFactura(idPedido) + "\n\n");
				numFactura.setAlignment(Paragraph.ALIGN_CENTER);
				
			documento.add(numFactura);
			
			// Fecha		
			String fecha = PedidoDAO.getFecha(idPedido).toString().substring(0, 10);
			Paragraph fechaFactura = new Paragraph();
				fechaFactura.add("Fecha: " + fecha + "\n\n");
				fechaFactura.setAlignment(Paragraph.ALIGN_LEFT);
						
			documento.add(fechaFactura);
			
			// Datos Cliente
            PedidoVO pedido = PedidoDAO.findById(idPedido);
            List<UsuarioVO> usuarios = UsuarioDAO.findAll();
            
            Paragraph datosCliente = new Paragraph();
            	
            	for (UsuarioVO usuario: usuarios) {
            		
            		if (usuario.getId() == pedido.getId_usuario()) {
            			datosCliente.add("Cliente: " + usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2() + "\n");
            			datosCliente.add("DNI: " + usuario.getDni() + "\n");
            			datosCliente.add("Dirección: " + usuario.getDireccion() + "\n");
            			datosCliente.add("Teléfono: " + usuario.getTelefono() + "\n\n");
            			break;
            		}            	
            	}
            	
            datosCliente.setAlignment(Paragraph.ALIGN_LEFT);
                
            documento.add(datosCliente);
                                        

			List<DetallePedidoVO> detallesPedido = DetallePedidoDAO.findByIdPedido(idPedido);
			List<ProductoVO> productos = ProductoService.getProductos();
			
			for (DetallePedidoVO detallePedido : detallesPedido) {
				
				Paragraph detalleParagraph = new Paragraph();
					
				for (ProductoVO producto : productos) {
					
					if (detallePedido.getId_producto() == producto.getId()) {
						detalleParagraph.add("Producto: " + producto.getNombre() + "\n");						
					}
				}
				
				detalleParagraph.add("Unidades: " + detallePedido.getUnidades() + " ud.\n");
				
				for (ProductoVO producto : productos) {
					
					if (detallePedido.getId_producto() == producto.getId()) {
						detalleParagraph.add("Precio unidad: " + producto.getPrecio() + "€\n");						
					}
				}
				
				detalleParagraph.add("Total: " + detallePedido.getTotal() + "€\n");
				detalleParagraph.add("\n");
				
				documento.add(detalleParagraph);
			}

			Paragraph totalParagraph = new Paragraph();			
				totalParagraph.add("TOTAL: " + pedido.getTotal() + "€\n");
							
			documento.add(totalParagraph);
			
			documento.close();
			writer.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		return baos;
	}
}
