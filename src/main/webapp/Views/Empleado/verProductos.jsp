<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO" 
 %>


<!DOCTYPE html>
<html>

	<head>
			
		<title>Login</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
		 		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_EMPLEADO%>" />
		
		<main>

			<div class="container-fluid px-2 px-lg-5 mt-4">
			
				<div class="d-flex justify-content-end">

					<form action="ProductoEmpleado" method="get">
						<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Producto</button>
					</form>

				</div>
			
				<h2 class="text-center mb-4" style="color: white">PRODUCTOS</h2>
			
			<%		  	          			  	
				List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
				List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");	
			
				if (categorias != null && !categorias.isEmpty() && productos != null && !productos.isEmpty()) {
					
					for (CategoriaVO categoria: categorias) {
			%>
						<h2 class="text-center mb-4" style="color: white"><%= categoria.getNombre()%></h2>
					
						<div class="row gx-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4">
						
			<%										
							for (ProductoVO producto: productos) {
				  					
				  				if (producto.getId_categoria() == categoria.getId()) {  			  			
			%>
									<div class="col mb-5">
							
										<div class="card h-100 cardProducto">
							
											<!-- Product image-->
											<div class="divProductoImg">
												<img class="card-img-top imgProd"
													src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>"
													alt="..." />
											</div>
							
											<!-- Product details-->
											<div class="card-body p-4">
							
												<div class="text-center">
							
													<!-- Product name-->
													<h5 class="fw-bolder"><%= producto.getNombre()%></h5>
							
													<!-- Product price-->
													<p class="p-3 m-0 fw-bolder fs-5"><%= producto.getPrecio()%>€
													</p>
							
													<p class="p-0 m-0 fw-light fs-6">
														Stock:
														<%= producto.getStock()%> ud.
													</p>
												</div>
							
											</div>
							
											<!-- Product actions-->
											<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
							
												<div class="text-center">
												
													<form action="ProductoEmpleado" method="get">
												
														<input type="hidden" name="idProd" value="<%= producto.getId()%>">
														<input type="hidden" name="idCat" value="<%= producto.getId_categoria()%>">
														
														<button type="submit" name="accion" value="edit" class="btn btn-warning mt-auto">Modificar</button>
														
													</form>
												
												</div>
							
											</div>
							
										</div>
									</div>
				<%	
								}
							}
			    %>
							
						</div>
				<%
						}
					}
				%>
			
			</div>
			
		</main>
		
	    <jsp:include page="<%= Rutas.FOOTER%>" />
	    
	</body>
	
</html>
	    
	    