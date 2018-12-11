<%@page import="Modelos.FacturasDetalles"%>
<%@page import="Modelos.FacturasVentas"%>
<%@page import="Controladores.FacturasDetallesControlador"%>
<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.FacturasVentasControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    FacturasVentas facturaventa = FacturasVentasControlador.buscarId(id_factura);
    if (facturaventa != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
        
    }    
    
    String contenido_detalle = FacturasDetallesControlador.buscarIdFactura(id_factura);
  
    FacturasVentas factura = new FacturasVentas();
    factura.setId_factura(id_factura);
    
    FacturasDetalles facturadetalle= new FacturasDetalles();
    facturadetalle.setFacturaventa(facturaventa);
    FacturasDetallesControlador.buscarTotal(facturadetalle);
        
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_factura", String.valueOf(facturaventa.getId_factura()));
    obj.put("id_tipofactura", String.valueOf(facturaventa.getTipofactura().getId_tipofactura()));
    obj.put("nombre_tipofactura", String.valueOf(facturaventa.getTipofactura().getNombre_tipofactura()));
    obj.put("id_cliente", String.valueOf(facturaventa.getCliente().getId_cliente()));
    obj.put("nombre_cliente", String.valueOf(facturaventa.getCliente().getNombre_cliente()));
    obj.put("ruc_cliente", String.valueOf(facturaventa.getCliente().getRuc_cliente()));
    obj.put ("id_pedidocliente",String.valueOf(facturaventa.getPedidocliente().getId_pedidocliente()));
    obj.put("fecha_venta", String.valueOf(facturaventa.getFecha_venta()));
    obj.put("total", facturadetalle.getSubtotal_venta());
    
System.out.println("total"+facturadetalle.getSubtotal_venta());
   
    
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>