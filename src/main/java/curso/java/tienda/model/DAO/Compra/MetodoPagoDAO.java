package curso.java.tienda.model.DAO.Compra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Compra.MetodoPagoVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;

public class MetodoPagoDAO {

	
	public static List<MetodoPagoVO> findAll() {
		
		List<MetodoPagoVO> metodos_pago = new ArrayList<MetodoPagoVO>();
		MetodoPagoVO metodo_pago = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM metodos_pago");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				metodo_pago = new MetodoPagoVO();
				
				metodo_pago.setId(rs.getInt("id"));
				metodo_pago.setMetodo_pago(rs.getString("metodo_pago"));
					
				metodos_pago.add(metodo_pago);
			}
			
		} catch (SQLException e) {
			return null;
	    }
		
		return metodos_pago;
				
	}
	
	public static MetodoPagoVO findById(int id) {
		
		MetodoPagoVO metodo_pago = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();		
			
			while(rs.next()) {
				
				metodo_pago = new MetodoPagoVO();
				
				metodo_pago.setId(rs.getInt("id"));
				metodo_pago.setMetodo_pago(rs.getString("metodo_pago"));
			}
			
		} catch (SQLException e) {
			return null;
	        
	    }
		
		return metodo_pago;
		
	}
}
