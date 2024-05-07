<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Categoria.CategoriaVO" 
 %>	

<!DOCTYPE html>
<html>

	<head>
			
		<title>Login</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
		 		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CAT_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>

			<div class="container-fluid px-2 px-lg-5 mt-4">
			
				<div class="d-flex justify-content-end">

					<form action="CategoriaAdmin" method="get">
						<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Categoría</button>
					</form>

				</div>
			
				<h2 class="text-center mb-4" style="color: white">CATEGORÍAS</h2>
			<%		  	          			  	
				List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
			
				if (categorias != null && !categorias.isEmpty()) {
			%>		
					
					<div class="row gx-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4">
			<%		
						for (CategoriaVO categoria: categorias) {
			%>					
							<div class="col mb-5">
					
								<div class="card h-100 cardCategoria">
					
									<!-- Categoria image-->
									<div class="divCategoriaImg">
									</div>
					
									<!-- Categoria details-->
									<div class="card-body p-4">
					
										<div class="text-center">
					
											<!-- Category name-->
											<h5 class="fw-bolder"><%= categoria.getNombre()%></h5>
					
											<!-- Category description-->
											<p class="p-2 m-0 fs-5"><%= categoria.getDescripcion()%>
											</p>
										</div>
					
									</div>
					
									<!-- Product actions-->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					
										<div class="text-center">
										
											<form action="CategoriaAdmin" method="get">
										
												<input type="hidden" name="idCat" value="<%= categoria.getId()%>">
												
												<button type="submit" name="accion" value="edit" class="btn btn-warning mt-auto">Modificar</button>
												<button type="submit" name="accion" value="delete" class="btn btn-danger mt-auto">Dar de Baja</button>
																								
											</form>
			
										</div>
					
									</div>
					
								</div>
							</div>
			
					<%  } %>
					
					</div>
					
				<%	} %>
			
			</div>
			
		</main>
		
	    <jsp:include page="<%= Rutas.FOOTER%>" />
	    
	</body>
	
</html>