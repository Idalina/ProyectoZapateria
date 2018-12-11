
<%@page import="Modelos.CategoriaTipoCalzados"%>
<%@page import="Modelos.TiposCalzados"%>
<%@page import="Controladores.TiposCalzadosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_tipocalzado = Integer.parseInt(request.getParameter("id_tipocalzado"));
    int id_categoriatipocalzado = Integer.parseInt(request.getParameter("id_categoriatipocalzado"));
    String nombre_tipocalzado = request.getParameter("nombre_tipocalzado");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    TiposCalzados tipo_calzado = new TiposCalzados();
    tipo_calzado.setId_tipocalzado(id_tipocalzado);
    tipo_calzado.setNombre_tipocalzado(nombre_tipocalzado);
    CategoriaTipoCalzados categoria_tipocalzado = new CategoriaTipoCalzados();
    categoria_tipocalzado.setId_categoriatipocalzado(id_categoriatipocalzado);
    tipo_calzado.setCategoria_tipocalzado(categoria_tipocalzado);
    
    if (TiposCalzadosControlador.agregar(tipo_calzado)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>
