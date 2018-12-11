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
    
    FacturasDetalles facturadetalle = FacturasDetallesControlador.calculartotal(id_factura);
    if (facturadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
        
    }    
        
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_factura", id_factura);
    obj.put("monto_total", facturadetalle.getSubtotal_venta());
    
   System.out.println("total"+facturadetalle.getSubtotal_venta() );
    out.print(obj);
    out.flush();
%>