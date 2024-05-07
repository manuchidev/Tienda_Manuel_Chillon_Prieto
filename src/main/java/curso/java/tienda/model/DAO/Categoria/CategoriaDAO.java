package curso.java.tienda.model.DAO.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;

public class CategoriaDAO {
	
	public static List<CategoriaVO> findAll() {
		
		List<CategoriaVO> categorias = new ArrayList<CategoriaVO>();
		CategoriaVO categoria = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM categorias");
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				categoria = new CategoriaVO();
				
				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setDescripcion(rs.getString("descripcion"));
				
				categorias.add(categoria);
			}
			
		} catch (SQLException e) {
	    }
		
		return categorias;
	}
	
	
	public static CategoriaVO findById(int id) {

		CategoriaVO categoria = null;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM categorias WHERE id = ?");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				categoria = new CategoriaVO();

				categoria.setId(rs.getInt("id"));
				categoria.setNombre(rs.getString("nombre"));
				categoria.setDescripcion(rs.getString("descripcion"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoria;
	}
	
	public static void insert(CategoriaVO categoria) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("INSERT INTO categorias (nombre, descripcion) VALUES (?, ?)");
			st.setString(1, categoria.getNombre());
			st.setString(2, categoria.getDescripcion());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(CategoriaVO categoria) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("UPDATE categorias SET nombre = ?, descripcion = ? WHERE id = ?");
			st.setString(1, categoria.getNombre());
			st.setString(2, categoria.getDescripcion());
			st.setInt(3, categoria.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void delete(int id) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("DELETE FROM categorias WHERE id = ?");
			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
