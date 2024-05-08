<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Categoria.CategoriaVO"
%>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CATEGORIAS_EMPLEADO_CSS %>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_EMPLEADO%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5 ">
	        											
        		<h2 class="text-center mb-4" style="color: white">ALTA DE CATEGORÍA</h2>
	        											
				<form action="CategoriaEmpleado" method="post" enctype="multipart/form-data">
				 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light catDetalle">	                			
												
							<div class="col-md-6">	
							
								<div class="text-center divImagen">
									<img class="card-img-top mb-5 mb-md-0 imgDetalle" id="preview" src="" alt="IMAGEN_CATEGORIA" />
								</div>
								
								<input type="file" id="imagen" name="imagen" onchange="previewImage()" class="mt-2 mb-2">
							</div>
														
							<div class="col-md-6 mt-2">
							
								<label>Nombre: </label>
								<h2 class="fw-bolder datosCat">
									<input type="text" class="inputModificar text-center bg-light" name="nombreCatAlta">
								</h2>
								
								<label>Descripción: </label>
								<div class="fs-5 mb-2 datosCat">
									<input type="text" class="inputModificar text-center bg-light" name="descripcionCatAlta">
								</div>
																															
								<div class="d-flex justify-content-center mt-3 mb-2 gap-2">
																    							
									<button type="submit" class="btn btn-success flex-shrink-0" name="accion" value="add"> Añadir Categoria </button>
									
								</div>
								
							</div>

		                </div>
		                
		            </div>
		            
				</form>
	            
	        </section>	        
		
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
		
		<script src="<%=request.getContextPath() %><%= Rutas.PREVIEW_IMAGEN_JS%>"></script>
	
	</body>
	
</html>
