package curso.java.tienda.service.Pedido;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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


	public static ByteArrayOutputStream generarFactura(int idPedido, String ruta) {

	    ByteArrayOutputStream baos = escribirPDF(idPedido, ruta);		

		return baos;	
	}
	
	public static ByteArrayOutputStream escribirPDF(int idPedido, String ruta) {
			
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			
			PdfWriter writer = PdfWriter.getInstance(documento, baos);
			Font fuenteTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);			
			Font fuenteTotal = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
			Font fuenteNegrita = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
			Font fuenteNormal = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
			
			documento.addAuthor("Riders Shop");			
			documento.addTitle("Factura");
			documento.addCreationDate();
			documento.setMargins(20, 20, 20, 20);

			documento.open();

			// Tabla con la Imagen, el Título y  los datos de la empresa
			PdfPTable tablaEncabezado = new PdfPTable(2);
				tablaEncabezado.setWidthPercentage(100);

			// IMAGEN
			//	Image imagen = Image.getInstance("C:\\Users\\mchillon\\Desktop\\ProyectoSerbatic\\Tienda_Manuel_Chillon_Prieto\\src\\main\\webapp" + Rutas.IMAGENES_LOGO);
			PdfPCell celda = new PdfPCell();
			Image imagen = Image.getInstance(ruta + Rutas.IMAGENES_LOGO);
				imagen.scaleAbsolute(150, 150);
				imagen.setAlignment(Image.ALIGN_CENTER);
        		
			celda.addElement(imagen);
			celda.setBorder(PdfPCell.NO_BORDER);

			tablaEncabezado.addCell(celda);

			// TITULO
			celda = new PdfPCell();
			Paragraph titulo = new Paragraph();			
			    titulo.setFont(fuenteTitulo);
				titulo.add("RIDERS SHOP");
				titulo.setIndentationRight(30);
				titulo.setAlignment(Paragraph.ALIGN_CENTER);
			
			celda.addElement(new Chunk("\n\n"));
			celda.addElement(titulo);
			celda.setBorder(PdfPCell.NO_BORDER);

			tablaEncabezado.addCell(celda);
			documento.add(tablaEncabezado);

			// Tabla con la fecha y el número de factura
			PdfPTable tablaFechaNumFactura = new PdfPTable(2);
				tablaFechaNumFactura.setWidthPercentage(100);
				tablaFechaNumFactura.setSpacingBefore(30);
						
			// FECHA
			celda = new PdfPCell();		
			String fecha = PedidoDAO.getFecha(idPedido).toString().substring(0, 10);
			Paragraph fechaFactura = new Paragraph();
				fechaFactura.setFont(fuenteNegrita);
				fechaFactura.setIndentationRight(20);
				fechaFactura.add("Fecha: " + fecha + "\n\n");
				fechaFactura.setAlignment(Paragraph.ALIGN_CENTER);
				
			celda.addElement(fechaFactura);
			celda.setBorder(PdfPCell.NO_BORDER);
						
			tablaFechaNumFactura.addCell(celda);
			
			// NUM_FACTURA
			celda = new PdfPCell();
			Paragraph numFactura = new Paragraph();
				numFactura.setFont(fuenteNegrita);
				numFactura.setIndentationRight(60);
				numFactura.setAlignment(Paragraph.ALIGN_CENTER);
				numFactura.add("Factura nº: " + obtenerNumFactura(idPedido) + "\n\n");

			celda.addElement(numFactura);
			celda.setBorder(PdfPCell.NO_BORDER);

			tablaFechaNumFactura.addCell(celda);					
			documento.add(tablaFechaNumFactura);

			// Tabla con los datos del cliente, los productos
			PdfPTable tablaClienteProductos = new PdfPTable(2);
				tablaClienteProductos.setWidthPercentage(100);
				tablaClienteProductos.setSpacingBefore(5);
			
			// CLIENTE
			celda = new PdfPCell();
            PedidoVO pedido = PedidoDAO.findById(idPedido);
            List<UsuarioVO> usuarios = UsuarioDAO.findAll();    
            Paragraph datosCliente = new Paragraph();
            	datosCliente.setFont(fuenteNormal);
            	datosCliente.setAlignment(Paragraph.ALIGN_JUSTIFIED);
				datosCliente.setLeading(30);
				datosCliente.setIndentationLeft(50);
				
				datosCliente.add(new Chunk("DATOS DEL CLIENTE:\n", fuenteNegrita));
            	
			for (UsuarioVO usuario: usuarios) {
				
				if (usuario.getId() == pedido.getId_usuario()) {
					datosCliente.add("Nombre: " + usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2() + "\n");
					datosCliente.add("DNI: " + usuario.getDni() + "\n");
					datosCliente.add("Dirección: " + usuario.getDireccion() + "\n");
					datosCliente.add("Teléfono: " + usuario.getTelefono() + "\n\n");
					break;
				}            	
			}
            	
			celda.addElement(datosCliente);
			celda.setBorder(PdfPCell.NO_BORDER);

			tablaClienteProductos.addCell(celda);
                                                   
			// PRODUCTOS
			celda = new PdfPCell();
			List<DetallePedidoVO> detallesPedido = DetallePedidoDAO.findByIdPedido(idPedido);
			List<ProductoVO> productos = ProductoService.getProductos();
			Paragraph detalleParagraph = new Paragraph();
			    detalleParagraph.setFont(fuenteNormal);
			    detalleParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
				detalleParagraph.setLeading(30);
				detalleParagraph.setIndentationLeft(50);
				
				detalleParagraph.add(new Chunk("PRODUCTOS:\n", fuenteNegrita));
			
			for (DetallePedidoVO detallePedido : detallesPedido) {
				
				for (ProductoVO producto : productos) {
					
					if (detallePedido.getId_producto() == producto.getId()) {
						detalleParagraph.add(producto.getNombre() + "\n");						
						detalleParagraph.add("Unidades: " + detallePedido.getUnidades() + " ud.\n");
						detalleParagraph.add("Precio unidad: " + producto.getPrecio() + "€\n");
						detalleParagraph.add("Total: " + detallePedido.getTotal() + "€\n");
						detalleParagraph.add("\n");
						break;						
					}
				}
			}

			celda.addElement(detalleParagraph);
			celda.setBorder(PdfPCell.NO_BORDER);

			tablaClienteProductos.addCell(celda);
			documento.add(tablaClienteProductos);

			// TOTAL
			Paragraph totalParagraph = new Paragraph();
				totalParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
				totalParagraph.setFont(fuenteTotal);	
				totalParagraph.setIndentationRight(100); // Margen a la derecha
				totalParagraph.setSpacingBefore(30); // Espacio antes del párrafo
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
