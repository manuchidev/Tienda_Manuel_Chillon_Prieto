package curso.java.tienda.model.VO.Compra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MetodoPagoVO {

	private int id;
	private String metodo_pago;
}
