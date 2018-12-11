

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.TiposFacturasControlador"%>
<%@page import="Modelos.TiposFacturas"%>

<%
    int id_tipofactura = Integer.parseInt(request.getParameter("id_tipofactura"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    TiposFacturas tipofactura = new TiposFacturas();
    tipofactura.setId_tipofactura(id_tipofactura);
    
    if (TiposFacturasControlador.eliminar(tipofactura)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
