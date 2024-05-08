<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  
	import=" java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.*, 
		curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.Categoria.CategoriaVO" 
%>

<%
	String idiomaActual = (String) request.getSession().getAttribute("idioma");
	ResourceBundle bundle = (ResourceBundle) request.getSession().getAttribute("bundle");
	
	List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
	
	UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
	
	HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");     
%>

<nav class="navbar navbar-expand-lg">

	<div class="container-fluid">
		    
	    <a class="navbar-brand px-0 mx-4" href="/Tienda_Manuel_Chillon_Prieto/">
	    	<img class="imgLogo" src="<%=request.getContextPath() %><%=Rutas.IMAGENES_LOGO %>" alt="">
	    </a>
	    
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	    </button>
	    
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    
	        <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
	        
	            <li class="nav-item"><a class="nav-link active" aria-current="page" href="/Tienda_Manuel_Chillon_Prieto/"><%= bundle.getString("nav.inicio") %></a></li>

	            <li class="nav-item dropdown">
	        
	                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><%= bundle.getString("nav.categorias") %></a>
	                
	                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	                    <li><a class="dropdown-item" href="categoria"><%= bundle.getString("nav.Allproductos") %></a></li>
	                    <li><hr class="dropdown-divider" /></li>
	                    
                    <%		  	          			  	
						if (categorias != null && !categorias.isEmpty()) {
							
							for (CategoriaVO categoria: categorias) {								
					%>
			                    <li><a class="dropdown-item" href="categoria?id=<%= categoria.getId() %>"><%= categoria.getNombre() %></a></li>			
					<%
							}
						}
                     %>
					
	                </ul>
	                
	            </li>
	            
   				<% if (usuario == null || usuario.esCliente()) {%>	            
	     	        <li class="nav-item"><a class="nav-link" href="Contacto"><%= bundle.getString("nav.contacto") %></a></li>
	            <% } %>
	            
	        </ul>
	        
	        <div class="d-flex flex-column-reverse flex-lg-row">

				<form class="d-flex px-lg-2 py-sm-1 order-2 order-lg-0" action="CambiarIdioma" method="post">
			<%
					if ("es".equals(idiomaActual)) {
			%>
						<button class="btn" type="submit" name="idioma" value="en">
							<i class="bi-globe me-1"></i>English
						</button>
			<%
					} else {
			%>
						<button class="btn" type="submit" name="idioma" value="es">
							<i class="bi-globe me-1"></i>Español
						</button>
			<%
					}
			%>
				</form>

	        <%		        	
				 if (usuario == null) {
	       	%>
	       	        <form class="d-flex px-lg-2 py-sm-1 order-1" action="Login" method="get">
	                 	<button class="btn" data-bs-toggle="modal" type="submit">
		                	<i class="bi-person-fill me-1"></i> <%= bundle.getString("nav.usuario") %>	  
		                </button>   
	               	</form> 
		                          	
	        <%
	             } else {
	        %>
	               	<button class="btn order-1" data-bs-toggle="modal" data-bs-target="#usuarioModal" type="button">
	               		<i class="bi-person-fill me-1"></i> <%= usuario.getEmail() %>
	               	</button>	                
	        <%
	             }
	        %>
	     
	     	<% if (usuario == null || usuario.esCliente()){ %>
		        <form class="d-flex order-1" action="Carrito">
		        	        
		            <button class="btn" type="submit">
		                <i class="bi-cart-fill me-1"></i>
		                <%= bundle.getString("nav.carrito") %>
		                <span class="badge bg-danger text-white ms-1 rounded-pill"><%= (carrito != null ? carrito.size() : 0) %></span>
		            </button>
		            
		        </form>
		   <% } %>
	        
	        </div>
	            		        
	    </div>
	    
	</div>
	
</nav>

<!-- Modal de Usuario -->
<div class="modal fade" id="usuarioModal" tabindex="-1" aria-labelledby="usuarioModalLabel" aria-hidden="true">

    <div class="modal-dialog modal-dialog-centered">
    
        <div class="modal-content">
        
            <div class="modal-header">
                <h5 class="modal-title" id="usuarioModalLabel" style="color:black">Opciones de Usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <div class="modal-body">
            
                <% if (usuario != null) { %>
                    <!-- Si el usuario ha iniciado sesión -->
                    <form action="Usuario" method="post" class="d-flex flex-column align-items-center">
                        <button class="btn btn-primary w-100 mb-3" type="submit" name="accion" value="perfil">PERFIL</button>
                        <button class="btn btn-primary w-100 mb-3" type="submit" name="accion" value="pedidos">PEDIDOS</button>
                        <button class="btn btn-danger w-100 mb-3" type="submit" name="accion" value="cerrarSesion">CERRAR SESIÓN</button>
                    </form>                
                <% 
                	} 
                %>
                
            </div>
            
        </div>
        
    </div>
    
</div>