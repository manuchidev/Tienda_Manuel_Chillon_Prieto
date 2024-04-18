package curso.java.tienda.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.ProductoVO;

public class ProductoDAO {
	
	public static List<ProductoVO> findAll() { 
	
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setStock(rs.getInt("stock"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;
	}
	
	
	public static ProductoVO findById(int id) {
		
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setStock(rs.getInt("stock"));
				producto.setImagen(rs.getString("imagen"));
	
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return producto;
		
	}
}
