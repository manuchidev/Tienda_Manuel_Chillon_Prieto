function actualizarCantidadDetalle() {

    var cantidad = document.getElementById("inputQuantity").value;
    var idProd = document.getElementById("añadirCarrito").getAttribute("data-idprod");

    document.getElementById("añadirCarrito").href = 'añadirCarrito?idProd=' + idProd + ' &cantidad=' + cantidad;
}