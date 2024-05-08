package curso.java.tienda.model.DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;

public class UsuarioDAO {
	
	public static List<UsuarioVO> findAll() {

		List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE id_rol = 3 AND fecha_baja IS NULL");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				UsuarioVO usuario = new UsuarioVO();

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
				
				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			return null;
		}

		return usuarios;
	}
	
	public static List<UsuarioVO> findEmpleados() {
		
		List<UsuarioVO> empleados = new ArrayList<UsuarioVO>();

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE id_rol = 2 AND fecha_baja IS NULL");

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				UsuarioVO empleado = new UsuarioVO();

				empleado.setId(rs.getInt("id"));
				empleado.setId_rol(rs.getInt("id_rol"));
				empleado.setEmail(rs.getString("email"));
				empleado.setClave(rs.getString("clave"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido1(rs.getString("apellido1"));
				empleado.setApellido2(rs.getString("apellido2"));
				empleado.setDireccion(rs.getString("direccion"));
				empleado.setProvincia(rs.getString("provincia"));
				empleado.setLocalidad(rs.getString("localidad"));
				empleado.setTelefono(rs.getString("telefono"));
				empleado.setDni(rs.getString("dni"));
				
				empleados.add(empleado);
			}

		} catch (SQLException e) {
			return null;
		}

		return empleados;
		
	}
	
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
	
	public static UsuarioVO insertarEmpleado(UsuarioVO usuario) {
		
		UsuarioVO usuarioRegistrado = null;
				    
        try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("INSERT INTO usuarios (id_rol, email, clave, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni) VALUES (2, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
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
	
	public static UsuarioVO updateUsuario(UsuarioVO usuario) {
		
		UsuarioVO usuarioActualizado = null;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("UPDATE usuarios SET email = ?, nombre = ?, apellido1 = ?, apellido2 = ?, direccion = ?, provincia = ?, localidad = ?, telefono = ?, dni = ? WHERE id = ?");

			st.setString(1, usuario.getEmail());
			st.setString(2, usuario.getNombre());
			st.setString(3, usuario.getApellido1());
			st.setString(4, usuario.getApellido2());
			st.setString(5, usuario.getDireccion());
			st.setString(6, usuario.getProvincia());
			st.setString(7, usuario.getLocalidad());
			st.setString(8, usuario.getTelefono());
			st.setString(9, usuario.getDni());
			st.setInt(10, usuario.getId());

			st.executeUpdate();

			usuarioActualizado = UsuarioDAO.findById(usuario.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarioActualizado;
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
	
	public static void deleteUsuario(int id) {

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("UPDATE usuarios SET fecha_baja = NOW() WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
