

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.FormasCobrosControlador"%>
<%@page import="Modelos.FormasCobros"%>

<%
    int id_formacobro = Integer.parseInt(request.getParameter("id_formacobro"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    FormasCobros formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    
    if (FormasCobrosControlador.eliminar(formacobro)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
