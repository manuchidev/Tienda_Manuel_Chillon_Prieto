package curso.java.tienda.model.VO.DetallePedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class DetallePedidoVO {
	
	private int id;
	private int id_pedido;
	private int id_producto;
	private float precio_unidad;
	private int unidades;
	private float impuesto;
	private double total;

}
