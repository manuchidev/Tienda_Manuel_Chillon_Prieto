<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="curso.java.tienda.config.Rutas"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Login</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
		 
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.LOGIN_CSS%>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
			<section class="gradient-custom">
	
				<%
				if (request.getAttribute("mensajeError") != null) {
				%>
					<div class="alert alert-danger alert-dismissible fade show text-center" role="alert"> <%=request.getAttribute("mensajeError")%>
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
			<% } %>

			<div class="container py-5">
			  
			    <div class="row d-flex justify-content-center align-items-center">
			    
			      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
			      
			        <div class="card bg-dark text-white" style="border-radius: 1rem;">
			        
			          <div class="card-body p-3 text-center">
			
			            <div class="mb-0">
			            
			              <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
			              <p class="text-white-50 mb-4">Introduzca su email y contraseña!</p>
			              
						  <form action="Login" method="post">
										
				              <div data-mdb-input-init class="form-outline form-white mb-4">
				                <label class="form-label" for="emailLogin">Email</label>
				                <input type="email" name="emailLogin" class="form-control form-control-lg" />
				              </div>
				
				              <div data-mdb-input-init class="form-outline form-white mb-3">
				                <label class="form-label" for="passwordLogin">Contraseña</label>
				                <input type="password" name="passwordLogin" class="form-control form-control-lg" />
				              </div>
				
				              <p class="small mb-4 pb-lg-2"><a class="text-white-50" href="#!">¿Olvidó su contraseña?</a></p>
							        
				              <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-5" type="submit">Iniciar Sesión</button>				          
				          </form>
			
			              <div class="d-flex justify-content-center text-center mt-4 pt-1">
			                <a href="#!" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
			                <a href="#!" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
			                <a href="#!" class="text-white"><i class="fab fa-google fa-lg"></i></a>
			              </div>
			
			            </div>
			
			            <div class="mt-4">
		             		<p class="mb-0">¿No está registrado? </p>

			             	<button  class="btn" type="submit">
			             		<a href="Registro" class="text-white-50 fw-bold">Registrarse</a>
			             	</button>
			             
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