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
				<form action="ProductoEmpleado" method="post">
				 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light prodDetalle">	                
						<%
							ProductoVO producto = (ProductoVO) request.getAttribute("producto");
							List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
						
						%>
							<div class="col-md-5">
							    <input type="hidden" name="imagenProdModif" value="<%=producto.getImagen()%>">
								<img class="card-img-top mb-5 mb-md-0 imgDetalle" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." />
<%-- 								<img class="card-img-top mb-5 mb-md-0 imgDetalle" id="preview" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." /> --%>
<!-- 								<input type="file" id="imagen" name="imagen" onchange="previewImage()" class="form-control mt-2 mb-2"> -->
							</div>
							
							
							<div class="col-md-7">
								<h1 class="display-5 fw-bolder datosProd">
									<input type="text" class="inputModificar text-center bg-light" name="nombreProdModif" value="<%= producto.getNombre() %>">
								</h1>
								
								<div class="fs-5 mb-2 datosProd">
									<input type="text" class="inputModificar text-center bg-light" name="precioProdModif" value="<%= producto.getPrecio() %>€">
								</div>
								
								<textarea class="inputModificar lead text-justify bg-light" name="descripcionProdModif"><%= producto.getDescripcion()%></textarea>
								
								<div class="d-flex justify-content-center">

									<div class="row g-3 align-items-center me-3">
									
										<div class="col-auto p-0">
											<label for="impuestoProdModif" class="col-form-label">Impuesto:</label>
										</div>
										
										<div class="col-auto">
											<input type="text" id="impuestoProdModif" name="impuestoProdModif" class="form-control" value="<%=producto.getImpuesto()%>%" style="max-width: 5rem" />
										</div>
										
									</div>
									
									<div class="row g-3 align-items-center">
									
										<div class="col-auto p-0">
											<label for="stockProdModif" class="col-form-label">Stock:</label>
										</div>
										
										<div class="col-auto">
											<input type="number" id="stockProdModif" name="stockProdModif" class="form-control" value="<%=producto.getStock()%>" min="1" style="max-width: 5rem" />
										</div>
										
									</div>
																		    
								</div>
								
								<div class="row align-items-center mt-3 d-flex justify-content-center">
								
								    <div class="col-auto p-0">
								        <label for="categoriaProdModif" class="col-form-label">Categoría:</label>
								    </div>
								    
								    <div class="col-auto">
								        <select id="categoriaProdModif" name="categoriaProdModif" class="form-control">
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
								
								<div class="d-flex justify-content-center mt-3 mb-2 gap-2">
								
								    <input type="hidden" name="idProdModif" value="<%=producto.getId()%>">
								    <input type="hidden" name="idCatModif" value="<%=producto.getId_categoria()%>">
								    							
									<button type="submit" class="btn btn-success flex-shrink-0" name="accion" value="edit"> Actualizar Producto </button>
									
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
