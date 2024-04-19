package curso.java.tienda.model.VO.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UsuarioVO {
	
	int id;
	int id_rol;
	String email;
	String clave;
	String nombre;
	String apellido1;
	String apellido2;
	String direccion;
	String provincia;
	String localidad;
	String telefono;
	String dni;
	
}
