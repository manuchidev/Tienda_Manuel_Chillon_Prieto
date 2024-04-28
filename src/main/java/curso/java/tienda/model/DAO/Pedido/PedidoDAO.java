package curso.java.tienda.model.DAO.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Pedido.PedidoVO;

public class PedidoDAO {
	
	public static List<PedidoVO> findAll() { 
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				pedido = new PedidoVO();
				
				pedido.setId(rs.getInt("id"));
				pedido.setId_usuario(rs.getInt("id_usuario"));
				pedido.setFecha(rs.getTimestamp("fecha"));
				pedido.setMetodo_pago(rs.getString("metodo_pago"));
				pedido.setEstado(rs.getString("estado"));
				pedido.setNum_factura(rs.getString("num_factura"));
				pedido.setTotal(rs.getDouble("total"));
				
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
				pedido.setTotal(rs.getDouble("total"));
	
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return pedido;
		
	}
	
	public static List<PedidoVO> findByIdUsuario(int id_usuario) {
		
		List<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM pedidos WHERE id_usuario = ?");
			
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
				pedido.setTotal(rs.getDouble("total"));
				
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
			st.setDouble(6, pedido.getTotal());
			st.setInt(7, pedido.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			return false;
		}

		return true;

	}
	
	

}
