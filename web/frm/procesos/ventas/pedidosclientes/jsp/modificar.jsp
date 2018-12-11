<%@page import="Utiles.Utiles"%>
<%@page import="Modelos.Estados"%>
<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int id_estado = Integer.parseInt(request.getParameter("id_estado"));
    
    String sfecha_pedido = request.getParameter("fecha_pedido");
    java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
 

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    Estados estado= new Estados();
    estado.setId_estado(id_estado);

    PedidosClientes pedido = new PedidosClientes();
    pedido.setId_pedidocliente(id_pedidocliente);
    pedido.setCliente(cliente);
    pedido.setEstado(estado);
   pedido.setFecha_pedido(fecha_pedido);
    if (PedidosClientesControlador.modificar(pedido)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>