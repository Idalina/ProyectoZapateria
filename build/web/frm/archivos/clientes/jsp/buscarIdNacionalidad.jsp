<%@page import="Modelos.Nacionalidades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.NacionalidadesControlador"%>
<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    NacionalidadesControlador.buscarId(nacionalidad);

    if (nacionalidad.getId_nacionalidad() != 0) {
        tipo = "sucess";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos  no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    System.out.println("tipo" +  nacionalidad.getId_nacionalidad());
    System.out.println("mensaje" + nacionalidad.getNombre_nacionalidad());
 
    obj.put("id_nacionalidad", nacionalidad.getId_nacionalidad());
    obj.put("nombre_nacionalidad", nacionalidad.getNombre_nacionalidad());
    
    
    out.print(obj);
    out.flush();
%>