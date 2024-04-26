function actualizarCantidadDetalle() {

    var cantidad = document.getElementById("inputQuantity").value;
    var idProd = document.getElementById("añadirCarrito").getAttribute("data-idprod");
    
    var url = 'añadirCarrito?idProd=' + encodeURIComponent(idProd) + '&cantidad=' + encodeURIComponent(cantidad);

    document.getElementById("añadirCarrito").href = url;
}