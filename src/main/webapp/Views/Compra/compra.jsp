<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.*, curso.java.tienda.config.Rutas,curso.java.tienda.model.VO.Producto.ProductoVO"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">

	<head>
	
	  <title>Tienda Serbatic</title>
	  
	  <jsp:include page="<%= Rutas.HEAD%>" />
	  
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PROD_CSS%>">
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %><%= Rutas.PAGO_CSS%>">
	  
	</head>
	
	<body>
	
		<jsp:include page="<%= Rutas.HEADER%>" />
		
		<jsp:include page="<%= Rutas.NAV%>" />
		
		<main >
		
			<section class="gradient-custom py-5">
			
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
    			          			      
						// Obtenemos los productos del carrito de la sesión					
						HashMap<ProductoVO, Integer> carrito = (HashMap<ProductoVO, Integer>)request.getSession().getAttribute("carrito");
	
	   					double totalCarrito = (double)request.getAttribute("totalCarrito");						
						double IVA = 0.21;
						
						double totalIVA = totalCarrito * IVA;
						
				        // Formatear el número con dos decimales utilizando String.format
				        String resultado = String.format("%.2f", totalCarrito);
				        String resultadoIVA = String.format("%.2f", totalIVA + totalCarrito);
							
						for (Map.Entry<ProductoVO, Integer> productoCarrito : carrito.entrySet()) {
							
							ProductoVO producto = productoCarrito.getKey();
							int cantidad = productoCarrito.getValue();
					  %>		          
				            <li class="list-group-item d-flex justify-content-between lh-condensed">
				              <div>
				                <h6 class="my-0"><%= producto.getNombre()%></h6>
				                <small class="text-muted">Cantidad: <%= cantidad%></small>
				              </div>
				              <span class="text-muted"><%= cantidad * producto.getPrecio()%>€</span>
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
			              <strong><%= resultadoIVA %> €</strong>
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
			                <input type="text" class="form-control" id="nombre" placeholder="" value="" required>
			                
			                <div class="errorNombre">
			                  	El nombre no puede estar vacío.
			                </div>
			              </div>
			              
			              <div class="col-md-6 mb-3">
			                <label for="apellidos">Apellidos</label>
			                <input type="text" class="form-control" id="apellidos" placeholder="" value="" required>
			                
			                <div class="errorApellidos">
			                    	Los apellidos no pueden estar vacíos.
			                </div>
			              </div>
			              
			            </div>
						
			            <div class="mb-3">
			              <label for="email">Email</label>
			              <input type="email" class="form-control" id="email" placeholder="nombre@email.com">
			              
			              <div class="errorEmail">
			                	Debe introducir un email válido.
			              </div>
			              
			            </div>
			
			            <div class="mb-3">
			              <label for="direccion">Dirección</label>
			              <input type="text" class="form-control" id="direccion" placeholder="C/ Amargura 12, 2B" required>
			              
			              <div class="errorDireccion">
			                	Debe introducir una dirección de envío.
			              </div>
			              
			            </div>
			
			            <div class="mb-3">
			              <label for="direccion2">Dirección Secundaria<span class="text-muted">(Opcional)</span></label>
			              <input type="text" class="form-control" id="direccion2" placeholder="Piso o Quiosco">
			            </div>
			
			            <div class="row">
			            
			              <div class="col-md-4 mb-3">
			              
			                <label for="zona">Zona de España</label>
			                
			                <select class="custom-select d-block w-100" id="zona" required>
								<option value="">Selecciona una zona</option>
								<option value="Península">Península</option>
								<option value="Baleares">Islas Baleares</option>
								<option value="Canarias">Islas Canarias</option>
								<option value="Ceuta">Ceuta</option>
								<option value="Melilla">Melilla</option>
			                </select>
			                
			                <div class="errorZona">
			                  	Selecciona una zona de España
			                </div>
			                
			              </div>
			              
			              <div class="col-md-5 mb-3">
			              
			                <label for="comunidad">Comunidad Autónoma</label>
			                
			                <select class="custom-select d-block w-100" id="comunidad" required>
								<option value="">Selecciona una comunidad</option>
								<option value="Andalucía">Andalucía</option>
								<option value="Aragón">Aragón</option>
								<option value="Asturias">Asturias</option>
								<option value="Baleares">Islas Baleares</option>
								<option value="Canarias">Canarias</option>
								<option value="Cantabria">Cantabria</option>
								<option value="Castilla-La Mancha">Castilla-La Mancha</option>
								<option value="Castilla y León">Castilla y León</option>
								<option value="Cataluña">Cataluña</option>
								<option value="Extremadura">Extremadura</option>
								<option value="Galicia">Galicia</option>
								<option value="Madrid">Comunidad de Madrid</option>
								<option value="Murcia">Región de Murcia</option>
								<option value="Navarra">Comunidad Foral de Navarra</option>
								<option value="País Vasco">País Vasco</option>
								<option value="La Rioja">La Rioja</option>
								<option value="Valencia">Comunidad Valenciana</option>
								<option value="Ceuta">Ciudad Autónoma de Ceuta</option>
								<option value="Melilla">Ciudad Autónoma de Melilla</option>
               				</select>
			                
			                <div class="errorComunidad">
			                  	Selecciona una comunidad autónoma
			                </div>
			                
			              </div>
			              
			              <div class="col-md-3 mb-3">
			              
			                <label for="cp">Código Postal</label>
			                <input type="text" class="form-control" id="cp" placeholder="" required>
			                
			                <div class="errorCp">
			                  	Debe introducir un código postal válido
			                </div>
			                
			              </div>
			              
				            </div>
				            
				            <hr class="mb-4">
				            
				            <div class="custom-control custom-checkbox">
				              <input type="checkbox" class="custom-control-input" id="mismaDireccion">
				              <label class="custom-control-label" for="mismaDireccion">La dirección de envío es la misma que la de facturación</label>
				            </div>
				            
				            <div class="custom-control custom-checkbox">
				              <input type="checkbox" class="custom-control-input" id="guardarInfo">
				              <label class="custom-control-label" for="guardarInfo">Guardar información para la próxima vez</label>
				            </div>
				            
				            <hr class="mb-4">
				
				            <h4 class="mb-3">Métodos de Pago</h4>
				
				            <div class="d-block my-3">
				            
				              <div class="custom-control custom-radio">
				                <input id="tarjeta" name="metodo_pago" type="radio" class="custom-control-input" checked required>
				                <label class="custom-control-label" for="tarjeta">Tarjeta de crédito</label>
				              </div>
				              				              
				              <div class="custom-control custom-radio">
				                <input id="paypal" name="metodo_pago" type="radio" class="custom-control-input" required>
				                <label class="custom-control-label" for="paypal">Paypal</label>
				              </div>
				              
   			                  <div class="errorMetodoPago">
			                  		Debe elegir un método de pago
			                  </div>
				              
				            </div>
				            
				            <div class="row">
				            
				              <div class="col-md-6 mb-3">
				                <label for="nombre_titular">Nombre del titular</label>
				                <input type="text" class="form-control" id="nombre_titular" placeholder="" required>
				                
				                <small class="text-muted">Nombre completo como aparece en la tarjeta</small>
				                
				                <div class="errorNombreTitular">
				                  	Debe introducir el nombre del titular
				                </div>
				                
				              </div>
				              
				              <div class="col-md-6 mb-3">
				                <label for="iban">IBAN</label>
				                <input type="text" class="form-control" id="iban" placeholder="" required>
				                
				                <small class="text-muted">ES** **** **** ** **********</small>
				                
				                <div class="errorIban">
				                  	Debe introducir un IBAN válido
				                </div>
				                
				              </div>
				              
				            </div>
				            
				            <div class="row">
				            
				              <div class="col-md-4 mb-3">
				                <label for="expedicion">Fecha de Expedición</label>
				                <input type="date" class="form-control" id="expedicion" placeholder="" required>
				                
				                <div class="errorExpedicion">
				                  	Debe introducir una fecha de expedición
				                </div>
				                
				              </div>
				              
				              <div class="col-md-3 mb-3">
				                <label for="cvv">CVV</label>
				                <input type="text" class="form-control" id="cvv" placeholder="" required>
				                
				                <div class="errorCvv">
				                  	Debe introducir un CVV
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
