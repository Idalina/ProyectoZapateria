

<%@page import="Controladores.CategoriaTipoCalzadosControlador"%>
<%@page import="Modelos.CategoriaTipoCalzados"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    int id_categoriatipocalzado = Integer.parseInt(request.getParameter("id_categoriatipocalzado"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    CategoriaTipoCalzados categoriatipocalzado = new CategoriaTipoCalzados();
    categoriatipocalzado.setId_categoriatipocalzado(id_categoriatipocalzado);
    
    if (CategoriaTipoCalzadosControlador.eliminar(categoriatipocalzado)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
