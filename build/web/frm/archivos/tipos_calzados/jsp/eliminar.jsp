

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.TiposCalzadosControlador"%>
<%@page import="Modelos.TiposCalzados"%>

<%
    int id_tipocalzado = Integer.parseInt(request.getParameter("id_tipocalzado"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    TiposCalzados tipo_calzado = new TiposCalzados();
    tipo_calzado.setId_tipocalzado(id_tipocalzado);
    
    if (TiposCalzadosControlador.eliminar(tipo_calzado)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
