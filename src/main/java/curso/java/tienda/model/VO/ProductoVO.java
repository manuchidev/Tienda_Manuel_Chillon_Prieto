package curso.java.tienda.model.VO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoVO {
	
	int id;
	int id_categoria;
	String nombre;
	String descripcion;
	double precio;
	int stock;
	Date fecha_alta;
	Date fecha_baja;
	float impuesto;
	String imagen;
	int cantidad;

}
