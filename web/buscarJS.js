$(document).ready(function(){
    $('#boton').click(function(){

        var apellido = $('#apellido').val();
        
        $.ajax({
            url:'Servlet2',
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