package curso.java.tienda.model.VO.Compra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProvinciasLocalidadesVO {
	
	public static List<String> getAllLocalidades() {
		
        Map<String, List<String>> provinciasLocalidades = getProvinciasLocalidades();
        List<String> todasLasLocalidades = new ArrayList<>();

        // Recorrer todas las provincias y agregar sus localidades a la lista todasLasLocalidades
        for (List<String> localidades : provinciasLocalidades.values()) {
            todasLasLocalidades.addAll(localidades);
        }

        return todasLasLocalidades;
    }
	
    private static Map<String, List<String>> getProvinciasLocalidades() {
    	
        Map<String, List<String>> provinciasLocalidades = new HashMap<>();

        // Definir las principales provincias y localidades de España
        provinciasLocalidades.put("Madrid", Arrays.asList(
            "Madrid", "Alcalá de Henares", "Móstoles", "Fuenlabrada", "Leganés",
            "Getafe", "Alcorcón", "Torrejón de Ardoz", "Parla", "Alcobendas"
        ));
        provinciasLocalidades.put("Barcelona", Arrays.asList(
            "Barcelona", "Hospitalet de Llobregat", "Badalona", "Terrassa", "Sabadell",
            "Mataró", "Santa Coloma de Gramenet", "Cornellà de Llobregat", "Sant Boi de Llobregat", "Manresa"
        ));
        provinciasLocalidades.put("Valencia", Arrays.asList(
            "Valencia", "Torrent", "Gandía", "Paterna", "Sagunto",
            "Alzira", "Mislata", "Burjassot", "Ontinyent", "Xàtiva"
        ));
        provinciasLocalidades.put("Sevilla", Arrays.asList(
            "Sevilla", "Dos Hermanas", "Alcalá de Guadaíra", "Utrera", "Écija",
            "Mairena del Aljarafe", "Los Palacios y Villafranca", "Carmona", "La Rinconada", "Tomares"
        ));
        provinciasLocalidades.put("Alicante", Arrays.asList(
            "Alicante", "Elche", "Benidorm", "Torrevieja", "Orihuela",
            "Alcoy", "San Vicente del Raspeig", "Elda", "Villena", "Denia"
        ));
        // Puedes añadir más provincias y localidades según sea necesario...

        return provinciasLocalidades;
    }

}
