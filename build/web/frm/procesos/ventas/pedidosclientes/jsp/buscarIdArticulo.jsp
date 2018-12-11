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
   
    out.print(obj);
    out.flush();
%>