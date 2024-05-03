<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO"
%>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.DETALLES_EMPLEADO_CSS %>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_EMPLEADO%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5 ">
	        											<!-- enctype="multipart/form-data" -->
				<form action="modificarProducto" method="post">
				 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light prodDetalle">	                
						<%
							ProductoVO producto = (ProductoVO) request.getAttribute("producto");
							List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
						
						%>
							<div class="col-md-5">
							    <input type="hidden" name="imagenProd" value="<%=producto.getImagen()%>">
								<img class="card-img-top mb-5 mb-md-0 imgDetalle" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." />
<%-- 								<img class="card-img-top mb-5 mb-md-0 imgDetalle" id="preview" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." /> --%>
<!-- 								<input type="file" id="imagen" name="imagen" onchange="previewImage()" class="form-control mt-2 mb-2"> -->
							</div>
							
							
							<div class="col-md-7">
								<h1 class="display-5 fw-bolder datosProd">
									<input type="text" class="inputModificar text-center bg-light" name="nombreProd" value="<%= producto.getNombre() %>">
								</h1>
								
								<div class="fs-5 mb-2 datosProd">
									<input type="text" class="inputModificar text-center bg-light" name="precioProd" value="<%= producto.getPrecio() %>€">
								</div>
								
								<textarea class="inputModificar lead text-justify bg-light" name="descripcionProd"><%= producto.getDescripcion()%></textarea>
								
								<div class="d-flex justify-content-center">

									<div class="row g-3 align-items-center me-3">
									
										<div class="col-auto p-0">
											<label for="impuestoProd" class="col-form-label">Impuesto:</label>
										</div>
										
										<div class="col-auto">
											<input type="text" id="impuestoProd" name="impuestoProd" class="form-control" value="<%=producto.getImpuesto()%>%" style="max-width: 5rem" />
										</div>
										
									</div>
									
									<div class="row g-3 align-items-center">
									
										<div class="col-auto p-0">
											<label for="stockProd" class="col-form-label">Stock:</label>
										</div>
										
										<div class="col-auto">
											<input type="number" id="stockProd" name="stockProd" class="form-control" value="<%=producto.getStock()%>" min="1" style="max-width: 5rem" />
										</div>
										
									</div>
																		    
								</div>
								
								<div class="row align-items-center mt-3 d-flex justify-content-center">
								
								    <div class="col-auto p-0">
								        <label for="categoria" class="col-form-label">Categoría:</label>
								    </div>
								    
								    <div class="col-auto">
								        <select id="categoriaProd" name="categoriaProd" class="form-control">
								            <%
								                for (CategoriaVO categoria : categorias) {
								            %>
								                <option value="<%=categoria.getId()%>" <%=categoria.getId() == (producto.getId_categoria()) ? "selected" : ""%>><%=categoria.getNombre()%></option>
								            <%
								                }
								            %>
								        </select>
								    </div>

								</div>
								
								<div class="d-flex justify-content-center mt-3">
								
								    <input type="hidden" name="idProd" value="<%=producto.getId()%>">
								    <input type="hidden" name="idCat" value="<%=producto.getId_categoria()%>">								
									<button class="btn btn-outline-dark flex-shrink-0" type="submit">
										Modificar Producto
									</button>
									<button class="btn btn-danger flex-shrink-0" type="submit">
										Eliminar Producto
									</button>
								</div>
								
							</div>
								
		                </div>
		                
		            </div>
		            
				</form>
	            
	        </section>
	        
	        <!-- Related items section-->
	        <section class="prodRelac">
	        
	            <div class="container py-3">
	            
	                <h2 class="fw-bolder mb-4">Productos Relacionados</h2>
	                
	                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	                
                    <%
                    	List<ProductoVO> productosCategoriaDetalles = (List<ProductoVO>) request.getAttribute("productosCategoriaDetalles");
                    
                        for(ProductoVO productoCategoria : productosCategoriaDetalles) {
                    
                    %>	           
		                    <div class="col mb-5">
		                    
		                        <div class="card h-100 cardProdRelac">
		                            <!-- Product image-->
		                            <img class="card-img-top imgProdRelac" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= productoCategoria.getImagen() %>" alt="..." />
		                            
		                            <!-- Product details-->
		                            <div class="card-body p-4">
		                            
		                                <div class="text-center">
		                                    <!-- Product name-->
		                                    <h5 class="fw-bolder"><%= productoCategoria.getNombre() %></h5>
		                                    <!-- Product price-->
		                                    <%= productoCategoria.getPrecio() %> €
		                                </div>
		                                
		                            </div>
		                            
		                            <!-- Product actions-->
		                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
		                                <div class="text-center">
		                                	<a class="btn btn-warning mt-auto" href="detalles?idProd=<%= productoCategoria.getId()%>&idCat=<%= productoCategoria.getId_categoria()%>">Detalles</a>
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
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
		
		<script src="<%=request.getContextPath() %><%= Rutas.PREVIEW_IMAGEN_JS%>"></script>
	
	</body>
	
</html>
