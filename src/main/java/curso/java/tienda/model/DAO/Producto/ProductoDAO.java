package curso.java.tienda.model.DAO.Producto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Producto.ProductoVO;

public class ProductoDAO {
	
	public static List<ProductoVO> findAll() { 
	
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE fecha_baja IS NULL");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
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
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setStock(rs.getInt("stock"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
	
			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return producto;
		
	}
	
	public static List<ProductoVO> findByIdCategoria(int id_categoria) {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id_categoria = ? ");
			
			st.setInt(1, id_categoria);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;
	}
	
	public static List<ProductoVO> findByIdCategoriaDetalles(int id_producto, int id_categoria) {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id != ? AND id_categoria = ? ");
			
			st.setInt(1, id_producto);
			st.setInt(2, id_categoria);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;
	}
	
	public static int findStock(int id) {
		
		int stock = 0;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT stock FROM productos WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
									
				stock = rs.getInt("stock");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return stock;
		
	}
	
	public static ProductoVO updateProducto(int id, int idCategoria, String nombre, String descripcion, BigDecimal precio, int stock, BigDecimal impuesto, String imagen) {
				
		ProductoVO producto = null;
		
		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement( "UPDATE productos SET id_categoria = ?, nombre = ?, descripcion = ?, precio = ?, stock = ?, impuesto = ?, imagen = ? WHERE id = ?");

			st.setInt(1, idCategoria);
			st.setString(2, nombre);
			st.setString(3, descripcion);
			st.setBigDecimal(4, precio);
			st.setInt(5, stock);
			st.setBigDecimal(6, impuesto);
			st.setString(7, imagen);
			st.setInt(8, id);

			st.executeUpdate();

			producto = findById(id);

		} catch (SQLException e) {

		}

		return producto;
	}
	
	public static void updateStock(int id, int cantidad) {
				
		try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("UPDATE productos SET stock = stock - ? WHERE id = ?");
            
            st.setInt(1, cantidad);
            st.setInt(2, id);
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            
        }
	}

	public static List<ProductoVO> findByMinPrecio() {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos ORDER BY precio ASC");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;	
	}

	public static List<ProductoVO> findByMaxPrecio() {

		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos ORDER BY precio DESC");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;	
	}


	public static List<ProductoVO> findByPrecio(int precio) {

		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE precio <= ? ORDER BY precio ASC");
			
			st.setInt(1, precio);

			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;	
	}

	public static List<ProductoVO> findByMaxPrecioCategoria(int categoria) {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id_categoria = ? ORDER BY precio DESC");
			
			st.setInt(1, categoria);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;		
	}


	public static List<ProductoVO> findByMinPrecioCategoria(int categoria) {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE id_categoria = ? ORDER BY precio ASC");
			
			st.setInt(1, categoria);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;	

	}
	
	public static void insertProducto(ProductoVO producto) {
		
		Timestamp fecha = new Timestamp(System.currentTimeMillis());
        
        try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("INSERT INTO productos (id_categoria, nombre, descripcion, precio, stock, fecha_alta, impuesto, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            st.setInt(1, producto.getId_categoria());
            st.setString(2, producto.getNombre());
            st.setString(3, producto.getDescripcion());
            st.setBigDecimal(4, producto.getPrecio());
            st.setInt(5, producto.getStock());
            st.setTimestamp(6, fecha);
            st.setBigDecimal(7, producto.getImpuesto());
            st.setString(8, producto.getImagen());
            
            st.executeUpdate();
            
            producto = findByIdCategoria(producto.getId_categoria()).get(0);
            
        } catch (SQLException e) {
            
        }
	}
	
	public static void deleteProducto(int id) {

		Timestamp fecha = new Timestamp(System.currentTimeMillis());

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("UPDATE productos SET fecha_baja = ? WHERE id = ?");

			st.setTimestamp(1, fecha);
			st.setInt(2, id);

			st.executeUpdate();

		} catch (SQLException e) {

		}
	}


	public static List<ProductoVO> findByPrecioCategoria(BigDecimal precio, int categoria) {
		
		List<ProductoVO> productos = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM productos WHERE precio <= ? AND id_categoria = ? ORDER BY precio ASC");
			
			st.setBigDecimal(1, precio);
			st.setInt(2, categoria);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setId_categoria(rs.getInt("id_categoria"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getBigDecimal("precio"));
				producto.setStock(rs.getInt("stock"));
				producto.setFecha_alta(rs.getTimestamp("fecha_alta"));
				producto.setFecha_baja(rs.getTimestamp("fecha_baja"));
				producto.setImpuesto(rs.getBigDecimal("impuesto"));
				producto.setImagen(rs.getString("imagen"));
				
				productos.add(producto);
			}
			
		} catch (SQLException e) {
	        
	    }
		
		return productos;	
	}
}
