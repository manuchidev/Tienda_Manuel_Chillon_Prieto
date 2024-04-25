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

	public DetallePedidoVO(int id_pedido, int id_producto, float precio_unidad, int unidades, float impuesto, double total) {
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = precio_unidad * unidades * (1 + impuesto);
	}

}
