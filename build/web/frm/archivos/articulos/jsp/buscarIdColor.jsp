<%@page import="Modelos.Colores"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.ColoresControlador"%>
<%
    int id_color = Integer.parseInt(request.getParameter("id_color"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Colores color = new Colores();
    color.setId_color(id_color);
    ColoresControlador.buscarId(color);

    if (color.getId_color() != 0) {
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

    System.out.println("tipo" +  color.getId_color());
    System.out.println("mensaje" + color.getNombre_color());
 
    obj.put("id_color", color.getId_color());
    obj.put("nombre_color", color.getNombre_color());
    
    
    out.print(obj);
    out.flush();
%>