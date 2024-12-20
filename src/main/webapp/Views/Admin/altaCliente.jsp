<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Registro</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
		 
		 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.REGISTRO_CSS%>">
	  	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV_ADMIN%>" />
		
		<main>
		
			<%
				HashMap<String, String> errores = (HashMap<String, String>)request.getAttribute("errores");
			%>
		
			<section class="gradient-custom">
			
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
			  
			    <div class="container py-5">
			    
			      <div class="row d-flex justify-content-center align-items-center">
			      
			        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
			        
			          <div class="card bg-dark text-white" style="border-radius: 1rem;">
			          
			            <div class="card-body p-4 text-center">
			            
			            	<div class="mb-0">
			            
				              <h2 class="text-uppercase text-center mb-5">ALTA DE CLIENTE</h2>
				
				              <form novalidate action="ClienteAdmin" method="post" id="formRegistro" class="form-container" onsubmit="return validarRegistro(event)">
								
								<div class="form-row">
								
							        <div class="form-item">
							            <label class="form-label" for="emailRegistro">Email</label>
							            <input type="email" id="emailRegistro" name="emailRegistro" class="form-control form-control-lg" value="<%= request.getAttribute("emailRegistro") != null ? request.getAttribute("emailRegistro") : ""%>" required/>
							            							         							            
							            <span name="errorEmail" id="errorEmail" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorEmail")) {
							            %>		
							            		<%= errores.get("errorEmail") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        <div class="form-item">
							            <label class="form-label" for="password">Contraseña</label>
							            <input type="password" id="passwordRegistro" name="passwordRegistro" class="form-control form-control-lg" required/>
							            
							            <span name="errorPassword" id="errorPassword" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorPassword")) {
							            %>		
							            		<%= errores.get("errorPassword") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							    </div>
							    
							    <div class="form-row">
							    
							        <div class="form-item">
							            <label class="form-label" for="password2">Repetir Contraseña</label>
							            <input type="password" id="password2Registro" name="password2Registro" class="form-control form-control-lg" required/>
							            
							            <span name="errorPassword2" id="errorPassword2" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorPassword2")) {
							            %>		
							            		<%= errores.get("errorPassword2") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        <div class="form-item">
							            <label class="form-label" for="nombreRegistro">Nombre</label>
							            <input type="text" id="nombreRegistro" name="nombreRegistro" class="form-control form-control-lg" required/>
							            
							            <span name="errorNombre" id="errorNombre" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorNombre")) {
							            %>		
							            		<%= errores.get("errorNombre") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        
							    </div>
							
							    <div class="form-row">
							    
							        <div class="form-item">
					                	<label class="form-label" for="apellido1Registro">Primer Apellido</label>
				                		<input type="text" id="apellido1Registro" name="apellido1Registro" class="form-control form-control-lg" required/>
				                		
				                		<span name="errorApellido1" id="errorApellido1" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorApellido1")) {
							            %>		
							            		<%= errores.get("errorApellido1") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        <div class="form-item">
						                	<label class="form-label" for="apellido2Registro">Segundo Apellido</label>
                                			<input type="text" id="apellido2Registro" name="apellido2Registro" class="form-control form-control-lg" required/>
                                			
                                		<span name="errorApellido2" id="errorApellido2" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorApellido2")) {
							            %>		
							            		<%= errores.get("errorApellido2") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							    </div>
							    
   							    <div class="form-row">
   							    
							        <div class="form-item">
	                                	<label class="form-label" for="direccionRegistro">Dirección</label>
                                		<input type="text" id="direccionRegistro" name="direccionRegistro" class="form-control form-control-lg" required/>
                                		
                                		<span name="errorDireccion" id="errorDireccion" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorDireccion")) {
							            %>		
							            		<%= errores.get("errorDireccion") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        <div class="form-item">		                                	
	                                	<label class="form-label" for="provinciaRegistro">Provincia</label>
	                                	<input type="text" id="provinciaRegistro" name="provinciaRegistro" class="form-control form-control-lg" required/>
	                                	
	                                	<span name="errorProvincia" id="errorProvincia" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorProvincia")) {
							            %>		
							            		<%= errores.get("errorProvincia") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							    </div>
							    
   							    <div class="form-row">
   							    
							        <div class="form-item">
	                                	<label class="form-label" for="localidadRegistro">Localidad</label>
                                		<input type="text" id="localidadRegistro" name="localidadRegistro" class="form-control form-control-lg" required/>
                                		
                                		<span name="errorLocalidad" id="errorLocalidad" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorLocalidad")) {
							            %>		
							            		<%= errores.get("errorLocalidad") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							        
							        <div class="form-item">
	                                	<label class="form-label" for="telefonoRegistro">Teléfono</label>
                                		<input type="text" id="telefonoRegistro" name="telefonoRegistro" class="form-control form-control-lg" required/>
                                		
                                		<span name="errorTelefono" id="errorTelefono" class="error">
							            <%
							            	if (errores != null && errores.containsKey("errorTelefono")) {
							            %>		
							            		<%= errores.get("errorTelefono") %>
							            <%
							            	}
							            %>
							            </span>
							        </div>
							    </div>
							    
   							    <div class="rowDNI mb-4">
   							   
							        <div>
	                            		<label class="form-label" for="dniRegistro">DNI</label>
                                		<input type="text" id="dniRegistro" name="dniRegistro" class="form-control form-control-lg" required/>
                                		

							        </div>
							        
							    </div>
							    							
							    <div class="d-flex justify-content-center py-2 mb-3">
							        <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" name="accion" value="add" type="submit">Crear una cuenta</button>
							    </div>
							    			
				              </form>
				              				              
				           </div>
			
			            </div>
			            
			          </div>
			          
			        </div>
			        
			      </div>
			      
			    </div>
			    
			  
			</section>
		
		</main>
		
		<script src="<%=request.getContextPath() %><%= Rutas.REGISTRO_JS%>"></script>
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>