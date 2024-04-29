package curso.java.tienda.model.DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;

public class UsuarioDAO {
	
	public static UsuarioVO findById(int id) {
		
		UsuarioVO usuario = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				usuario = new UsuarioVO();
				
				usuario.setId(rs.getInt("id"));
				usuario.setId_rol(rs.getInt("id_rol"));
				usuario.setEmail(rs.getString("email"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setProvincia(rs.getString("provincia"));
				usuario.setLocalidad(rs.getString("localidad"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setDni(rs.getString("dni"));

			}

		} catch (SQLException e) {
			return null;
		}
		
		return usuario;
	}
	
	
	public static UsuarioVO findByEmail(String email) {
		
		UsuarioVO usuario = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE email = ?");
			
			st.setString(1, email);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				usuario = new UsuarioVO();
				
				usuario.setId(rs.getInt("id"));
				usuario.setId_rol(rs.getInt("id_rol"));
				usuario.setEmail(rs.getString("email"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setProvincia(rs.getString("provincia"));
				usuario.setLocalidad(rs.getString("localidad"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setDni(rs.getString("dni"));

			}
			
						
		} catch (SQLException e) {
			return null;
		}
		
		return usuario;
				
	}
	
	public static UsuarioVO insertarCliente(UsuarioVO usuario) {
		
		UsuarioVO usuarioRegistrado = null;
				    
        try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("INSERT INTO usuarios (id_rol, email, clave, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni) VALUES (3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            st.setString(1, usuario.getEmail());
            st.setString(2, usuario.getClave());
            st.setString(3, usuario.getNombre());
            st.setString(4, usuario.getApellido1());
            st.setString(5, usuario.getApellido2());
            st.setString(6, usuario.getDireccion());
            st.setString(7, usuario.getProvincia());
            st.setString(8, usuario.getLocalidad());
            st.setString(9, usuario.getTelefono());
            st.setString(10, usuario.getDni());

            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            
			if (rs.next()) {
				usuarioRegistrado = UsuarioDAO.findById(rs.getInt(1));
			}
                        
        } catch (SQLException e) {
        	e.printStackTrace();
        }       
        
        return usuarioRegistrado;
	}
	
	public static void updateUsuario(UsuarioVO usuario) {
		
		try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("UPDATE usuarios SET nombre = ?, apellido1 = ?, apellido2 = ?, telefono = ?, direccion = ?, provincia = ?, localidad = ? WHERE id = ?");
            
            st.setString(1, usuario.getNombre());
            st.setString(2, usuario.getApellido1());
            st.setString(3, usuario.getApellido2());
            st.setString(4, usuario.getTelefono());
            st.setString(5, usuario.getDireccion());
            st.setString(6, usuario.getProvincia());
            st.setString(7, usuario.getLocalidad());
            st.setInt(8, usuario.getId());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        
	}
	
	public static void updateClaveUsuario(UsuarioVO usuario, String clave) {
		
		try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("UPDATE usuarios SET clave = ? WHERE id = ?");
            
            st.setString(1, clave);
            st.setInt(2, usuario.getId());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }        
	}

}
