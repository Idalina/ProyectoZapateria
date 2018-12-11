
<%@page import="Modelos.FacturasVentas"%>
<%@page import="Controladores.FacturasVentasControlador"%>
<%@page import="Modelos.TiposFacturas"%>
<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int id_tipofactura = Integer.parseInt(request.getParameter("id_tipofactura"));
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    
    String sfecha_venta = request.getParameter("fecha_venta");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_venta);
 

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
   
    

    TiposFacturas tipofactura= new TiposFacturas();
    tipofactura.setId_tipofactura(id_tipofactura);
    
    FacturasVentas facturaventa=new FacturasVentas ();
    facturaventa.setId_factura(id_factura);
   
    facturaventa.setFecha_venta(fecha_venta);
    facturaventa.setTipofactura(tipofactura);
    
    PedidosClientes pedido = new PedidosClientes();
    pedido.setId_pedidocliente(id_pedidocliente);
    facturaventa.setCliente(cliente);
  
   
    if (FacturasVentasControlador.modificar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>