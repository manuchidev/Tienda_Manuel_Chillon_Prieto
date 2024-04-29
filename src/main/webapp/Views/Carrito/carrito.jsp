<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import=" java.util.*, curso.java.tienda.config.Rutas,curso.java.tienda.model.VO.Producto.ProductoVO" %>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<!DOCTYPE html>
<html>
	<head>
	
		<title>Carrito</title>
		
		<jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CARRITO_CSS %>">
<%-- 	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.CANTIDAD_CARRITO_CSS %>"> --%>
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main>
		
			<section class="h-100 gradient-custom">
			
			  <div class="container py-5">
			  
			    <div class="row d-flex justify-content-center my-4">
			    			    
			      <div class="col-md-8">
			      
			        <div class="card mb-4">
			        
			          <div class="card-header py-3">			          
			            <h5 class="mb-0">Lista de Productos</h5>
			          </div>
			          
			          <div class="card-body">
					          
   					<%	
						// Obtenemos los productos del carrito de la sesión					
						HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");

	   					double totalCarrito = (double)request.getAttribute("totalCarrito");						
	   					double totalCarritoIVA = (double)request.getAttribute("totalCarritoIVA");;
													
						if (carrito != null && !carrito.isEmpty()) {
							
							for (Map.Entry<ProductoVO, Integer> productoCarrito : carrito.entrySet()) {
								
								ProductoVO producto = productoCarrito.getKey();
								int cantidad = productoCarrito.getValue();
					%>
					            <!-- Single item -->
					            <div class="row">
					            
					              <div class="col-lg-3 col-md-12 mb-4 mb-lg-0">
					              
					                <!-- Image -->
					                <div class="bg-image hover-overlay hover-zoom ripple rounded text-center" data-mdb-ripple-color="light">
					                
					                  <img src="<%=request.getContextPath() %><%=Rutas.IMAGENES_PROD %><%= producto.getImagen() %>" class="img-responsive imgProdCarrito"
					                    class="w-100" alt="Blue Jeans Jacket" />
					                    
					                  <a href="#!">
					                    <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
					                  </a>
					                  
					                </div>
					                
					                <!-- Image -->
					              </div>
					
					              <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
					              
					                <!-- Data -->
					                <p><strong><%= producto.getNombre()%></strong></p>
					                <p><%= producto.getDescripcion()%></p>
					                <p>Stock: <%= producto.getStock()%></p>
					                
					                <button type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-warning btn-sm mb-2" data-mdb-tooltip-init
					                  title="Move to the wish list">
					                  <i class="fas fa-heart"></i>
					                </button>
					                				                
					                <!-- Data -->
					              </div>
					
					              <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
					              
					                <!-- Quantity -->
									<form action="actualizarCantidad" method="post">
									
                                        <input type="hidden" id="idProd" name="idProd" value="<%= producto.getId() %>"></input>
                                        
										<div class="d-flex mb-4" style="max-width: 300px">
										
										  <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary px-3 me-2"
											onclick="decrementarCantidad(this.parentElement.querySelector('input'))">
											<i class="fas fa-minus"></i>
										  </button>
						
										  <div data-mdb-input-init class="form-outline text-center">
											<input id="cantidad" min="0" name="cantidad" value="<%= cantidad %>" type="number" class="form-control text-center" 
												onchange="this.form.submit()" />
										  </div>
						
										  <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary px-3 ms-2"
											onclick="incrementarCantidad(this.parentElement.querySelector('input'))">
											<i class="fas fa-plus"></i>
										  </button>
										  
										</div>

									</form>
					                <!-- Quantity -->
					
					                <!-- Price -->
					                <p class="text-start text-md-center">
					                  <strong><%= cantidad * producto.getPrecio()%> €</strong>
					                </p>
					                <!-- Price -->
					                
					              </div>
					              
					              <div class="col-lg-12 col-md-6 mb-4 mb-lg-0">
					              
					              	<form action="eliminar" method="post">
					              	
					              		<input type="hidden" id="idProd" name="idProd" value="<%= producto.getId() %>"></input>
	              		                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-sm me-1 btnEliminar" data-mdb-tooltip-init title="Remove item">
						                  <i class="fas fa-trash iconoPapelera"></i>
						                </button>
						                
					                </form>
					                
					              </div>
					              
					            </div>
					            <!-- Single item -->
					
					            <hr class="my-4" />
										           
	      			<%
							}
							
						} else {
							
					%>
							<div class="col-md-8">
								<h2 style="color: red">El carrito está vacío</h2>
							</div>							
					<%
						        
						}
					%>
								
				          </div>
					          
				        </div>
				        
				      
				        <div class="card mb-4">
				        
				          <div class="card-body">
				          
				            <p><strong>Plazo de Envío Estimado</strong></p>
				            <p class="mb-0">12.10.2020 - 14.10.2020</p>
				            
				          </div>
				          
				        </div>
					        
				        <div class="card mb-4 mb-lg-0">
				        
				          <div class="card-body">
				          
				            <p><strong>Métodos de Pago Aceptados</strong></p>
				            
				            <img class="me-2" width="45px"
				              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
				              alt="Visa" />
				              
				            <img class="me-2" width="45px"
				              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
				              alt="American Express" />
				              
				            <img class="me-2" width="45px"
				              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
				              alt="Mastercard" />
				              
				            <img class="me-2" width="45px"
				              src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.webp"
				              alt="PayPal acceptance mark" />
				              
				          </div>
					          
				        </div>
				        
				  </div>
				      
			      <div class="col-md-4">
			      
			        <div class="card mb-4">
			        
			          <div class="card-header py-3">
			            <h5 class="mb-0">Resumen</h5>
			          </div>
			          
			          <div class="card-body">
			          
			            <ul class="list-group list-group-flush">
			            
			              <li
			                class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
			                Productos
			                <span><%= totalCarrito %> €</span>
			              </li>
			              
			              <li class="list-group-item d-flex justify-content-between align-items-center px-0">
			                Envío
			                <span>Gratis</span>
			              </li>
			              
			              <li
			                class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
			                <div>
			                  <strong>Precio Total</strong>
			                  <strong>
			                    <p class="mb-0">(IVA incluido)</p>
			                  </strong>
			                </div>
			                <span><strong><%= totalCarritoIVA %> €</strong></span>
			              </li>
			              
			            </ul>
				
						<div class="d-flex justify-content-between">
						
	   						<form action="vaciar" method="post">
								<button type="submit" data-mdb-button-init data-mdb-ripple-init class="btnVaciarCarrito btn btn-danger btn-lg btn-block">Vaciar Carrito</button>
							</form>	
							
							<form action="comprar" method="post">
				            	<button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block">Comprar</button>
				            </form>
				            
						</div>
						
			          </div>
			          
			        </div>
			        
			      </div>
			      
				      				      
			    </div>
			    
			  </div>
			  
			</section>
		
		</main>				
						
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
		<script src="<%=request.getContextPath() %><%= Rutas.CANTIDAD_CARRITO_JS%>"></script>
	</body>
	
</html>