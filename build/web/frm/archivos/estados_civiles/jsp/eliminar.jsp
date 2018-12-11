

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EstadosCivilesControlador"%>
<%@page import="Modelos.EstadosCiviles"%>

<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    
    if (EstadosCivilesControlador.eliminar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
