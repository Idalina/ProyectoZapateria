<%@page import="Modelos.FacturasVentas"%>
<%@page import="Controladores.FacturasDetallesControlador"%>
<%@page import="Modelos.FacturasDetalles"%>

<%@page import="Modelos.Articulos"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int cantidad_venta = Integer.parseInt(request.getParameter("cantidad_venta"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int subtotal_venta = Integer.parseInt(request.getParameter("subtotal_venta"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FacturasVentas facturaventa = new FacturasVentas();
    facturaventa.setId_factura(id_factura);

    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);

    //detallepedido.setId_detallepedido(id_detallepedido);
    FacturasDetalles facturadetalle = new FacturasDetalles();
    facturadetalle.setCantidad_venta(cantidad_venta);
    facturadetalle.setSubtotal_venta(subtotal_venta);
    facturadetalle.setFacturaventa(facturaventa);
    facturadetalle.setArticulo(articulo);

    if (FacturasDetallesControlador.agregar(facturadetalle)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>