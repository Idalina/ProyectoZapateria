
<%@page import="Modelos.CategoriaCalces"%>
<%@page import="Controladores.CategoriaCalcesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_categoriacalce = Integer.parseInt(request.getParameter("id_categoriacalce"));
    String nombre_categoriacalce = request.getParameter("nombre_categoriacalce");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    CategoriaCalces categoriacalce = new CategoriaCalces();
    categoriacalce.setId_categoriacalce(id_categoriacalce);
    categoriacalce.setNombre_categoriacalce(nombre_categoriacalce);

    if (CategoriaCalcesControlador.agregar(categoriacalce)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
