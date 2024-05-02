<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, java.math.BigDecimal, curso.java.tienda.config.Rutas, curso.java.tienda.model.VO.Producto.ProductoVO, 
	curso.java.tienda.model.VO.Usuario.UsuarioVO, curso.java.tienda.model.VO.Compra.MetodoPagoVO"
%>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PAGO_CSS%>">
	  
	</head>
	
	<body class="gradient-custom">
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main >
		
			<section class="py-5">
			
				<% if (request.getAttribute("mensajeError") != null) { %>
					<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
						<%= request.getAttribute("mensajeError") %>
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				<% } %>
			
				<div class="divPago">
	 			
			      <div class="py-3 text-center">
			      
			        <img class="d-block mx-auto mb-4" src="<%=request.getContextPath() %><%=Rutas.IMAGENES_LOGO %>" alt="" width="72" height="72">
			        
			        <h2>Proceso de pago</h2>		        		     
			        
			      </div>
			
			      <div class="row">
			      
			        <div class="col-md-4 order-md-2 mb-4 py-2">
			        
			          <h4 class="d-flex justify-content-between align-items-center mb-3">
			            <span class="text-muted">Su carrito</span>
			            <span class="badge badge-secondary badge-pill">3</span>
			          </h4>
			          
			          <ul class="list-group mb-3">
				          
    			      <%	    			     
    			      
    			        // Obtenemos el usuario de la sesión
    					UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
    			      
	//     			     ProvinciasLocalidadesVO provinciasLocalidades = provinciasLocalidades.getProvinciasLocalidades();

    					List<MetodoPagoVO> metodos_pago = (List<MetodoPagoVO>)request.getAttribute("metodos_pago");
    						
						// Obtenemos los productos del carrito de la sesión					
						HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");
	
	   					BigDecimal totalCarrito = (BigDecimal)request.getAttribute("totalCarrito");						
	   					BigDecimal totalCarritoIVA = (BigDecimal)request.getAttribute("totalCarritoIVA");
													
						for (Map.Entry<ProductoVO, Integer> productoCarrito : carrito.entrySet()) {
							
							ProductoVO producto = productoCarrito.getKey();
							int cantidad = productoCarrito.getValue();
							
							BigDecimal precioTotal = producto.getPrecio().multiply(new BigDecimal(cantidad));
					  %>		          
				            <li class="list-group-item d-flex justify-content-between lh-condensed">
				              <div>
				                <h6 class="my-0"><%= producto.getNombre()%></h6>
				                <small class="text-muted">Cantidad: <%= cantidad%></small>
				              </div>
				              <span class="text-muted"><%= precioTotal %>€</span>
				            </li>
				      <%
						}																				    								    
					  %>				            				            
				            
			            <li class="list-group-item d-flex justify-content-between bg-light">
			              <div class="text-success">
			                <h6 class="my-0">Código Descuento</h6>
			                <small>EXAMPLECODE</small>
			              </div>
			              <span class="text-success">-5€</span>
			            </li>
			            
			            <li class="list-group-item d-flex justify-content-between">
			              <span>Total (IVA)</span>
			              <strong><%= totalCarritoIVA %> €</strong>
			            </li>
			            
			          </ul>
			
			          <form class="card p-2">
			          
			            <div class="input-group">
			              <input type="text" class="form-control" placeholder="Código Descuento">
			              <div class="input-group-append">
			                <button type="submit" class="btn btn-secondary">Aplicar</button>
			              </div>
			            </div>
			            
			          </form>
		          
			        </div>
			        
			        <div class="col-md-8 order-md-1 py-2">
			        
			          <h4 class="mb-3">Datos de Envío</h4>
			          
			          <form action="pedido" method="post" class="needs-validation" validate>
			          
			            <div class="row">
			            
			              <div class="col-md-6 mb-3">
			                <label for="nombre">Nombre</label>
			                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= usuario.getNombre() %>" required>
			                
			                <div class="errorNombre">
<!-- 			                  	El nombre no puede estar vacío. -->
			                </div>
			              </div>
			              
			              <div class="col-md-6 mb-3">
			                <label for="apellidos">Apellidos</label>
			                <input type="text" class="form-control" id="apellidos" name="apellidos" value="<%= usuario.getApellido1() + " " + usuario.getApellido2() %>" required>
			                
			                <div class="errorApellidos">
			               <!-- El apellido no puede estar vacío. -->
			                </div>
			              </div>
			              
			            </div>
						
			            <div class="mb-3">
			              <label for="email">Email</label>
			              <input type="email" class="form-control" id="email" name="email" placeholder="nombre@email.com" value="<%= usuario.getEmail()%>">
			              
			              <div class="errorEmail">
<!-- 			                	Debe introducir un email válido. -->
			              </div>
			              
			            </div>
			            
			            <div class="mb-3">
			              <label for="telefono">Teléfono</label>
			              <input type="text" class="form-control" id="telefono" name="telefono" placeholder="600 00 00 00" value="<%= usuario.getTelefono()%>">
			              
			              <div class="errorTelefono">
<!-- 			                	Debe introducir un teléfono válido. -->
			              </div>
			              
			            </div>
			
			            <div class="mb-3">
			              <label for="direccion">Dirección</label>
			              <input type="text" class="form-control" id="direccion" name="direccion" placeholder="C/ Amargura 12, 2B" value="<%= usuario.getDireccion() %>" required>
			              
			              <div class="errorDireccion">
