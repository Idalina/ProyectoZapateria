
<%@page import="Controladores.FacturasDetallesControlador"%>
<%@page import="Modelos.FacturasDetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ventadetalle = Integer.parseInt(request.getParameter("id_ventadetalle"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FacturasDetalles facturadetalle = new FacturasDetalles();
    facturadetalle.setId_ventadetalle(id_ventadetalle);

    if (FacturasDetallesControlador.eliminar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>