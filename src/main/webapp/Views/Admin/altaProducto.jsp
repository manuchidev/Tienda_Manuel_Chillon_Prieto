<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO"
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.DETALLES_EMPLEADO_CSS %>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5 ">
	        
	        	<h2 class="text-center mb-4" style="color: white">ALTA DE PRODUCTO</h2>

				<form action="ProductoAdmin" method="post" enctype="multipart/form-data">
				 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light prodDetalle">	                
						<%
							List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
						
						%>
							<div class="col-md-5">	
							
								<div class="text-center divImagen">
									<img class="card-img-top mb-5 mb-md-0 imgDetalle" id="preview" src="" alt="IMAGEN_PRODUCTO" />
								</div>
								
								<input type="file" id="imagen" name="imagen" onchange="previewImage()" class="mt-2 mb-2">
							</div>
														
							<div class="col-md-7 mt-3">
							
								<div class="row">
									<div class="col-md-3">
										<label for="nombreProdAlta" class="form-label">Nombre:</label>
									</div>
									<div class="col-md-9">								
										<div class="fs-5 mb-2 datosProd">
											<input type="text" class="inputModificar bg-light" name="nombreProdAlta">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-3">
										<label for="precioProdAlta" class="form-label">Precio:</label>
									</div>
									<div class="col-md-9">
										<div class="fs-5 mb-2 datosProd">
											<input type="text" class="inputModificar bg-light" name="precioProdAlta" placeholder="€">
										</div>
									</div>
								</div>
								
								<div class="row mb-2">
									<div class="col-md-3">
										<label for="descripcionProdAlta" class="form-label">Descripción: </label>
									</div>
									<div class="col-md-9">
										<textarea class="inputModificar text-justify bg-light" name="descripcionProdAlta"></textarea>
									</div>
								</div>
								
								<div class="d-flex justify-content-center">

									<div class="row g-3 align-items-center me-3">
									
										<div class="col-auto p-0">
											<label for="impuestoProdAlta" class="col-form-label">Impuesto:</label>
										</div>
										
										<div class="col-auto">
											<input type="text" id="impuestoProdAlta" name="impuestoProdAlta" class="form-control" placeholder="%" style="max-width: 5rem" />
										</div>
										
									</div>
									
									<div class="row g-3 align-items-center">
									
										<div class="col-auto p-0">
											<label for="stockProdAlta" class="col-form-label">Stock:</label>
										</div>
										
										<div class="col-auto">
											<input type="number" id="stockProdAlta" name="stockProdAlta" class="form-control"  min="1" style="max-width: 5rem" />
										</div>
										
									</div>
																		    
								</div>
								
								<div class="row align-items-center mt-3 d-flex justify-content-center">
								
								    <div class="col-auto p-0">
								        <label for="categoriaProdAlta" class="col-form-label">Categoría:</label>
								    </div>
								    
								    <div class="col-auto">
								        <select id="categoriaProdAlta" name="categoriaProdAlta" class="form-control">
								            <%
								                for (CategoriaVO categoria : categorias) {
								            %>
								                <option value="<%=categoria.getId()%>"><%=categoria.getNombre()%></option>
								            <%
								                }
								            %>
								        </select>
								    </div>

								</div>
								
								<div class="d-flex justify-content-center mt-3 mb-2 gap-2">
																    							
									<button type="submit" class="btn btn-success flex-shrink-0" name="accion" value="add"> Añadir Producto</button>
									
								</div>
								
							</div>
								
		                </div>
		                
		            </div>
		            
				</form>
					            
	        </section>	        
		
		</main>
					
		<jsp:include page="<%=Rutas.FOOTER%>" />
		
		<script src="<%=request.getContextPath() %><%= Rutas.PREVIEW_IMAGEN_JS%>"></script>
	
	</body>
	
</html>
