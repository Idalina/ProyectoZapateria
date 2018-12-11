function fechaHoy(){

var hoy = new  new Date().toJSON().slice(0,10);

console.log(hoy);
 $("#fecha_actual_timbrado").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function agregarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_timbrado").focus();
        }
    });
}

function modificarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            
        }
    });
}

function eliminarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_timbrado").focus();
            $("#id_timbrado").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
                
            }
        }
    });
}

function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_timbrado").val(json.id_timbrado);
            $("#numero_timbrado").val(json.numero_timbrado);
            $("#fecha_inicio_timbrado").val(json.fecha_inicio_timbrado);
            $("#fecha_vencimiento_timbrado").val(json.fecha_vencimiento_timbrado);
            $("#fecha_actual_timbrado").val(json.fecha_actual_timbrado);
            var fecha = $("#fecha_factura_venta").serialize();
            
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#numero_timbrado", "#botonAgregar", true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#numero_timbrado", "#botonModificar", true);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNumeroTimbrado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNumero.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_timbrado").val(id);
              $("#numero_timbrado").focus();
              buscarIdTimbrado();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo recuperar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            
            if (json.nuevo === "false") {
               $("#numero_timbrado").val("");
               $("#numero_timbrado").focus();
           } else {
               
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#numero_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nª timbrado no puede estar vacio.");
        $("#numero_timbrado").focus();
    }
    
    if ($("#fecha_inicio_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha inicio no puede estar vacio.");
        $("#fecha_inicio_timbrado").focus();
    }
    
    if ($("#fecha_vencimiento_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha vencimiento no puede estar vacio.");
        $("#fecha_vencimiento_timbrado").focus();
    }
    
    if ($("#fecha_actual_timbrado").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha actual no puede estar vacio.");
        $("#fecha_actual_timbrado").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#id_timbrado").val("");
    $("#numero_timbrado").val("");
    $("#fecha_inicio_timbrado").val("");
    $("#fecha_vencimiento_timbrado").val("");
    $("#fecha_actual_timbrado").val("");
    
}
