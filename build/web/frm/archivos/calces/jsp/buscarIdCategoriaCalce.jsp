<%@page import="Modelos.CategoriaCalces"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.CategoriaCalcesControlador"%>
<%
    int id_categoriacalce = Integer.parseInt(request.getParameter("id_categoriacalce"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    CategoriaCalces categoriacalce = new CategoriaCalces();
    categoriacalce.setId_categoriacalce(id_categoriacalce);
    CategoriaCalcesControlador.buscarId(categoriacalce);

    if (categoriacalce.getId_categoriacalce() != 0) {
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

    System.out.println("tipo" +  categoriacalce.getId_categoriacalce());
    System.out.println("mensaje" + categoriacalce.getNombre_categoriacalce());
 
    obj.put("id_categoriacalce", categoriacalce.getId_categoriacalce());
    obj.put("nombre_categoriacalce", categoriacalce.getNombre_categoriacalce());
    
    
    out.print(obj);
    out.flush();
%>