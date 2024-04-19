package curso.java.tienda.model.VO.DetallePedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class DetallePedidoVO {
	
	int id;
	int id_pedido;
	int id_producto;
	float precio_unidad;
	int unidades;
	float impuesto;
	double total;

}
