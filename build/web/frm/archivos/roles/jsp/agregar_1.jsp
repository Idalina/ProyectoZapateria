<%@page import="Controladores.RolesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Roles"%>
<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    String nombre_rol = request.getParameter("nombre_rol");


    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    rol.setNombre_rol(nombre_rol);
    
    if (RolesControlador.agregar(rol)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>