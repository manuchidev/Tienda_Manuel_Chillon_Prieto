<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas,curso.java.tienda.model.VO.Producto.ProductoVO,curso.java.tienda.service.Producto.ProductoService" 
 %>

<div class="container">
    
  <div class="row">
  
  	<%
  		request.setAttribute("productos", ProductoService.getProductos());
  	
  		List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
  	
  		for (ProductoVO producto: productos) {
  			  			
  	%>
	  	<div class="col-sm-4">
	    
	      <div class="panel panel-primary">
	      
	        <div class="panel-heading textoCard nombreCard"><%= producto.getNombre()%></div>
	        
	        <div class="panel-body"><img src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" class="img-responsive imgProd" style="width:100%" alt="Image"></div>
	        
	        <div class="panel-footer textoCard">
	        	<p>Precio: <%= producto.getPrecio()%> €</p>
	        	<p class="stock">Stock: <%= producto.getStock()%></p>	       	       
	        </div>
	        
	        <div class="panel-footer textoCard">
	        	<a href="añadirCarrito?idProd=<%=producto.getId()%>">
	        		<button class="btn btn-danger">Comprar</button>
	        	</a>
	        </div>
	        
	      </div>
	      
	     </div>
  				
  	<%	
  		}
  		
  	%>
  
    </div>
    
</div>
