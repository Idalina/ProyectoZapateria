
<%@page import="Modelos.CajasDetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CajasDetallesControlador"%>
<%@page import="Controladores.FacturasVentasControlador"%>
<%@page import="Modelos.FormasCobros"%>
<%@page import="Modelos.FacturasVentas"%>
<%@page import="Modelos.Cajas"%>

<%

    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int importe = Integer.parseInt(request.getParameter("importe"));
    int total = Integer.parseInt(request.getParameter("total"));
    int id_formacobro = Integer.parseInt(request.getParameter("id_formacobro"));
    String nombre_formacobro = request.getParameter("nombre_formacobro");

    int totald = importe - total;

    String tipo = "error";

    String mensaje = "Datos no agregados.";

    CajasDetalles detallecaja = new CajasDetalles();
    detallecaja.setImporte(importe);

    FacturasVentas facturaventa = new FacturasVentas();
    facturaventa.setId_factura(id_factura);

    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);

    FormasCobros formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    formacobro.setNombre_formacobro(nombre_formacobro);
    detallecaja.setFacturaventa(facturaventa);
    detallecaja.setCaja(caja);
    detallecaja.setFormacobro(formacobro);
    //ComprasControlador.modificarestado(compra);
    FacturasVentasControlador.modificarestadocobro(facturaventa);

    if (CajasDetallesControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    facturaventa = new FacturasVentas();
    facturaventa.setId_factura(id_factura);
    formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    formacobro.setNombre_formacobro(nombre_formacobro);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>