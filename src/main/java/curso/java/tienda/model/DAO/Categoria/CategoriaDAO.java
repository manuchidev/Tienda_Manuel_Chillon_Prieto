package curso.java.tienda.model.DAO.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;
import curso.java.tienda.model.VO.Producto.ProductoVO;

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

		}

		return categoria;
	}

}
