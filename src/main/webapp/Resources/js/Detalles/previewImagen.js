function previewImage() {
    var reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('preview').src = e.target.result;
    }
    reader.readAsDataURL(document.getElementById('imagen').files[0]);
}