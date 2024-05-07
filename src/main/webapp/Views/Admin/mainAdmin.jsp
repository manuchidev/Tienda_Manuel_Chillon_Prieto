<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO, 
	curso.java.tienda.model.VO.Usuario.UsuarioVO"
%> 

<!-- Section-->
<main>

	<% 
		if (request.getAttribute("mensajeError") != null) { %>
			<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
				<%= request.getAttribute("mensajeError") %>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
	<%  } 
		
		List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
		List<ProductoVO> productos = (List<ProductoVO>) request.getAttribute("productos");
		List<UsuarioVO> usuarios = (List<UsuarioVO>) request.getAttribute("usuarios");

	%>
	
	<section class="py-5 gradient-custom">
	
		<div class="container">
		
			<div class="row">
			
				<!-- Card de Productos -->
				<div class="col-md-4">
				
					<div class="card text-center">
					
						<div class="card-body">
							<h5 class="card-title">PRODUCTOS</h5>
							
							<form action="ProductoAdmin" method="get">
								<button type="submit" name="accion" value="view" class="btn btn-primary">Ver Productos</button>
								<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Producto</button>
							</form>
						</div>
						
					</div>
					
				</div>
				
				<!-- Card de Categorias -->
				<div class="col-md-4">
				
					<div class="card text-center">
					
						<div class="card-body">
							<h5 class="card-title">CATEGORÍAS</h5>

							<form action="CategoriaAdmin" method="get">
								<button type="submit" name="accion" value="view" class="btn btn-primary">Ver Categorias</button>
								<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Categoria</button>
							</form>
						</div>
						
					</div>
					
				</div>
				
				<!-- Card de Clientes -->
				<div class="col-md-4">
				
					<div class="card text-center">
					
						<div class="card-body">
							<h5 class="card-title">CLIENTES</h5>

							<form action="ClienteAdmin" method="get">
								<button type="submit" name="accion" value="view" class="btn btn-primary">Ver Clientes</button>
								<button type="submit" name="accion" value="add" class="btn btn-success">Añadir Cliente</button>
							</form>

						</div>
						
					</div>
					
				</div>
				
			</div>
			
		</div>
		
	</section>
	
</main>
