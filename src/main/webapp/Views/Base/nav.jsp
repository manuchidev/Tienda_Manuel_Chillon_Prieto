<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  
	import=" java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.*, curso.java.tienda.model.VO.Usuario.*" %>

<nav class="navbar navbar-expand-lg">

	<div class="container-fluid">
		    
	    <a class="navbar-brand  px-0 mx-5" href="/Tienda_Manuel_Chillon_Prieto/">
	    	<img class="imgLogo" src="<%=request.getContextPath() %><%=Rutas.IMAGENES_LOGO %>" alt="">
	    </a>
	    
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	    </button>
	    
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    
	        <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
	        
	            <li class="nav-item"><a class="nav-link active" aria-current="page" href="/Tienda_Manuel_Chillon_Prieto/">Inicio</a></li>

	            <li class="nav-item dropdown">
	        
	                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Categorías</a>
	                
	                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	                    <li><a class="dropdown-item" href="#!">Todos los productos</a></li>
	                    <li><hr class="dropdown-divider" /></li>
	                    <li><a class="dropdown-item" href="#!">Ordenadores</a></li>
	                    <li><a class="dropdown-item" href="#!">Monitores</a></li>
	                    <li><a class="dropdown-item" href="#!">Teclados</a></li>
	                    <li><a class="dropdown-item" href="#!">Ratones</a></li>
	                </ul>
	                
	            </li>
	            
     	        <li class="nav-item"><a class="nav-link" href="Contacto">Contacto</a></li>
	            
	        </ul>
	        
	        <div class="d-flex">
	         <%
	        	HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");
	        	
	        	UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		        	
				 if (usuario == null) {
	       	%>
	       	        <form class="d-flex px-lg-2 py-sm-1" action="Login" method="get">
	                 	<button class="btn" data-bs-toggle="modal" type="submit">
		                	<i class="bi-person-fill me-1"></i> Usuario	  
		                </button>   
	               	</form> 
		                          	
	        <%
	             } else {
	        %>
	               	<button class="btn" data-bs-toggle="modal" data-bs-target="#usuarioModal" type="button">
	               		<i class="bi-person-fill me-1"></i> <%= usuario.getEmail() %>
	               	</button>	                
	        <%
	             }
	        %>
	     
		        <form class="d-flex" action="Carrito">
		        	        
		            <button class="btn" type="submit">
		                <i class="bi-cart-fill me-1"></i>
		                Carrito
		                <span class="badge bg-danger text-white ms-1 rounded-pill"><%= (carrito != null ? carrito.size() : 0) %></span>
		            </button>
		            
		        </form>
	        
	        </div>
	            		        
	    </div>
	    
	</div>
	
</nav>

<!-- Modal de Usuario -->
<div class="modal fade" id="usuarioModal" tabindex="-1" aria-labelledby="usuarioModalLabel" aria-hidden="true">

    <div class="modal-dialog modal-dialog-centered">
    
        <div class="modal-content">
        
            <div class="modal-header">
                <h5 class="modal-title" id="usuarioModalLabel">Opciones de Usuario</h5>
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