<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO, 
	curso.java.tienda.service.Producto.ProductoService, curso.java.tienda.service.Categoria.CategoriaService" 
 %>
 
<%
	List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
	List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");	
%>

<div class="container text-center">

	<form action="productos" method="get">

		<div class="row justify-content-center divFiltros">

			<div class="col-md-4 mb-3">

				<label for="precio" class="lblFiltro">Precio:</label>
				<!-- 						<input type="number" id="precio" name="precio" min="0"> -->

				<select id="precio" name="precio">
					<option value="Todos">Seleccione una opcion</option>
					<!-- Aquí puedes agregar las categorías disponibles -->
					<option value="min">Precio Más Barato</option>
					<option value="max">Precio Más Caro</option>
					<option value="50">Hasta 50€</option>
					<option value="100">Hasta 100€</option>
					<option value="500">Hasta 500€</option>
					<option value="1000">Hasta 1000€</option>
					<option value="3000">Hasta 3000€</option>
				</select>

			</div>

			<div class="col-md-4 mb-3">

				<label for="categoria" class="lblFiltro">Categoría:</label> 
				<select id="categoria" name="categoria">
					<option value="Todas">Seleccione una opcion</option>
					<%
							for (CategoriaVO categoria: categorias) {
							%>
					<option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
					<%							
							}
							%>

				</select>

			</div>

			<div class="col-md-4">
				<input type="submit" value="Filtrar Productos">
			</div>

		</div>

	</form>

</div>