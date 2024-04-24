package curso.java.tienda.model.VO.Pedido;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class PedidoVO {
	
	private int id;
	private int id_usuario;
	private Timestamp fecha;
	private String metodo_pago;
	private String estado;
	private String num_factura;
	private double total;
	
	public PedidoVO(int id_usuario, Timestamp fecha, String metodo_pago) {
		this.id_usuario = id_usuario;
		this.fecha = fecha;
		this.metodo_pago = metodo_pago;
		this.estado = "PE";
	}

}
