<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5">
	        
	            <div class="container px-4 px-lg-5 my-5">
	            
	                <div class="row gx-4 gx-lg-5 align-items-center">
	                
                    <%
                    	ProductoVO producto = (ProductoVO) request.getAttribute("producto");
                    
                    %>
	                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." /></div>
	                    
	                    
	                    <div class="col-md-6">
	                        <h1 class="display-5 fw-bolder"><%= producto.getNombre() %></h1>
	                        
	                        <div class="fs-5 mb-2">
	                            <span><%= producto.getPrecio()%> €</span>
	                        </div>
	                        
	                        <p class="lead"><%= producto.getNombre() %></p>
	                        
	                        <div class="d-flex">
	                            <input class="form-control text-center me-3" id="inputQuantity" type="number" value="1" style="max-width: 3rem" onchange="actualizarCantidadDetalle()" />
	                            <a id="añadirCarrito" href="añadirCarrito?idProd=<%= producto.getId()%>&cantidad=1" data-idprod="<%= producto.getId() %>">
		                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                        				<i class="bi-cart-fill me-1"></i>
	                                	Añadir al carrito
		                            </button>
	                            </a>
	                        </div>
	                        
	                    </div>
	                    
	                </div>
	                
	            </div>
	            
	        </section>
	        
	        <!-- Related items section-->
	        <section class="py-5 bg-light">
	        
	            <div class="container px-4 px-lg-5 mt-5">
	            
	                <h2 class="fw-bolder mb-4">Related products</h2>
	                
	                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	                
	                    <div class="col mb-5">
	                    
	                        <div class="card h-100">
	                            <!-- Product image-->
	                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
	                            
	                            <!-- Product details-->
	                            <div class="card-body p-4">
	                            
	                                <div class="text-center">
	                                    <!-- Product name-->
	                                    <h5 class="fw-bolder">Fancy Product</h5>
	                                    <!-- Product price-->
	                                    $40.00 - $80.00
	                                </div>
	                                
	                            </div>
	                            
	                            <!-- Product actions-->
	                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
	                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
	                            </div>
	                            
	                        </div>
	                        
	                    </div>

	                </div>
	                
	            </div>
	            
	        </section>
		
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
		
			<script src="<%=request.getContextPath() %><%= Rutas.CANTIDAD_DETALLES_JS%>"></script>
	
	</body>
	
</html>
