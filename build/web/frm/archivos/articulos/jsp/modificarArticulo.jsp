

<%@page import="Modelos.Calces"%>
<%@page import="Modelos.TiposCalzados"%>
<%@page import="Modelos.Marcas"%>
<%@page import="Modelos.Colores"%>
<%@page import="Controladores.ArticulosControlador"%>
<%@page import="Modelos.Articulos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    String nombre_articulo = request.getParameter("nombre_articulo");
    String codigo_articulo = request.getParameter("codigo_articulo");
    String precioc = request.getParameter("precio_compra").replaceAll("\\.", "");
    int precio_compra=Integer.parseInt((precioc));
    String preciov = request.getParameter("precio_venta").replaceAll("\\.", "");
    int precio_venta=Integer.parseInt((preciov));
    int iva=Integer.parseInt(request.getParameter("iva"));
    int id_color = Integer.parseInt(request.getParameter("id_color"));
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_tipocalzado = Integer.parseInt(request.getParameter("id_tipocalzado"));
    int id_calce = Integer.parseInt(request.getParameter("id_calce"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";
    //debo instanciar los objetos en este caso rubro y color//
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    articulo.setNombre_articulo(nombre_articulo);
    articulo.setCodigo_articulo(codigo_articulo);
    articulo.setPrecio_compra(precio_compra);
    articulo.setPrecio_venta(precio_venta);
    articulo.setIva(iva);
    Colores color = new Colores();
    color.setId_color(id_color);
    articulo.setColor(color);
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    articulo.setMarca(marca);
    TiposCalzados tipocalzado = new TiposCalzados();
    tipocalzado.setId_tipocalzado(id_tipocalzado);
    articulo.setTipocalzado(tipocalzado);
    Calces calce = new Calces();
    calce.setId_calce(id_calce);
    articulo.setCalce(calce);

    if (ArticulosControlador.modificarArticulo(articulo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();


%>
