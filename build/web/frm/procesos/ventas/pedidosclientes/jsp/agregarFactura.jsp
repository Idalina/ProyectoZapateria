<%@page import="Controladores.FacturasDetallesControlador"%>
<%@page import="Modelos.FacturasDetalles"%>
<%@page import="Controladores.FacturasVentasControlador"%>
<%@page import="Modelos.FacturasVentas"%>
<%@page import="Modelos.Estados"%>
<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int id_estado = 2;

    String sfecha_pedido = request.getParameter("fecha_pedido");
    java.sql.Date fecha_venta = Utiles.stringToSqlDate(sfecha_pedido);

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);

    Estados estado = new Estados();
    estado.setId_estado(id_estado);
    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);
    pedidocliente.setEstado(estado);

    FacturasVentas facturaventa = new FacturasVentas();
    facturaventa.setCliente(cliente);
    facturaventa.setFecha_venta(fecha_venta);
    facturaventa.setPedidocliente(pedidocliente);
    
    FacturasDetalles facturadetalle =new FacturasDetalles();
    facturadetalle.setFacturaventa(facturaventa);

    if (FacturasVentasControlador.agregar(facturaventa)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    FacturasDetallesControlador.agregar(facturadetalle);
    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_factura", String.valueOf(facturaventa.getId_factura()));
    System.out.println("factura"+facturaventa.getId_factura());
    out.print(obj);
    out.flush();

%>