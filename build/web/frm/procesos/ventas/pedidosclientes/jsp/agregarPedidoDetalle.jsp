<%@page import="Controladores.PedidosDetallesControlador"%>
<%@page import="Modelos.Articulos"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="Modelos.Pedidosdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int cantidad_articulo = Integer.parseInt(request.getParameter("cantidad_articulo"));
    int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Pedidosdetalles pedidodetalle = new Pedidosdetalles();
    //detallepedido.setId_detallepedido(id_detallepedido);
    pedidodetalle.setCantidad_articulo(cantidad_articulo);
    
    PedidosClientes pedidocliente = new PedidosClientes();
    pedidocliente.setId_pedidocliente(id_pedidocliente);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
      pedidodetalle.setPedidocliente(pedidocliente);
      pedidodetalle.setArticulo(articulo);
      
    if (PedidosDetallesControlador.agregar(pedidodetalle)) {
        tipo = "success";
        mensaje = "Datos agregados.";
        
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>