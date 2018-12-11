<%@page import="Modelos.TiposCalzados"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.TiposCalzadosControlador"%>
<%
    int id_tipocalzado = Integer.parseInt(request.getParameter("id_tipocalzado"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    TiposCalzados tipo_calzado = new TiposCalzados();
    tipo_calzado.setId_tipocalzado(id_tipocalzado);
    TiposCalzadosControlador.buscarId(tipo_calzado);

    if (tipo_calzado.getId_tipocalzado() != 0) {
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

    System.out.println("tipo" + tipo_calzado.getId_tipocalzado());
    System.out.println("mensaje" + tipo_calzado.getNombre_tipocalzado());

    obj.put("id_tipocalzado", tipo_calzado.getId_tipocalzado());
    obj.put("nombre_tipocalzado", tipo_calzado.getNombre_tipocalzado());
    
    obj.put("id_categoriatipocalzado",tipo_calzado.getCategoria_tipocalzado().getId_categoriatipocalzado());
    obj.put("nombre_categoriatipocalzado",tipo_calzado.getCategoria_tipocalzado().getNombre_categoriatipocalzado());
      System.out.println("mensaje" + tipo_calzado.getCategoria_tipocalzado().getNombre_categoriatipocalzado());

  
    out.print(obj);
    out.flush();
%>