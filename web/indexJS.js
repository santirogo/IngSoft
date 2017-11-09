$(document).ready(function(){
    $('#boton').click(function(){

        var nombre = $('#nombre').val();
        $.ajax({
            url:'Servlet',
            type:'post',
            data:{nombre:nombre},
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