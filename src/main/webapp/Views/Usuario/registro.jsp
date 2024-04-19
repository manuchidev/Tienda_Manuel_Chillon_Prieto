<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Registro</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
	  	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
			<section class="gradient-custom">
			  
			    <div class="container py-5">
			    
			      <div class="row d-flex justify-content-center align-items-center">
			      
			        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
			        
			          <div class="card bg-dark text-white" style="border-radius: 1rem;">
			          
			            <div class="card-body p-4 text-center">
			            
			            	<div class="mb-0">
			            
				              <h2 class="text-uppercase text-center mb-5">Formulario de Registro</h2>
				
				              <form action="ValidarRegistro" method="post">
								
		              			<div data-mdb-input-init class="form-outline form-white mb-4">
				                	<label class="form-label" for="emailRegistro">Email</label>
				                	<input type="email" id="emailRegistro" class="form-control form-control-lg" />
				              	</div>
				              	
		              			<div data-mdb-input-init class="form-outline form-white mb-4">
				                	<label class="form-label" for="password">Contraseña</label>
				                	<input type="password" id="password" class="form-control form-control-lg" />
				              	</div>
				              	
		              			<div data-mdb-input-init class="form-outline form-white mb-4">
				                	<label class="form-label" for="password2">Repetir Contraseña</label>
				                	<input type="password" id="password2" class="form-control form-control-lg" />
				              	</div>
		
				                <div data-mdb-input-init class="form-outline form-white mb-4">
				                	<label class="form-label" for="nombreRegistro">Nombre</label>
				                	<input type="text" id="nombreRegistro" class="form-control form-control-lg" />
				              	</div>
				              	
				                <div data-mdb-input-init class="form-outline form-white mb-4">
				                	<label class="form-label" for="apellido1Registro">Primer Apellido</label>
				                	<input type="text" id="apellido1Registro" class="form-control form-control-lg" />
				              	</div>
				              	
                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="apellido2Registro">Segundo Apellido</label>
                                	<input type="text" id="apellido2Registro" class="form-control form-control-lg" />
                               	</div>
                               	
                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="direccionRegistro">Dirección</label>
                                	<input type="text" id="direccionRegistro" class="form-control form-control-lg" />
                               	</div>
                               	
                             	<div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="provinciaRegistro">Provincia</label>
                                	<input type="text" id="provinciaRegistro" class="form-control form-control-lg" />
                               	</div>
                               	
                             	<div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="localidadRegistro">Localidad</label>
                                	<input type="text" id="localidadRegistro" class="form-control form-control-lg" />
                               	</div>
                               	
                             	<div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="telefonoRegistro">Teléfono</label>
                                	<input type="text" id="telefonoRegistro" class="form-control form-control-lg" />
                               	</div>
                               	
                             	<div data-mdb-input-init class="form-outline form-white mb-4">
                                	<label class="form-label" for="dniRegistro">DNI</label>
                                	<input type="text" id="dniRegistro" class="form-control form-control-lg" />
                               	</div>
                               	
                               	
				
				                <div data-mdb-input-init class="form-outline form-white mb-4 py-2">
				                  <input class="form-check-input me-2" type="checkbox" value="" id="terminos" />
				                  <label class="form-check-label" for="terminos">
				                    Acepto todo los <a href="#!" class="text-body"><u style="color: white;">Términos y Servicios</u></a>
				                  </label>
				                </div>
				
				                <div class="d-flex justify-content-center py-2 mb-3">
				                  <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Crear una cuenta</button>
				                </div>
				
				              </form>
				              
      			              <div>
	             				  <p class="mb-0">¿Ya tiene una cuenta? </p>
	             		
  			             		  <button  class="btn" type="submit">
			             			  <a href="Usuario" class="text-white-50 fw-bold">Ir al Login</a>
			             		  </button>		              	  
				              	  
					          </div>
				              
				           </div>
			
			            </div>
			            
			          </div>
			          
			        </div>
			        
			      </div>
			      
			    </div>
			    
			  
			</section>
		
		</main>
		
				
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>