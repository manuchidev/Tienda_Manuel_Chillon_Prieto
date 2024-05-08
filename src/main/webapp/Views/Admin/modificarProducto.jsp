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
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
		
			<!-- Product section-->
	        <section class="py-5 ">
	        											
	        	<h2 class="text-center mb-4" style="color: white">ACTUALIZACIÓN DE PRODUCTO</h2>
	        	
				<form action="modificarProducto" method="post">
								 
	            	<div class="container px-4 px-lg-5 my-3 d-flex justify-content-center align-items-center">
	            
	                	<div class="row gx-4 gx-lg-5 bg-light prodDetalle">	                
						<%
							ProductoVO producto = (ProductoVO) request.getAttribute("producto");
							List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
						
						%>
							<div class="col-md-5">
							    <input type="hidden" name="imagenProdModif" value="<%=producto.getImagen()%>">							    
								<img class="card-img-top mb-5 mb-md-0 imgDetalle" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="IMAGEN_PRODUCTO" />
							</div>
														
							<div class="col-md-7 mt-2">
								<h2 class="fw-bolder datosProd">
									<input type="text" class="inputModificar text-center bg-light" name="nombreProdModif" value="<%= producto.getNombre() %>">
								</h2>
								
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
<%-- 								    <input type="hidden" name="idCategoria" value="<%=producto.getId_categoria()%>"> --%>
								    							
									<button type="submit" class="btn btn-success flex-shrink-0" name="accion" value="edit"> Actualizar Producto </button>
									
								</div>
								
							</div>
								
		                </div>
		                
		            </div>
		            
				</form>
				
				<table class= "table bg-light mt-5" border="1">
				
					<thead>
					
						<tr>
							<th colspan="8" class="text-center bg-secondary p-3">LISTADO DE PRODUCTOS</th>
						</tr>
					
						<tr class="text-center bg-info p-3">						
						    <th>Nombre</th>
							<th>Precio</th>
							<th>Descripción</th>
							<th>Impuesto</th>
							<th>Stock</th>
							<th>Imagen</th>
							<th>Acciones</th>						
						</tr>
							
					</thead>
					
					<tbody>
					
					<%
					    List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
					
					    if (productos != null) {
					    	
					        for (ProductoVO prod : productos) {
					%>
					            <tr class="text-center">
					                <td><%= prod.getNombre() %></td>
					                <td><%= prod.getPrecio() %>€</td>
					                <td><%= prod.getDescripcion() %></td>
					                <td><%= prod.getImpuesto() %>%</td>
					                <td><%= prod.getStock() %></td>
					                <td><img src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= prod.getImagen() %>" height="50" width="50"></td>
					                <td>
										<form action="ProductoAdmin" method="get">
											<input type="hidden" name="accion" value="edit">
											<input type="hidden" name="idProducto" value="<%= prod.getId() %>">
											
											<input type="submit" value="Editar">
										</form>
					                </td>
					            </tr>
					<%
					        }
					    }
					%>
					
					
					</tbody>
								
				</table>
	            
	        </section>	        
		
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
		
	</body>
	
</html>
