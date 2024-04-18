package curso.java.tienda.model.VO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class PedidoVO {
	
	int id;
	int id_usuario;
	Timestamp fecha;
	String metodo_pago;
	String estado;
	String num_factura;
	double total;

}
