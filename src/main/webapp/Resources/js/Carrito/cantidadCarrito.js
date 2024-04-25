function incrementarCantidad() {
	
	const cantidad = document.getElementById('cantidad');
	
	cantidad.stepUp();
	
	actualizarCantidad(cantidad.value);
}

function decrementarCantidad() {
	
	const cantidad = document.getElementById('cantidad');
	
	cantidad.stepDown();
	
	actualizarCantidad(cantidad.value);
}

function actualizarCantidad(cantidadActulizada) {
	
	const cantidad = document.getElementById('cantidad');
	
	cantidad.value = parseInt(cantidadActulizada);
	
	const idProd = document.getElementById('idProd').value;
	
	if (carrito.has(idProd)) {
		
		carrito.set(idProd, cantidadActulizada);
		console.log('Cantidad actualizada');
	
	} else {
		console.log('No se ha encontrado el producto');
	}
	
}