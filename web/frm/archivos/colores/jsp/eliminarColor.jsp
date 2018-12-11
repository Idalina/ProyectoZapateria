

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ColoresControlador"%>
<%@page import="Modelos.Colores"%>

<%
    int id_color = Integer.parseInt(request.getParameter("id_color"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Colores color = new Colores();
    color.setId_color(id_color);
    
    if (ColoresControlador.eliminarColor(color)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
