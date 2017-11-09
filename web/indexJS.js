$(document).ready(function(){
    $('#boton').click(function(){

        var nombre = $('#nombre').val();
        var apellido = $('#apellido').val();
        var edad = $('#edad').val();
        $.ajax({
            url:'Servlet',
            type:'post',
            data:{nombre:nombre,apellido:apellido,edad:edad},
            dataType: 'json',
            success: function(data) {
                alert("Genial");
            },

            error: function(){
                $('#ack').val("ERROR FATAL");
            }
        });
    });
});