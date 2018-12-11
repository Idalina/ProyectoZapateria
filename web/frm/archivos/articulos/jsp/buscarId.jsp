<%@page import="Modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.ArticulosControlador"%>
<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    ArticulosControlador.buscarId(articulo);

    if (articulo.getId_articulo() != 0) {
        tipo = "sucess";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } 
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    
    obj.put("id_articulo", articulo.getId_articulo());
    obj.put("nombre_articulo", articulo.getNombre_articulo());
    obj.put("codigo_articulo", articulo.getCodigo_articulo());
    obj.put("precio_compra",articulo.getPrecio_compra());
    obj.put("precio_venta",articulo.getPrecio_venta());
    obj.put("iva",articulo.getIva());
    obj.put("id_color", articulo.getColor().getId_color());
    obj.put("nombre_color", articulo.getColor().getNombre_color());
    obj.put("id_marca",articulo.getMarca().getId_marca());
    obj.put("nombre_marca",articulo.getMarca().getNombre_marca());
    obj.put("id_tipocalzado",articulo.getTipocalzado().getId_tipocalzado());
    obj.put("nombre_tipocalzado",articulo.getTipocalzado().getNombre_tipocalzado());
    obj.put("id_calce",articulo.getCalce().getId_calce());
    obj.put("numero_calce",articulo.getCalce().getNumero_calce());
    obj.put("nombre_categoriacalce",articulo.getCalce().getCategoriacalce().getNombre_categoriacalce());
    //cuando es tabla relacionada siempre se le llama primero a la  tabla y por ultimo el objeto que seria lo que queres que traiga de esa tabla.
    System.out.println("calce" + articulo.getCalce().getCategoriacalce().getNombre_categoriacalce());
    System.out.println("mensaje" + articulo.getNombre_articulo());

    out.print(obj);
    out.flush();
%>