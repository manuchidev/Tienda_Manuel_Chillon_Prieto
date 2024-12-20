package curso.java.tienda.model.VO.Usuario;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioVO {
	
	private int id;
	private int id_rol;
	private String email;
	private String clave;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private String provincia;
	private String localidad;
	private String telefono;
	private String dni;
	private Timestamp fecha_baja;
	
	public UsuarioVO(String email, String clave, String nombre, String apellido1, String apellido2, String direccion,
			String provincia, String localidad, String telefono, String dni) {
		
		this.email = email;
		this.clave = clave;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public boolean esAdmin() {
		return this.id_rol == 1;
	}
	
	public boolean esEmpleado() {		
		return this.id_rol == 2;
	}
	
	public boolean esCliente() {
		return this.id_rol == 3;
	}
	
}
