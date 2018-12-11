<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    PedidosClientes pedido = new PedidosClientes();
    pedido.setId_pedidocliente(id_pedidocliente);

    if (PedidosClientesControlador.eliminar(pedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>