<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Perfil</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PERFIL_CSS%>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
			<% 
				UsuarioVO usuario = (UsuarioVO)session.getAttribute("usuario");			
			%>
		
			<section class="gradient-custom py-4">

				<div class="container rounded bg-dark">
				
					<div class="row">
					
						<div class="col-md-3 divIzq">
						
							<div class="d-flex flex-column align-items-center text-center p-3 py-5">
								<img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
								<span class="font-weight-bold text-white"><%= usuario.getNombre() %></span>							
								<span class="text-white-50"><%= usuario.getEmail() %></span>
							</div>
							
						</div>
						
						<div class="col-md-5 divCentral">
						
							<div class="p-3 py-3">
							
								<div class="d-flex justify-content-center align-items-center mb-3">
									<h4 class="text-center text-white">Datos del Usuario</h4>
								</div>
								
								<form action="Perfil" method="post">
								
									<div class="row">
									
										<div class="col-md-6">
											<label class="labels">Nombre</label>
											<input type="text" class="form-control" value="<%= usuario.getNombre()%>" name="nombrePerfil">
										</div>
										
										<div class="col-md-6">
											<label class="labels">Primer Apellido</label>
											<input type="text" class="form-control" value="<%= usuario.getApellido1()%>" name="apellido1Perfil">
										</div>
										
										<div class="col-md-6 mt-3">
											<label class="labels">Segundo Apellido</label>
											<input type="text" class="form-control" value="<%= usuario.getApellido2()%>" name="apellido2Perfil">
										</div>
										
										<div class="col-md-6 mt-3">
											<label class="labels">DNI</label>
											<input type="text" class="form-control noEditable" value="<%= usuario.getDni()%>" readonly>
										</div>
																		
										<div class="col-md-6 mt-3">
											<label class="labels">Email</label>
											<input type="text" class="form-control noEditable" value="<%= usuario.getEmail()%>" readonly>
										</div>
																				
										<div class="col-md-6 mt-3">
											<label class="labels">Teléfono</label>
											<input type="text" class="form-control" value="<%= usuario.getTelefono()%>" name="telefonoPerfil">
										</div>
										
										<div class="col-md-6 mt-3">
											<label class="labels">Dirección</label>
											<input type="text" class="form-control" value="<%= usuario.getDireccion()%>" name="direccionPerfil">
										</div>
																																																																						
										<div class="col-md-6 mt-3">
											<label class="labels">Provincia</label>
											<input type="text" class="form-control" value="<%= usuario.getProvincia()%>" name="provinciaPerfil">
										</div>
										
										<div class="col-md-6 mt-3">
											<label class="labels">Localidad</label>
											<input type="text" class="form-control" value="<%= usuario.getLocalidad()%>" name="localidadPerfil">
										</div>
										
									</div>
									
									<div class="mt-5 text-center">
										<input type="hidden" name="idPerfil" value="<%= usuario.getId()%>">
										<button type="submit" class="btn btn-outline-light btn-lg px-5" name="accion" value="guardarPerfil">Guardar Perfil</button>
									</div>
									
								</form>							
								
							</div>
							
						</div>
						
						<div class="col-md-4">
						
							<div class="p-3 py-5">
							
								<form action="Perfil" method="post">
								
									<div class="d-flex justify-content-center align-items-center">
										<span class="text-white">Cambio de Contraseña</span>
									</div>
																	
									<div class="col-md-12 mt-3">
										<label class="labels">Contraseña Actual</label>
										<input type="password" class="form-control" name="claveActual">
									</div>
																
									<div class="col-md-12 mt-3">
										<label class="labels">Nueva Contraseña</label>
										<input type="password" class="form-control" name="claveNueva">
									</div>
											
									<div class="col-md-12 mt-4 text-center">
										<button type="submit" class="btn btn-outline-light btnCambioClave" name="accion" value="cambioClave">
	                                        <i class="fa fa-key"></i>&nbsp;Cambiar Contraseña							
										</button>
									</div>
														
								</form>
																														
							</div>
						
<!-- 						<div class="col-md-4"> -->
						
<!-- 							<div class="p-3 py-5"> -->
							
<!-- 								<div class="d-flex justify-content-between align-items-center experience"> -->
<!-- 									<span class="text-white">Tarjeta de Crédito</span> -->
<!-- 									<span class="border px-3 p-1 add-experience"> -->
<!-- 										<i class="fa fa-plus"></i>&nbsp;Añadir Tarjeta -->
<!-- 									</span> -->
<!-- 								</div> -->
								
<!-- 								<br> -->
								
<!-- 								<div class="col-md-12"> -->
<!-- 									<label class="labels">Titular</label> -->
<%-- 									<input type="text" class="form-control" value="<%=usuario.getNombre()%> <%=usuario.getApellido1()%> <%=usuario.getApellido2()%>"> --%>
<!-- 								</div> -->
								
<!-- 								<br> -->
								
<!-- 								<div class="col-md-12"> -->
<!-- 									<label class="labels">IBAN</label> -->
<!-- 									<input type="text" class="form-control" value=""> -->
<!-- 								</div> -->
								
<!-- 								<br> -->
								
<!-- 								<div class="row"> -->
								
<!-- 									<div class="col-md-6"> -->
<!-- 										<label class="labels">Fecha de Expedición</label> -->
<!-- 										<input type="date" class="form-control" placeholder="additional details" value=""> -->
<!-- 									</div> -->
									
<!-- 									<div class="col-md-6"> -->
<!-- 										<label class="labels">CVV</label> -->
<!-- 										<input type="password" class="form-control" value=""> -->
<!-- 									</div> -->
									
<!-- 								</div> -->
																								
<!-- 							</div> -->
							
						</div>
						
					</div>
					
				</div>
	
			</section>
		
		</main>
		
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>