<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.PedidosDetallesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    PedidosClientes pedidos = PedidosClientesControlador.buscarId(id_pedidocliente);
    if (pedidos != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } 
    
    String contenido_detalle = PedidosDetallesControlador.buscarIdPedido(id_pedidocliente);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_pedido", String.valueOf(pedidos.getId_pedidocliente()));
    obj.put("fecha_pedido", String.valueOf(pedidos.getFecha_pedido()));
    obj.put("id_cliente", String.valueOf(pedidos.getCliente().getId_cliente()));
    obj.put("id_estado", String.valueOf(pedidos.getEstado().getId_estado()));
    obj.put("nombre_cliente", pedidos.getCliente().getNombre_cliente());
    obj.put("nombre_estado", pedidos.getEstado().getNombre_estado());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>