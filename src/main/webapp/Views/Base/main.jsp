<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO, 
	curso.java.tienda.model.VO.Usuario.UsuarioVO"
%> 

<!-- Section-->
<main>

	<% if (request.getAttribute("mensajeError") != null) { %>
		<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
			<%= request.getAttribute("mensajeError") %>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	<% } %>
	
	<section class="py-5 gradient-custom">
		
	<%
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
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
