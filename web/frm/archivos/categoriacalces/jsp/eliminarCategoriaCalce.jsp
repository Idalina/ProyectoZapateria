

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CategoriaCalcesControlador"%>
<%@page import="Modelos.CategoriaCalces"%>

<%
    int id_categoriacalce = Integer.parseInt(request.getParameter("id_categoriacalce"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    CategoriaCalces categoriacalce = new CategoriaCalces();
    categoriacalce.setId_categoriacalce(id_categoriacalce);
    
    if (CategoriaCalcesControlador.eliminarCategoriaCalce(categoriacalce)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
