package curso.java.tienda.model.VO.DetallePedido;

import java.math.BigDecimal;

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
	private BigDecimal precio_unidad;
	private int unidades;
	private BigDecimal impuesto;
	private BigDecimal total;

	public DetallePedidoVO(int id_pedido, int id_producto, BigDecimal precio_unidad, int unidades, BigDecimal impuesto, BigDecimal total) {
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		
		BigDecimal totalSinImpuesto = precio_unidad.multiply(BigDecimal.valueOf(unidades));
		BigDecimal totalImpuesto = totalSinImpuesto.multiply(impuesto.add(BigDecimal.ONE));
		
		this.total = totalSinImpuesto.add(totalImpuesto) ;
	}

}
