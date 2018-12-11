

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.NacionalidadesControlador"%>
<%@page import="Modelos.Nacionalidades"%>

<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    
    if (NacionalidadesControlador.eliminarNacionalidad(nacionalidad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
