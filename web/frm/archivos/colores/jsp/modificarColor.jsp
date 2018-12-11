
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ColoresControlador"%>
<%@page import="Modelos.Colores"%>
<%
    int id_color = Integer.parseInt(request.getParameter("id_color"));
    String nombre_color = request.getParameter("nombre_color");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Colores color = new Colores();
    color.setId_color(id_color);
    color.setNombre_color(nombre_color);

    if (ColoresControlador.modificarColor(color)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
