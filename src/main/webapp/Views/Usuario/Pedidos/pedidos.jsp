<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.Pedido.PedidoVO"
%>

<!DOCTYPE html>
<html>

	<head>
			
		<title>Perfil</title>
		
		 <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PEDIDOS_CSS%>">
	  
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
						List<PedidoVO> pedidos = (List<PedidoVO>) request.getAttribute("pedidos");
						
						
				        // Formatear el número con dos decimales utilizando String.format
// 				        String resultadoIVA = String.format("%.2f", totalIVA + totalCarrito);
							
						if (pedidos != null && !pedidos.isEmpty()) {
							
							for (PedidoVO pedido : pedidos) {
								
					%>
					            <!-- Single item -->
					            <div class="row">
					
					              <div class="col-lg-5 col-md-6 mb-4 mb-lg-0">
					              
					                <!-- Data -->
					                <p><strong><%= pedido.getFecha()%></strong></p>
					                <p><%= pedido.getMetodo_pago()%></p>
					                
					                				                
					                <!-- Data -->
					              </div>
					
					              <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
					
					                <!-- Price -->
					                <p class="text-start text-md-center">
					                  <strong><%= pedido.getEstado()%> </strong>
					                </p>
					                <!-- Price -->
					                
					              </div>
					              
					              <div class="col-lg-12 col-md-6 mb-4 mb-lg-0">
					              
					              	<form action="factura" method="post">
					              	
					              		<input type="hidden" id="idProd" name="idProd" value="<%= pedido.getId() %>"></input>
	            		                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-sm me-1" value="Detalle">
<!-- 						                  <i class="fas fa-trash"></i> -->
						                </button>
	              		                <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-sm me-1" value="Factura">
<!-- 						                  <i class="fas fa-trash"></i> -->
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
								<h2 style="color: red">Todavía no ha realizado ningún pedido</h2>
							</div>							
					<%
						        
						}
					%>
								
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