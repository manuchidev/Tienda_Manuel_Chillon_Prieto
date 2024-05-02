package curso.java.tienda.model.DAO.Pedido;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Pedido.PedidoVO;

public class PedidoDAO {
	
	public static List<PedidoVO> findAllASC() { 
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos ORDER BY fecha ASC");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getBigDecimal("total"));
				
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return pedidos;
	}
	
	public static List<PedidoVO> findAllDESC() { 
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos ORDER BY fecha DESC");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getBigDecimal("total"));
				
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			
		}
		
		return pedidos;
	}
	
	
	public static PedidoVO findById(int id) {
		
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getBigDecimal("total"));
	
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return pedido;
		
	}
	
	public static List<PedidoVO> findByIdUsuarioASC(int id_usuario) {
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos WHERE id_usuario = ? ORDER BY fecha ASC");
			
			st.setInt(1, id_usuario);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getBigDecimal("total"));
				
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return pedidos;
	}
	
	public static List<PedidoVO> findByIdUsuarioDESC(int id_usuario) {
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos WHERE id_usuario = ? ORDER BY fecha DESC");
			
			st.setInt(1, id_usuario);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getBigDecimal("total"));
				
				pedidos.add(pedido);
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return pedidos;
	}
	
	public static int insert(PedidoVO pedido) {

		int idPedido = 0;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("INSERT INTO pedidos (id_usuario, fecha, metodo_pago, estado) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

			st.setInt(1, pedido.getId_usuario());
			st.setTimestamp(2, pedido.getFecha());
			st.setString(3, pedido.getMetodo_pago());
			st.setString(4, pedido.getEstado());

			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				idPedido = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idPedido;

	}
	
	public static boolean update(PedidoVO pedido) {
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement(
					"UPDATE pedidos SET id_usuario = ?, fecha = ?, metodo_pago = ?, estado = ?, num_factura = ?, total = ? WHERE id = ?");
			
			st.setInt(1, pedido.getId_usuario());
			st.setTimestamp(2, pedido.getFecha());
			st.setString(3, pedido.getMetodo_pago());
			st.setString(4, pedido.getEstado());
			st.setString(5, pedido.getNum_factura());
			st.setBigDecimal(6, pedido.getTotal());
			st.setInt(7, pedido.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean updateEstado(int id, String estado) {
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement(
					"UPDATE pedidos SET estado = ? WHERE id = ?");
			
			st.setString(1, estado);
			st.setInt(2, id);
						
			st.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public static void insertFactura(int id, String numFactura, BigDecimal total) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("UPDATE pedidos SET num_factura = ?, total = ? WHERE id = ?");

			st.setString(1, numFactura);
			st.setBigDecimal(2, total);
			st.setInt(3, id);

			st.executeUpdate();

		} catch (SQLException e) {

		}

	}

	public static String getNumFactura(int idPedido) {

		String numFactura = null;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT num_factura FROM pedidos WHERE id = ?");

			st.setInt(1, idPedido);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				numFactura = rs.getString("num_factura");
			}

		} catch (SQLException e) {
			return null;
		}

		return numFactura;
	}

}
