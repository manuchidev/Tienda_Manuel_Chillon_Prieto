<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.model.VO.ProductoVO, curso.java.tienda.service.ProductoService" 
 %>
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    
  <div class="row">
  	 	
  	<c:choose>
  	
  		<c:when test="${not empty requestScope.productos}">
  	
			<c:forEach var="producto" items="${param.productos}">
						
			  	<div class="col-sm-4">
			    
			      <div class="panel panel-primary">
			      
			        <div class="panel-heading textoCard nombreCard">${producto.nombre}</div>
			        
			        <div class="panel-body"><img src="<%=request.getContextPath() %>${producto.ruta_img}" class="img-responsive imgProd" style="width:100%" alt="Image"></div>
			        
			        <div class="panel-footer textoCard">
			        	<p>Precio: ${producto.precio} €</p>
			        	<p class="stock">Stock: ${producto.stock}</p>	       	       
			        </div>
			        
			        <div class="panel-footer textoCard">
			        	<a href="añadirCarrito?idProd=${producto.id}">
			        		<button class="btn btn-danger">Comprar</button>
			        	</a>
			        </div>
			        
			      </div>
			      
			     </div>
		  				
			</c:forEach>
			
		</c:when>
		
		<c:otherwise>
		
			<div class="col-sm-12">
	          <p style="color:red; text-align: center">No se encontraron productos</p>
	        </div>
	        
		</c:otherwise>
			
	</c:choose>
 
  
    </div>
    
</div>

<!-- <br> -->

<!-- <div class="container">  -->
   
<!--   <div class="row"> -->
  
<!--     <div class="col-sm-4"> -->
    
<!--       <div class="panel panel-primary"> -->
      
<!--         <div class="panel-heading">PRODUCTO</div> -->
<!--         <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> -->
<!--         <div class="panel-footer">Precio</div> -->
        
<!--       </div> -->
      
<!--     </div> -->
    
<!--     <div class="col-sm-4">  -->
    
<!--       <div class="panel panel-primary"> -->
      
<!--         <div class="panel-heading">PRODUCTO</div> -->
<!--         <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> -->
<!--         <div class="panel-footer">Precio</div> -->
        
<!--       </div> -->
      
<!--     </div> -->
    
<!--     <div class="col-sm-4">  -->
    
<!--       <div class="panel panel-primary"> -->
      
<!--         <div class="panel-heading">PRODUCTO</div> -->
<!--         <div class="panel-body"><img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div> -->
<!--         <div class="panel-footer">Precio</div> -->
        
<!--       </div> -->
      
<!--     </div> -->
    
<!--   </div> -->
  
<!-- </div> -->

<br><br>