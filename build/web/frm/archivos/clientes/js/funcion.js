
function agregar() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando mensaje al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cliente").focus();
            $("#id_cliente").select();
        },
        error: function (e) {
            $("mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_cliente").focus();
        }

    });
}
function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ci_cliente").val(json.ci_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#direccion_cliente").val(json.direccion_cliente);
            $("#telefono_cliente").val(json.telefono_cliente);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);



            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_cliente", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //   siguienteCampo("#nombre_cliente", "#botonModificar", true);
            }
        },
        error: function (e) {

            $("#mensajes").html("No se pudo recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarIdNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdNacionalidad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);

        },
        error: function (e) {

            $("#mensajes").html("No se pudo recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarIdCiudad() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);

        },
        error: function (e) {

            $("#mensajes").html("No se pudo recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarIdEstadoCivil() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstadoCivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);

        },
        error: function (e) {

            $("#mensajes").html("No se pudo recuperar los datos");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarNombre() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente(); //no funcionaba porque el nombre no coincidia//
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });

        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarNombreNacionalidad() {
    var datosFormulario = $("#formBuscar").serialize();
   
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreNacionalidad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_nacionalidad").val(id);
                $("#nombre_nacionalidad").focus();
                buscarIdNacionalidad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });

        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreCiudad() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });

        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreEstadoCivil() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstadoCivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_estadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstadoCivil();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });

        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cliente").focus();
            $("#id_cliente").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarCliente() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_cliente").focus();
            $("#id_cliente").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function validarFormulario() {
    var valor = true;
    if ($("#nombre_cliente").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio");
        $("#nombre_cliente").focus();
    } else {
        if ($("#ci_cliente").val().trim() === "0") {
            valor = false;
            $("#mensajes").html("Cedula no puede ser 0");
            $("#ci_cliente").focus();
        }
    }

    return valor;
}
function limpiarFormulario() {
    $("#id_cliente").val("0");
    $("#nombre_cliente").val("");
    $("#apellido_cliente").val("");
    $("#ci_cliente").val("");
    $("#ruc_cliente").val("");
    $("#direccion_cliente").val("");
    $("#telefono_cliente").val("");
    $("#id_nacionalidad").val("");
    $("#nombre_nacionalidad").val("");
    $("#id_ciudad").val("");
    $("#nombre_ciudad").val("");
    $("#id_estadocivil").val("");
    $("#nombre_estadocivil").val("");
}


function buscarCedulaRepetida() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCedulaRepetida.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#ci_cliente").val(json.ci_cliente);
            $("#ci_cliente").focus();
            if ($("#ci_cliente").val().trim() === "0") {
                $("#mensajes").html("Cedula ya existe");
                $("#ci_cliente").focus();
                $("#mensajes").html(json.mensaje);
                console.log(" repetido");
            } else {
                $("#ci_cliente").val(json.ci_cliente);
                $("#direccion_cliente").focus();
                $("#mensajes").html(json.mensaje);
                console.log("no repetido");
            }

        },
        error: function (e) {

            $("#mensajes").html("La cedula es Repetida");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}
function buscarRucCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarRuc.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#ruc_cliente").val("");
                  $("#ruc_cliente").focus();
            }else{
                 
            }
            
            /*if (json.nuevo === "true") {
                
            } else {
                alert("Datos Repetidos");
                $("#ci_cliente").val("");
                $("#ci_cliente").focus();
            }*/
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarTelefonoCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarTelefono.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
            if (json.nuevo === "false"){
                 $("#telefono_cliente").val("");
                  $("#telefono_cliente").focus();
            }else{
                 
            }
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}