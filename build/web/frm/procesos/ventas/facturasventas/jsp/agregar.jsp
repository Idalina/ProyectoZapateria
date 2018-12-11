<%@page import="Modelos.FacturasVentas"%>
<%@page import="Controladores.FacturasVentasControlador"%>
<%@page import="Modelos.TiposFacturas"%>
<%@page import="Modelos.Estados"%>
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
    String mensaje = "Datos no agregados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);

    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);

    TiposFacturas tipofactura = new TiposFacturas();
    tipofactura.setId_tipofactura(id_tipofactura);

    FacturasVentas facturaventa = new FacturasVentas();
    facturaventa.setId_factura(id_factura);
    
    facturaventa.setFecha_venta(fecha_venta);
    facturaventa.setCliente(cliente);
    facturaventa.setPedidocliente(pedidocliente);
    facturaventa.setTipofactura(tipofactura);

    if (FacturasVentasControlador.agregar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_factura", String.valueOf(facturaventa.getId_factura()));
    out.print(obj);
    out.flush();

%>