<!-- 			                	Debe introducir una dirección de envío. -->
			              </div>
			              
			            </div>
			
			            <div class="mb-3">
			              <label for="direccion2">Dirección Secundaria<span class="text-muted">(Opcional)</span></label>
			              <input type="text" class="form-control" id="direccion2" name="direccion2" placeholder="Piso o Quiosco">
			            </div>
			
			            <div class="row">
			            
				              <div class="col-md-4 mb-3">
				              
				                <label for="provincia">Provincia</label>
				                
				                <select class="custom-select d-block w-100" id="provincia" name="provincia" value="<%= usuario.getProvincia() %>" required>
<!-- 									    <option value="">Selecciona una provincia</option> -->
									    <option value="1" ><%= usuario.getProvincia() %></option>
					            </select>
				                
				                <div class="errorZona">
	<!-- 			                  	Selecciona una zona de España -->
				                </div>
				                
				              </div>
			              
				              <div class="col-md-5 mb-3">
				              
				                <label for="localidad">Localidad</label>
				                
				                <select class="custom-select d-block w-100" id="localidad" name="localidad" value="<%= usuario.getLocalidad() %>" required>
<!-- 									<option value="">Selecciona una localidad</option> -->
									<option value="1" ><%= usuario.getLocalidad() %></option>
	               				</select>
				                
					                <div class="errorComunidad">
		<!-- 			                  	Selecciona una comunidad autónoma -->
					                </div>
				                
				              </div>
			              
				              <div class="col-md-3 mb-3">
				              
				                <label for="cp">Código Postal</label>
				                <input type="text" class="form-control" id="cp" name="cp" placeholder="" required>
				                
				                <div class="errorCp">
	<!-- 			                  	Debe introducir un código postal válido -->
				                </div>
				                
				              </div>
			              
				            </div>
				            
				            <hr class="mb-4">
				            
				            <div class="custom-control custom-checkbox">
				              <input type="checkbox" class="custom-control-input" id="mismaDireccion" name="mismaDireccion">
				              <label class="custom-control-label" for="mismaDireccion">La dirección de envío es la misma que la de facturación</label>
				            </div>
				            
				            <div class="custom-control custom-checkbox">
				              <input type="checkbox" class="custom-control-input" id="guardarInfo" name="guardarInfo">
				              <label class="custom-control-label" for="guardarInfo">Guardar información para la próxima vez</label>
				            </div>
				            
				            <hr class="mb-4">
				
				            <h4 class="mb-3">Métodos de Pago</h4>
				
				            <div class="d-block my-3">
				            
 				            <% 
 				           		if (metodos_pago != null && !metodos_pago.isEmpty()) {
 				           			
 					            	for (MetodoPagoVO metodo_pago : metodos_pago) {
					        %>				       
	 	       			              <div class="custom-control custom-radio"> 
	 	       			    <%
	 	       			    		  	 if (metodo_pago.getId() == 1) {
	 	       			    %>
	 					                	<input id="<%= metodo_pago.getId() %>" name="metodo_pago" type="radio" class="custom-control-input" 
	 					                		value="<%= metodo_pago.getMetodo_pago()%>" checked required>
	 					    <%
	 	       			    		  	 } else {
	 					    %>
	 					    				<input id="<%= metodo_pago.getId() %>" name="metodo_pago" type="radio" class="custom-control-input" 
	 					    					value="<%= metodo_pago.getMetodo_pago()%>" required>
	 					    <%
	 	       			    		  	 }
	 					    %>
	 					                <label class="custom-control-label" for="metodo_<%= metodo_pago.getId()%>"><%= metodo_pago.getMetodo_pago() %></label>
	 					              </div> 
					        <%				            	
					            	}
 				           		}
 				            %>        				              
   			                  <div class="errorMetodoPago">
<!--  			                  		Debe elegir un método de pago -->
			                  </div>
				              
				            </div>
				            
				            <div class="row">
				            
				              <div class="col-md-6 mb-3">
				                <label for="nombre_titular">Nombre del titular</label>
				                <input type="text" class="form-control" id="nombre_titular" name="nombre_titular" placeholder="" value="<%=usuario.getNombre()%> <%=usuario.getApellido1()%> <%=usuario.getApellido2()%>" required>
				                
				                <small class="text-muted">Nombre completo como aparece en la tarjeta</small>
				                
				                <div class="errorNombreTitular">
<!-- 				                  	Debe introducir el nombre del titular -->
				                </div>
				                
				              </div>
				              
				              <div class="col-md-6 mb-3">
				                <label for="iban">IBAN</label>
				                <input type="text" class="form-control" id="iban" name="iban" placeholder="" required>
				                
				                <small class="text-muted">ES** **** **** ** **********</small>
				                
				                <div class="errorIban">
<!-- 				                  	Debe introducir un IBAN válido -->
				                </div>
				                
				              </div>
				              
				            </div>
				            
				            <div class="row">
				            
				              <div class="col-md-4 mb-3">
				                <label for="expedicion">Fecha de Expedición</label>
				                <input type="date" class="form-control" id="expedicion" name="expedicion" placeholder="" required>
				                
				                <div class="errorExpedicion">
<!-- 				                  	Debe introducir una fecha de expedición -->
				                </div>
				                
				              </div>
				              
				              <div class="col-md-3 mb-3">
				                <label for="cvv">CVV</label>
				                <input type="password" class="form-control" id="cvv" name="cvv" placeholder="" required>
				                
				                <div class="errorCvv">
<!-- 				                  	Debe introducir un CVV -->
				                </div>
				                
				              </div>
				              
				            </div>
				            
				            <hr class="mb-4">
				            
					        <button class="btn btn-primary btn-lg btn-block float-end" type="submit">Realizar Pedido</button>
				           
				          </form>
			          
			          </div>
			          
			        </div>
			        					      	
		      	</div>
			
			</section>		 			
	      	
		</main>
					
		<jsp:include page="<%= Rutas.FOOTER%>" />
	
	</body>
	
</html>
