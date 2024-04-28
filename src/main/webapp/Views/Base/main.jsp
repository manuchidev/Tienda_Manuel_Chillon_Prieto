<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO ,curso.java.tienda.service.Producto.ProductoService" 
 %>

<!-- Section-->
<main>

	<section class="py-5 gradient-custom">
	
	    <div class="container-fluid px-2 px-lg-5 mt-5">
	    
	        <div class="row gx-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4">
	        
          	<%
		  		request.setAttribute("productos", ProductoService.getProductos());
		  	
		  		List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
		  	
		  		for (ProductoVO producto: productos) {
  			  			
  			%>
	        
	            <div class="col mb-5">
	            
	                <div class="card h-100 cardProducto">
	                
	                    <!-- Product image-->
	                    <div class="divProductoImg">
		                    <img class="card-img-top imgProd" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." />	                    
	                    </div>
	                    
	                    <!-- Product details-->
	                    <div class="card-body p-4">
	                    
	                        <div class="text-center">
	                        
	                            <!-- Product name-->
	                            <h5 class="fw-bolder"><%= producto.getNombre()%></h5>
	                            
	                            <!-- Product price-->
	                            <p class="p-3 m-0 fw-bolder fs-5"><%= producto.getPrecio()%> €</p>
	                            
	                            <p class="p-0 m-0 fw-light fs-6">Stock: <%= producto.getStock()%> ud.</p>
	                        </div>
	                        
	                    </div>
	                    
	                    <!-- Product actions-->
	                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                    
	                        <div class="text-center">
	                        	<a class="btn btn-primary mt-auto" href="añadirCarrito?idProd=<%= producto.getId()%>">Comprar</a>
	                        	<a class="btn btn-warning mt-auto" href="detalles?idProd=<%= producto.getId()%>&idCat=<%= producto.getId_categoria()%>">Detalles</a>
	                        </div>
	                        
	                    </div>
	                    
	                </div>
	            </div>
	            
           	<%	
  				}
  		
  			%>
	            	            
	        </div>
	    </div>
	    
	</section>
	
</main>
