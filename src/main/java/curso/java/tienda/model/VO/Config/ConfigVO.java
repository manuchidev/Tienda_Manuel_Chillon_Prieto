package curso.java.tienda.model.VO.Config;

import lombok.*;

@Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ConfigVO {
	
	private int id;
	private String clave;
	private String valor;
	private String tipo;
	
	public ConfigVO(String valor) {
		this.valor = valor;
    }
	
}
