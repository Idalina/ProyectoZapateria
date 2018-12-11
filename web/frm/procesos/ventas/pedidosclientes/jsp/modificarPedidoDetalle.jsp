<%@page import="Controladores.PedidosDetallesControlador"%>
<%@page import="Modelos.Articulos"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="Modelos.Pedidosdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_pedidodetalle = Integer.parseInt(request.getParameter("id_pedidodetalle"));
    int cantidad_articulo= Integer.parseInt(request.getParameter("cantidad_articulo"));
   int id_pedidocliente = Integer.parseInt(request.getParameter("id_pedidocliente"));
    int id_articulo = Integer.parseInt(request.getParameter("id_articulo")); 

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Pedidosdetalles pedidodetalle = new Pedidosdetalles();
    pedidodetalle.setId_pedidodetalle(id_pedidodetalle);
    pedidodetalle.setCantidad_articulo(cantidad_articulo);
    
  PedidosClientes pedido = new PedidosClientes();
  pedido.setId_pedidocliente(id_pedidocliente);
    
    Articulos articulo = new Articulos();
    articulo.setId_articulo(id_articulo);
    
   pedidodetalle.setArticulo(articulo);
   pedidodetalle.setPedidocliente(pedido);
      
    if (PedidosDetallesControlador.modificar(pedidodetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>