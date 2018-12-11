

<%@page import="Controladores.CategoriaTipoCalzadosControlador"%>
<%@page import="Modelos.CategoriaTipoCalzados"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    int id_categoriatipocalzado = Integer.parseInt(request.getParameter("id_categoriatipocalzado"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    CategoriaTipoCalzados categoriatipocalzado = new CategoriaTipoCalzados();
    categoriatipocalzado.setId_categoriatipocalzado(id_categoriatipocalzado);
    CategoriaTipoCalzadosControlador.buscarId(categoriatipocalzado);

    if (categoriatipocalzado.getId_categoriatipocalzado() != 0) {
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

    System.out.println("tipo" +  categoriatipocalzado.getId_categoriatipocalzado());
    System.out.println("mensaje" + categoriatipocalzado.getNombre_categoriatipocalzado());
 
    obj.put("id_categoriatipocalzado", categoriatipocalzado.getId_categoriatipocalzado());
    obj.put("nombre_categoriatipocalzado", categoriatipocalzado.getNombre_categoriatipocalzado());
    
    
    out.print(obj);
    out.flush();
%>