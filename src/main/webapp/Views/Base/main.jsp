<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO, 
	curso.java.tienda.service.Producto.ProductoService, curso.java.tienda.service.Categoria.CategoriaService" 
 %>

<!-- Section-->
<main>

	<section class="py-5 gradient-custom">
	
	<%
		List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
		List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
		List<ProductoVO> productosFiltrados = (List<ProductoVO>) request.getAttribute("productosFiltrados");
		List<ProductoVO> productosCategoria = (List<ProductoVO>) request.getAttribute("productosCategoria");		
		
		if (productosFiltrados != null && !productosFiltrados.isEmpty()) {
	%>
			<jsp:include page="<%= Rutas.FILTROS_JSP%>" />	
	    	<jsp:include page="<%= Rutas.PRODUCTOS_FILTRADOS_JSP%>" />
	<%
		} else if (productosCategoria != null && !productosCategoria.isEmpty()) {			
	%>
			<jsp:include page="<%= Rutas.PRODUCTOS_CATEGORIAS_JSP%>" />
	<%
		} else {		
	%>
			<jsp:include page="<%= Rutas.FILTROS_JSP%>" />	
	    	<jsp:include page="<%= Rutas.PRODUCTOS_JSP%>" />    
	<%
		}
	
	%>	
	    
	</section>
	
</main>
