function incrementarCantidad(input) {

	let aumentar = parseInt(input.value);
	
	aumentar++;

	input.value = aumentar;
}

function decrementarCantidad(input) {

	let decrementar = parseInt(input.value);
	
	if (decrementar > 1) {
		decrementar--;
	}
	
	input.value = decrementar;
}