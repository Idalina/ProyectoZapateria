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
    java.sql.Date fecha_pedido = Utiles.stringToSqlDate(sfecha_pedido);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    Estados estado=new Estados();
    estado.setId_estado(id_estado);
    
    PedidosClientes pedidocliente=new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);
    pedidocliente.setFecha_pedido(fecha_pedido);
    pedidocliente.setCliente(cliente);
    pedidocliente.setEstado(estado);
      
    
    if (PedidosClientesControlador.agregar(pedidocliente)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_pedidocliente", String.valueOf(pedidocliente.getId_pedidocliente()));
    out.print(obj);
    out.flush();
    
%>