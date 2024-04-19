package curso.java.tienda.model.VO.Producto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class ProductoVO {
	
	int id;
	int id_categoria;
	String nombre;
	String descripcion;
	double precio;
	int stock;
	Timestamp fecha_alta;
	Timestamp fecha_baja;
	float impuesto;
	String imagen;
	
}
