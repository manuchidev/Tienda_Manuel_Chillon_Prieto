<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, curso.java.tienda.model.VO.Categoria.CategoriaVO, 
	curso.java.tienda.model.VO.Usuario.UsuarioVO"		
 %>

<%
	UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
	List<CategoriaVO> categorias = (List<CategoriaVO>) request.getAttribute("categorias");
	List<ProductoVO> productosCategoria = (List<ProductoVO>) request.getAttribute("productosCategoria");
	Integer idCategoria = (Integer) request.getAttribute("idCategoria");
%>		

<div class="container-fluid px-2 px-lg-5">

<%		  	          			  	
	if (categorias != null && !categorias.isEmpty() && productosCategoria != null && !productosCategoria.isEmpty()) {
		
		for (CategoriaVO categoria: categorias) {

			if (categoria.getId() == idCategoria) {		
%>
				<h2 class="text-center mb-4" style="color: white"><%= categoria.getNombre()%></h2>
						
				<div class="row gx-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4">
<%						
					for (ProductoVO producto: productosCategoria) {
							
						if (categoria.getId() == producto.getId_categoria()) {
%>
							<div class="col mb-5">
					
								<div class="card h-100 cardProducto">
					
									<!-- Product image-->
									<div class="divProductoImg">
										<img class="card-img-top imgProd" src="<%= request.getContextPath() %><%= Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" alt="..." />
									</div>
					
									<!-- Product details-->
									<div class="card-body p-4">
					
										<div class="text-center">
					
											<!-- Product name-->
											<h5 class="fw-bolder"><%= producto.getNombre()%></h5>
					
											<!-- Product price-->
											<p class="p-3 m-0 fw-bolder fs-5"><%= producto.getPrecio()%>
												€
											</p>
					
											<p class="p-0 m-0 fw-light fs-6">
												Stock:
												<%= producto.getStock()%>
												ud.
											</p>
										</div>
					
									</div>
					
									<!-- Product actions-->
									<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					
										<div class="text-center">
									    <% if (usuario == null || usuario.esCliente()){ %>
											<a class="btn btn-primary mt-auto" href="añadirCarrito?idProd=<%= producto.getId()%>">Comprar</a> 
											<a class="btn btn-warning mt-auto" href="detalles?idProd=<%= producto.getId()%>&idCat=<%= producto.getId_categoria()%>">Detalles</a>
																				    
									    <%} else if (usuario.esEmpleado()){ %>
											<a class="btn btn-warning mt-auto" href="detallesEmpleado?idProd=<%= producto.getId()%>&idCat=<%= producto.getId_categoria()%>">Modificar</a>
									    	<a class="btn btn-danger mt-auto" href="retirar?idProd=<%= producto.getId()%>">Dar de Baja</a> 
									    <%} %>
										</div>
					
									</div>
					
								</div>
							</div>				
<%                
						}					
					}
%>
				</div>
<%
				break;
			}
		}
	}
%>

</div>