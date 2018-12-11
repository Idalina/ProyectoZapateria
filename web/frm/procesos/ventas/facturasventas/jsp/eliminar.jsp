<%@page import="Modelos.FacturasVentas"%>
<%@page import="Controladores.FacturasVentasControlador"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturasVentas facturaventa = new FacturasVentas();
    facturaventa.setId_factura(id_factura);

    if (FacturasVentasControlador.eliminar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>