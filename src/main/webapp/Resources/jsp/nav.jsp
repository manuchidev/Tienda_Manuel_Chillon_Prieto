<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  
	import=" java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.ProductoVO" %>

<nav class="navbar navbar-expand-lg">

	<div class="container px-lg-12">
	
	    <a class="navbar-brand" href="/Tienda_Manuel_Chillon_Prieto/">LOGO</a>
	    
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
	    
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
	            
     	        <li class="nav-item"><a class="nav-link" href="#!">Contacto</a></li>
	            
	        </ul>
	        
	        <form class="d-flex px-lg-2 py-sm-1" action="verUsuario">
	        
	            <button class="btn btn-outline-dark" type="submit">
	                <i class="bi-person-fill me-1"></i> Usuario
	            </button>
	            
	        </form>
	        
	        <form class="d-flex" action="Carrito">
	        
	        <%
	        	HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)session.getAttribute("carrito");
	        %>
	        
	            <button class="btn btn-outline-dark" type="submit">
	                <i class="bi-cart-fill me-1"></i>
	                Carrito
	                <span class="badge bg-dark text-white ms-1 rounded-pill"><%= carrito.size()%></span>
	            </button>
	            
	        </form>
	        
	    </div>
	    
	</div>
</nav>