

<%@page import="Controladores.CajasDetallesControlador"%>
<%@page import="Modelos.CajasDetalles"%>
<%@page import="Modelos.Cajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_cobrodetalle = Integer.parseInt(request.getParameter("id_cobrodetalle"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
  
    
    CajasDetalles detallecaja = new CajasDetalles();
    detallecaja.setId_cobrodetalle(id_cobrodetalle);
 

    if (CajasDetallesControlador.eliminar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>