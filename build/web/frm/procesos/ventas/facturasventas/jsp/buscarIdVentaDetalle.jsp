<%@page import="Modelos.FacturasDetalles"%>
<%@page import="Modelos.Articulos"%>
<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.FacturasDetallesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ventadetalle = Integer.parseInt(request.getParameter("id_ventadetalle"));
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    
     FacturasDetalles facturadetalle = FacturasDetallesControlador.buscarId(id_ventadetalle);
    if (facturadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
      
        facturadetalle = new  FacturasDetalles();
        facturadetalle.setId_ventadetalle(0);
        facturadetalle.setCantidad_venta(0);
        facturadetalle.setSubtotal_venta(0);
        
        
       
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        facturadetalle.setArticulo(articulo);
        
        
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_ventadetalle", String.valueOf(facturadetalle.getId_ventadetalle()));
    obj.put("cantidad_venta", String.valueOf(facturadetalle.getCantidad_venta()));
    obj.put("id_articulo", String.valueOf(facturadetalle.getArticulo().getId_articulo()));
    obj.put("nombre_articulo", facturadetalle.getArticulo().getNombre_articulo());
    obj.put("subtotal_venta",String.valueOf(facturadetalle.getSubtotal_venta()));
    
    out.print(obj);
    out.flush();
%>