package curso.java.tienda.model.VO.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoriaVO {

	private int id;
	private String nombre;
	private String descripcion;
}
