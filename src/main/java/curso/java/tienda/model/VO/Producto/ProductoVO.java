package curso.java.tienda.model.VO.Producto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class ProductoVO {
	
	private int id;
	private int id_categoria;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private Timestamp fecha_alta;
	private Timestamp fecha_baja;
	private float impuesto;
	private String imagen;
	
}
