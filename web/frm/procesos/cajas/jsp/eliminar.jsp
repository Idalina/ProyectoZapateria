

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CajasControlador"%>
<%@page import="Modelos.CajasDetalles"%>
<%@page import="Modelos.Cajas"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
if (CajasControlador.eliminar(caja)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>