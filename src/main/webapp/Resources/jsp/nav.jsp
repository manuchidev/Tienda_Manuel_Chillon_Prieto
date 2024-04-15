<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>

<nav class="navbar navbar-inverse">

  <div class="container-fluid">
  
    <div class="navbar-header">
    
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      
      <a class="navbar-brand" href="#">Logo</a>
      
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
    
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Inicio</a></li>
        <li><a href="#">Categorías</a></li>
        <li><a href="#">Productos</a></li>
        <li><a href="#">Ofertas</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li>
     	    <form action="verUsuario">
       			<button type="submit" class="btnUsuario"><span class="glyphicon glyphicon-user"></span> Usuario</button>
       		</form>	
        </li>
        
        <li> 
       		<form action="verCarrito">
       			<button type="submit" class="btnCarrito"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito</button>
       		</form>		
        </li>
	    
      </ul>
      
    </div>
    
  </div>
  
</nav>