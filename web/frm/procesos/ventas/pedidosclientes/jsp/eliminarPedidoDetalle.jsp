<%@page import="Controladores.PedidosDetallesControlador"%>
<%@page import="Modelos.Pedidosdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidodetalle = Integer.parseInt(request.getParameter("id_pedidodetalle"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Pedidosdetalles detallepedido = new Pedidosdetalles();
    detallepedido.setId_pedidodetalle(id_pedidodetalle);

    if (PedidosDetallesControlador.eliminar(detallepedido)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>