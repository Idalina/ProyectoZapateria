
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
            $("#id_marca").focus();
            $("#id_marca").select();
        },
        error: function (e) {
            $("mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_marca").focus();
        }

    });
}
function buscarId() {
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
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_marca", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //   siguienteCampo("#nombre_marca", "#botonModificar", true);
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
function buscarNombreRepetido() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreRepetido.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            //$("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            $("#nombre_marca").focus();
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
function buscarNombreMarca() {
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
                $("#id_marca").val(id);
                $("#nombre_marca").focus();
                buscarId();
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
function modificarMarca() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/modificarMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_marca").focus();
            $("#id_marca").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarMarca() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_marca").focus();
            $("#id_marca").select();
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
    if ($("#nombre_marca").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio");
        $("#nombre_marca").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_marca").val("0");
    $("#nombre_marca").val("");
}