package curso.java.tienda.model.DAO.DetallePedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.DetallePedido.DetallePedidoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;

public class DetallePedidoDAO {
	
	public static List<DetallePedidoVO> findAll() { 
		
		List<DetallePedidoVO> detallesPedidos = new ArrayList<DetallePedidoVO>();
		DetallePedidoVO detallePedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM detalles_pedido");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				detallePedido = new DetallePedidoVO();
				
				detallePedido.setId(rs.getInt("id"));
				detallePedido.setId_pedido(rs.getInt("id_pedido"));
				detallePedido.setId_producto(rs.getInt("id_producto"));
				detallePedido.setPrecio_unidad(rs.getBigDecimal("precio_unidad"));
				detallePedido.setUnidades(rs.getInt("unidades"));
				detallePedido.setImpuesto(rs.getBigDecimal("impuesto"));
				detallePedido.setTotal(rs.getBigDecimal("total"));
				
				detallesPedidos.add(detallePedido);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return detallesPedidos;
	}
	
	
	public static DetallePedidoVO findById(int id) {
		
		DetallePedidoVO detallePedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM detalles_pedido WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				detallePedido = new DetallePedidoVO();
				
				detallePedido.setId(rs.getInt("id"));
				detallePedido.setId_pedido(rs.getInt("id_pedido"));
				detallePedido.setId_producto(rs.getInt("id_producto"));
				detallePedido.setPrecio_unidad(rs.getBigDecimal("precio_unidad"));
				detallePedido.setUnidades(rs.getInt("unidades"));
				detallePedido.setImpuesto(rs.getBigDecimal("impuesto"));
				detallePedido.setTotal(rs.getBigDecimal("total"));
	
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return detallePedido;
		
	}
	
	public static List<DetallePedidoVO> findByIdPedido(int id_pedido) {

		List<DetallePedidoVO> detallesPedidos = new ArrayList<DetallePedidoVO>();
		DetallePedidoVO detallePedido = null;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM detalles_pedido WHERE id_pedido = ?");

			st.setInt(1, id_pedido);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				detallePedido = new DetallePedidoVO();

				detallePedido.setId(rs.getInt("id"));
				detallePedido.setId_pedido(rs.getInt("id_pedido"));
				detallePedido.setId_producto(rs.getInt("id_producto"));
				detallePedido.setPrecio_unidad(rs.getBigDecimal("precio_unidad"));
				detallePedido.setUnidades(rs.getInt("unidades"));
				detallePedido.setImpuesto(rs.getBigDecimal("impuesto"));
				detallePedido.setTotal(rs.getBigDecimal("total"));

				detallesPedidos.add(detallePedido);
			}

		} catch (SQLException e) {

		}

		return detallesPedidos;

	}
	
	public static List<ProductoVO> findProducto(int idPedido) {
				
		List<ProductoVO> productos = new ArrayList<>();
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT p.* FROM productos p JOIN detalles_pedido dp ON p.id = dp.id_producto WHERE dp.id_pedido = ?");

			st.setInt(1, idPedido);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				ProductoVO producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productos;
		
	}
	
	public static boolean insert(DetallePedidoVO detallePedido) {		

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement(
					"INSERT INTO detalles_pedido (id_pedido, id_producto, precio_unidad, unidades, impuesto, total) VALUES (?, ?, ?, ?, ?, ?)");

			st.setInt(1, detallePedido.getId_pedido());
			st.setInt(2, detallePedido.getId_producto());
			st.setBigDecimal(3, detallePedido.getPrecio_unidad());
			st.setInt(4, detallePedido.getUnidades());
			st.setBigDecimal(5, detallePedido.getImpuesto());
			st.setBigDecimal(6, detallePedido.getTotal());
			
			System.out.println("Total detalle insert: " + detallePedido.getTotal());

			st.executeUpdate();

			return true;

		} catch (SQLException e) {
			return false;
		}

	}
	
	public static boolean update(DetallePedidoVO detallePedido) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement(
					"UPDATE detalles_pedido SET id_pedido = ?, id_producto = ?, precio_unidad = ?, unidades = ?, impuesto = ?, total = ? WHERE id = ?");

			st.setInt(1, detallePedido.getId_pedido());
			st.setInt(2, detallePedido.getId_producto());
			st.setBigDecimal(3, detallePedido.getPrecio_unidad());
			st.setInt(4, detallePedido.getUnidades());
			st.setBigDecimal(5, detallePedido.getImpuesto());
			st.setBigDecimal(6, detallePedido.getTotal());
			st.setInt(7, detallePedido.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			return false;
		}

		return true;

	}
	
	

}
