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
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5 ">
	        											
    			<h2 class="text-center mb-4" style="color: white">ACTUALIZACIÓN DE CATEGORÍA</h2>
	        											
				<form action="CategoriaEmpleado" method="post">
				 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light catDetalle">	                
						<%
							CategoriaVO categoria = (CategoriaVO) request.getAttribute("categoria");
						
						%>
<!-- 							<div class="col-md-5"> -->
<%-- 							    <input type="hidden" name="imagenProdModif" value="<%=categoria.getImagen()%>"> --%>
<%-- 								<img class="card-img-top mb-5 mb-md-0 imgDetalle" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." /> --%>
<%-- 								<img class="card-img-top mb-5 mb-md-0 imgDetalle" id="preview" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." /> --%>
<!-- 								<input type="file" id="imagen" name="imagen" onchange="previewImage()" class="form-control mt-2 mb-2"> -->
<!-- 							</div> -->
							
							
							<div class="col-md-12 mt-2">
							
								<label>Nombre: </label>
								<h2 class="fw-bolder datosCat">
									<input type="text" class="inputModificar text-center bg-light" name="nombreCatModif" value="<%= categoria.getNombre() %>">
								</h2>
								
								<label>Descripción: </label>
								<div class="fs-5 mb-2 datosCat">
									<input type="text" class="inputModificar text-center bg-light" name="descripcionCatModif" value="<%= categoria.getDescripcion() %>">
								</div>
																															
								<div class="d-flex justify-content-center mt-3 mb-2 gap-2">
								
								    <input type="hidden" name="idCatModif" value="<%=categoria.getId()%>">
								    							
									<button type="submit" class="btn btn-success flex-shrink-0" name="accion" value="edit"> Actualizar Categoria </button>
									<button type="submit" name="accion" value="delete" class="btn btn-danger mt-auto">Dar de Baja</button>
									
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
