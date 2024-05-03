<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Login</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
		 
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CONTACTO_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
					
			<section>

			<% if (request.getAttribute("mensajeExito") != null) { %>
				<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
					<%= request.getAttribute("mensajeExito") %>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			<% } %>

			<% if (request.getAttribute("mensajeError") != null) { %>
				<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
					<%= request.getAttribute("mensajeError") %>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			<% } %>
			
				<div class="container">
				
					<div class="row justify justify-content-center">
					
						<div class="col-11 col-md-8 col-lg-6 col-xl-5">
						
							<form action="Contacto" method="post">
							
								<div class="card bg-dark">
							
									<div class="row mt-0">
										<div class="col-md-12 mb-3">
											<h4 class="text-center">Contacte con Nosotros</h4>
										</div>
									</div>
								
									<div class="form-group row mb-3">
										<div class="col-md-12 mb-0">
											<p class="mb-1">Email</p> 
											
											<%
												UsuarioVO usuario = (UsuarioVO) request.getSession().getAttribute("usuario");
												
												if (usuario != null) {
											%>	
													<input type="text" id="emailContactoLogueado"  name="emailContacto" value="<%= usuario.getEmail() %>" class="form-control input-box rm-border" readonly>
											<%	
												} else {
											%>	
													<input type="text" id="emailContacto"  name="emailContacto" placeholder="Introduzca su email" class="form-control input-box rm-border">
											<%	
												}											
											%>
																																
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-md-12 mb-3">
											<p class="mb-1">Asunto</p> 
											<input type="text" id="asuntoContacto"  name="asuntoContacto" placeholder="Introduzca el asunto" class="form-control input-box rm-border">
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-md-12 mb-3">
											<p class="mb-1">Mensaje</p> 
											<textarea id="mensajeContacto" type="text" placeholder="Introduzca su mensaje" name="mensajeContacto" class="form-control input-box rm-border" rows="5"></textarea>
										</div>
									</div>
									
									<div class="form-group row justify-content-center mb-0">
										<div class="col-md-12 px-3 mt-3 text-center"> 
											<input type="submit" value="Enviar" class="btn btn-outline-light btn-lg px-5">
										</div>
									</div>
										
							 	</div>
							
							</form>
							
						</div>
					
					</div>
				
				</div>
				
			</section> 
		
		</main>
		
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>