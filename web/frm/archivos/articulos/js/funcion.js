
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
            $("#id_articulo").focus();
            $("#id_articulo").select();
        },
        error: function (e) {
            $("mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_articulo").focus();
        }

    });
}
function insertarcolor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarColor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando mensaje al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_color").focus();
            $("#id_color").select();
        },
        error: function (e) {
            $("mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_color").focus();
        }

    });
}


function buscarIdArticulo() {
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

            var compra = dar_formato_numero(json.precio_compra, ",", ".");
            var venta = dar_formato_numero(json.precio_venta, ",", ".");
            $("#mensajes").html(json.mensaje);
            $("#id_articulo").val(json.id_articulo);
            $("#nombre_articulo").val(json.nombre_articulo);
            $("#codigo_articulo").val(json.codigo_articulo);
            $("#precio_compra").val(compra);
            $("#precio_venta").val(venta);
            $("#iva").val(json.iva);
            $("#id_color").val(json.id_color);
            $("#nombre_color").val(json.nombre_color);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);
            $("#id_tipocalzado").val(json.id_tipocalzado);
            $("#nombre_tipocalzado").val(json.nombre_tipocalzado);
            $("#id_calce").val(json.id_calce);
            $("#numero_calce").val(json.numero_calce);
            $("#nombre_categoriacalce").val(json.nombre_categoriacalce);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_articulo", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //   siguienteCampo("#nombre_articulo", "#botonModificar", true);
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
function buscarIdColor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdColor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_color").val(json.id_color);
            $("#nombre_color").val(json.nombre_color);

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
function buscarIdMarca() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdMarca.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_marca").val(json.id_marca);
            $("#nombre_marca").val(json.nombre_marca);

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
function buscarIdTipoCalzado() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipoCalzado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_tipocalzado").val(json.id_tipocalzado);
            $("#id_categoriatipocalzado").val(json.id_categoriatipocalzado);
            $("#nombre_tipocalzado").val(json.nombre_tipocalzado);
            $("#nombre_categoriatipocalzado").val(json.nombre_categoriatipocalzado);


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
function buscarIdCalce() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCalce.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {

            $("#mensajes").html(json.mensaje);
            $("#id_calce").val(json.id_calce);
            $("#numero_calce").val(json.numero_calce);
            $("#id_categoriacalce").val(json.id_categoriacalce);
            $("#nombre_categoriacalce").val(json.nombre_categoriacalce);

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

function buscarNombreArticulo() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreArticulo.jsp',
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
                $("#id_articulo").val(id);
                $("#nombre_articulo").focus();
                buscarIdArticulo();
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
function buscarNombreColor() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreColor.jsp',
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
                $("#id_color").val(id);
                $("#nombre_color").focus();
                buscarIdColor();
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

function buscarNombreMarca() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreMarca.jsp',
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
                buscarIdMarca();// no coloque el nombre buscarIdMarca//
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
function buscarNombreTipoCalzado() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTipoCalzado.jsp',
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
                $("#id_tipocalzado").val(id);
                $("#nombre_tipocalzado").focus();
                buscarIdTipoCalzado();
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
function buscarNombreCalce() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCalce.jsp',
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
                $("#id_calce").val(id);
                $("#nombre_calce").focus();
                buscarIdCalce();
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

function modificarArticulo() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/modificarArticulo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_articulo").focus();
            $("#id_articulo").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}

function eliminarArticulo() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarArticulo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_articulo").focus();
            $("#id_articulo").select();
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
    if ($("#nombre_articulo").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Articulo no puede estar vacio");
        $("#nombre_articulo").focus();
    } else {
        if ($("#codigo_articulo").val().trim() === "") {
            valor = false;
            $("#mensajes").html("El Codigo no puede estar vacio");
            $("#codigo_articulo").focus();
        } else {
            if ($("#precio_compra").val().trim() === "") {
                valor = false;
                $("#mensajes").html("Precio no puede estar vacio");
                $("#precio_compra").focus();
            } else {
                if ($("#precio_venta").val().trim() === "") {
                    valor = false;
                    $("#mensajes").html("Precio no puede estar vacio");
                    $("#precio_venta").focus();
                } else {
                    if ($("#iva").val().trim() === "") {
                        valor = false;
                        $("#mensajes").html("IVA no puede estar vacio");
                        $("#iva").focus();
                    }

                }
                if ($("#nombre_color").val().trim() === "") {
                    valor = false;
                    $("#mensajes").html(" El Color no puede estar vacio");
                    $("#nombre_color").focus();
                } else {
                    if ($("#nombre_marca").val().trim() === "") {
                        valor = false;
                        $("#mensajes").html(" La Marca no puede estar vacio");
                        $("#nombre_marca").focus();

                    } else {
                        if ($("#nombre_tipocalzado").val().trim() === "") {
                            valor = false;
                            $("#mensajes").html("El Tipo Calzado no puede estar vacio");
                            $("#nombre_tipocalzado").focus();
                        } else {
                            if ($("#numero_calce").val().trim() === "") {
                                valor = false;
                                $("#mensajes").html(" El Calce no puede estar vacio");
                                $("#numero_calce").focus();
                            }
                        }
                    }
                }
            }
        }

    }
    return valor;
}
function limpiarFormulario() {
    $("#id_articulo").val("0");
    $("#nombre_articulo").val("");
    $("#codigo_articulo").val("");
    $("#precio_compra").val("");
    $("#precio_venta").val("");
    $("#iva").val("");
    $("#id_color").val("0");
    $("#nombre_color").val("");
    $("#id_marca").val("0");
    $("#nombre_marca").val("");
    $("#id_tipocalzado").val("0");
    $("#nombre_tipocalzado").val("");
    $("#id_calce").val("0");
    $("#numero_calce").val("");
    $("#nombre_categoriacalce").val("");
}