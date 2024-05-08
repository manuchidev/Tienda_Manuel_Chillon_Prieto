package curso.java.tienda.service.Categoria;

import java.util.List;

import curso.java.tienda.model.DAO.Categoria.CategoriaDAO;
import curso.java.tienda.model.VO.Categoria.CategoriaVO;

public class CategoriaService {
	
	public static List<CategoriaVO> getCategorias() {
		
		List<CategoriaVO> categorias = CategoriaDAO.findAll();
		
		return categorias;
	}
	
	public static CategoriaVO getCategoria(int id) {
		
		CategoriaVO categoria = CategoriaDAO.findById(id);
		
		return categoria;
	}
	
	public static void altaCategoria(CategoriaVO categoria) {

		CategoriaDAO.insert(categoria);
	}
	
	public static CategoriaVO modificarCategoria(CategoriaVO categoria) {

		CategoriaVO categoriaActualizada = CategoriaDAO.update(categoria);
		
		return categoriaActualizada;
	}
	
	public static void bajaCategoria(int id) {

		CategoriaDAO.delete(id);
	}

}
