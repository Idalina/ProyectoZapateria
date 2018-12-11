

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EstadosControlador"%>
<%@page import="Modelos.Estados"%>

<%
    int id_estado = Integer.parseInt(request.getParameter("id_estado"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Estados estado = new Estados();
    estado.setId_estado(id_estado);
    
    if (EstadosControlador.eliminar(estado)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
