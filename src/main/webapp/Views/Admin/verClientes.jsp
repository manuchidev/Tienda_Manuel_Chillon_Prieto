<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO" 
 %>	

<!DOCTYPE html>
<html>

	<head>
			
		<title>Login</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
		 		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CLIENTE_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
		
			<% List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios"); %>	
		
			<div class="container-fluid px-2 px-lg-5 mt-4">
			
				<div class="d-flex justify-content-end">

					<form action="ClienteAdmin" method="get">
						<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Cliente</button>
					</form>

				</div>
			
				<h2 class="text-center mb-4" style="color: white">CLIENTES</h2>
				
			<%	 if (usuarios != null && !usuarios.isEmpty()) { %>
					
				
					<div class="row gx-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4">
					
			<%			for (UsuarioVO usuario: usuarios) { %>
			
							<div class="col mb-5">
					
								<div class="card h-100 cardCliente">
					
									<!-- Cliente image-->
									<div class="divUsuarioImg">
										<img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
									</div>
					
									<!-- Cliente details-->
									<div class="card-body p-3">
					
										<div class="text-center">
					
											<!-- Cliente email-->
											<h5 class="fw-bolder"><%= usuario.getEmail()%></h5>
					
											<!-- Cliente name-->
											<p class="p-2 m-0">Nombre: <%= usuario.getNombre()%> <%= usuario.getApellido1() %> <%= usuario.getApellido2() %></p>
											
											<!-- Cliente address-->
											<p class="p-2 m-0">Dirección: <%= usuario.getDireccion()%></p>
											
											<!-- Cliente phone number-->
											<p class="p-2 m-0">Teléfono: <%= usuario.getTelefono()%></p>
										</div>
					
									</div>
					
									<!-- Product actions-->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					
										<div class="text-center">
										
											<form action="ClienteAdmin" method="get">
										
												<input type="hidden" name="idUsuario" value="<%= usuario.getId()%>">
												
												<button type="submit" name="accion" value="edit" class="btn btn-warning mt-auto">Modificar</button>
												<button type="submit" name="accion" value="delete" class="btn btn-danger mt-auto">Dar de Baja</button>
																								
											</form>
				
										</div>
					
									</div>
					
								</div>
							</div>
			
						<% } %>
					</div>
					
				<% } %>
			
			</div>
			
		</main>
		
	    <jsp:include page="<%= Rutas.FOOTER%>" />
	    
	</body>
	
</html>