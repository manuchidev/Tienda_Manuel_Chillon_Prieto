const email = document.getElementById("emailRegistro");
const password = document.getElementById("passwordRegistro");
const password2 = document.getElementById("password2Registro");
const nombre = document.getElementById("nombreRegistro");
const apellido1 = document.getElementById("apellido1Registro");
const apellido2 = document.getElementById("apellido2Registro");
const direccion = document.getElementById("direccionRegistro");
const provincia = document.getElementById("provinciaRegistro");
const localidad = document.getElementById("localidadRegistro");
const telefono = document.getElementById("telefonoRegistro");
const dni = document.getElementById("dniRegistro");
const terminos = document.getElementById("terminosRegistro");

let ultimoElementoFoco = null;

// Función para borrar los errores
function limpiarErrores() {

    const eleFormulario = document.getElementById('formRegistro');
    const elementosError = eleFormulario.querySelectorAll('.error');

    elementosError.forEach(elemento => {
        elemento.textContent = "";
    });
}

// Función para mostrar los errores
function mostrarError(idSpan, texto, inputElement) {
	const elementoError = document.getElementById(idSpan); 
    elementoError.textContent = texto;

	if (!ultimoElementoFoco) {
		ultimoElementoFoco = inputElement;
	}
}

// Función para mostrar los exitos
function mostrarExito(idSpan, texto) {
	const elementoExito = document.getElementById(idSpan);
	elementoExito.textContent = texto;
}

let emailExiste = false;

// Evento para comprobar si el email ya existe sin necesidad de enviar el formulario
email.addEventListener('input', function() {

	const emailExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

	if (email.value.trim() !== "" && emailExp.test(email.value.trim())) {
		const xhr = new XMLHttpRequest();
		xhr.open("GET", "comprobarEmail?emailRegistro=" + encodeURIComponent(email.value.trim()), true); // True para que sea asíncrono

		xhr.onreadystatechange = function () {
			if (xhr.readyState == 4 && xhr.status == 200) {

				if (xhr.responseText == "true") {
					document.getElementById("errorEmail").style.color = "red";
					mostrarError("errorEmail", "El email ya existe", email);
					emailExiste = true;
				
				} else {
					document.getElementById("errorEmail").style.color = "green";
					mostrarExito("errorEmail", "El email es válido");
					emailExiste = false;
				} 
			}
		};

		xhr.send();
	}
});


function validarRegistro(event) {
	
	ultimoElementoFoco = null;
	
	// Limpiamos los errores
	limpiarErrores();
	
	const emailExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	const passwordExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
	const cadenaExp = /^[a-zA-Z]+$/;
	const telefonoExp = /^[0-9]{9}$/;
	const dniExp = /^[0-9]{8}[A-Z]$/;
			
	let error = false;
			
	if (!emailExiste) {

		if (email.value.trim() === "") {
			mostrarError("errorEmail", "Debe introducir un email", email);
			error = true;
		
		} else if (!emailExp.test(email.value.trim())) {
			mostrarError("errorEmail", "El email no es válido", email);
			error = true;
		} 
	
	} else {
		mostrarError("errorEmail", "El email ya existe", email);
		error = true;
	}
	
	// else {
		
	// 	// Realizamos una petición AJAX para comprobar si el email ya existe
	// 	const xhr = new XMLHttpRequest();
	// 	xhr.open("GET", "comprobarEmail?emailRegistro=" + encodeURIComponent(email.value.trim()), false); // False para que sea síncrono
	// 	xhr.send();
		
	// 	if (xhr.responseText == "true") {
	// 		mostrarError("errorEmail", "El email ya existe");
	// 		error = true;
	// 	}
	// }
	
	if (password.value.trim() === "") {
		mostrarError("errorPassword", "Debe introducir una contraseña", password);
		error = true;
	
	} else if (!passwordExp.test(password.value.trim())) {
		mostrarError("errorPassword", "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número", password);
		error = true;
	}
	
	if (password2.value.trim() === "") {
		mostrarError("errorPassword2", "Debe repetir la contraseña", password2);
		error = true;
	
	} else if (password.value.trim() !== password2.value.trim()) {
		
		if (password.value.trim() === "") {
			mostrarError("errorPassword", "Debe introducir una contraseña", password);
		
		} else {
			mostrarError("errorPassword2", "Las contraseñas no coinciden", password2);
			error = true;	
		}
	}
	
	if (nombre.value.trim() === "") {
		mostrarError("errorNombre", "Debe introducir un nombre", nombre);
		error = true;
	
	} else if (!cadenaExp.test(nombre.value.trim())) {
		mostrarError("errorNombre", "El nombre no puede contener números", nombre);
		error = true;
	} 
	
	if (apellido1.value.trim() === "") {
		mostrarError("errorApellido1", "Debe introducir su primer apellido", apellido1);
		error = true;
	
	} else if (!cadenaExp.test(apellido1.value.trim())) {
		mostrarError("errorApellido1", "El apellido no puede contener números", apellido1);
		error = true;
	}
	
	if (apellido2.value.trim() === "") {
		mostrarError("errorApellido2", "Debe introducir su segundo apellido", apellido2);
		error = true;
	
	} else if (!cadenaExp.test(apellido2.value.trim())) {
		mostrarError("errorApellido2", "El apellido no puede contener números", apellido2);
		error = true;
	}
	
	if (direccion.value.trim() === "") {
		mostrarError("errorDireccion", "Debe introducir una dirección", direccion);
		error = true;
	}
	
	if (provincia.value.trim() === "") {
		mostrarError("errorProvincia", "Debe introducir una provincia", provincia);
		error = true;
	
	} else if (!cadenaExp.test(provincia.value.trim())) {
		mostrarError("errorProvincia", "La provincia no puede contener números", provincia);
		error = true;
	}
	
	if (localidad.value.trim() === "") {
		mostrarError("errorLocalidad", "Debe introducir una localidad", localidad);
		error = true;
	
	} else if (!cadenaExp.test(localidad.value.trim())) {
		mostrarError("errorLocalidad", "La localidad no puede contener números", localidad);
		error = true;
	}
	
	if (telefono.value.trim() === "") {
		mostrarError("errorTelefono", "Debe introducir un teléfono", telefono);
		error = true;
	
	} else if (!telefonoExp.test(telefono.value.trim())) {
		mostrarError("errorTelefono", "El teléfono no es válido", telefono);
		error = true;
	}
	
	if (dni.value.trim() === "") {
		mostrarError("errorDni", "Debe introducir un DNI", dni);
		error = true;
	
	} else if (!dniExp.test(dni.value.trim())) {
		mostrarError("errorDni", "El DNI no es válido", dni);
		error = true;
	}
	
	if (!terminos.checked) {
		mostrarError("errorTerminos", "Debe aceptar los términos y condiciones", terminos);
		error = true;
	}
	
	
	// Si hay algún error, no se envía el formulario
	if (error || emailExiste) {

		if (ultimoElementoFoco) {
			ultimoElementoFoco.focus();
		}

		event.preventDefault();
		return false;
	}
	
	// Si no hay errores, se envía el formulario
	return true
}