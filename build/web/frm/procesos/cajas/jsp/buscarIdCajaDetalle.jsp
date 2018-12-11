
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.FormasCobros"%>
<%@page import="Modelos.FacturasVentas"%>
<%@page import="Modelos.Cajas"%>
<%@page import="Controladores.CajasDetallesControlador"%>
<%@page import="Modelos.CajasDetalles"%>
<%
    int id_cobrodetalle = Integer.parseInt(request.getParameter("id_cobrodetalle"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    CajasDetalles detallecaja = CajasDetallesControlador.buscarId(id_cobrodetalle);
    if (detallecaja != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallecaja = new CajasDetalles();
        detallecaja.setId_cobrodetalle(0);

        Cajas caja = new Cajas();
        caja.setId_caja(0);
        detallecaja.setCaja(caja);

        FacturasVentas facturaventa = new FacturasVentas();
        facturaventa.setId_factura(0);
        detallecaja.setFacturaventa(facturaventa);

        FormasCobros formacobro = new FormasCobros();
        formacobro.setId_formacobro(0);
        detallecaja.setFacturaventa(facturaventa);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_cobrodetalle", String.valueOf(detallecaja.getId_cobrodetalle()));
    obj.put("id_caja", String.valueOf(detallecaja.getCaja().getId_caja()));
    obj.put("id_factura", String.valueOf(detallecaja.getFacturaventa().getId_factura()));
    obj.put("id_formacobro", String.valueOf(detallecaja.getFormacobro().getId_formacobro()));

    obj.put("importe", String.valueOf(detallecaja.getImporte()));
    out.print(obj);
    out.flush();
%>