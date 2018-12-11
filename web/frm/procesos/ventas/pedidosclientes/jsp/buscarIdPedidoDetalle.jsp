<%@page import="Modelos.Articulos"%>
<%@page import="Utiles.Utiles"%>
<%@page import="Controladores.PedidosDetallesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="Modelos.Pedidosdetalles"%>
<%@page import="Modelos.PedidosClientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_pedidodetalle = Integer.parseInt(request.getParameter("id_pedidodetalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Pedidosdetalles pedidodetalle = PedidosDetallesControlador.buscarId(id_pedidodetalle);
    if (pedidodetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        pedidodetalle = new Pedidosdetalles();
        pedidodetalle.setId_pedidodetalle(0);
        
        PedidosClientes pedido = new PedidosClientes();
        pedido.setId_pedidocliente(0);
        pedidodetalle.setPedidocliente(pedido);
        
        Articulos articulo = new Articulos();
        articulo.setId_articulo(0);
        articulo.setNombre_articulo("");
        pedidodetalle.setArticulo(articulo);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_pedidodetalle", String.valueOf(pedidodetalle.getId_pedidodetalle()));
   System.out.println("id_pedidodetalle"+pedidodetalle.getId_pedidodetalle());
    obj.put("id_pedidocliente", String.valueOf(pedidodetalle.getPedidocliente().getId_pedidocliente()));
    
       System.out.println("id_pedidocliente"+pedidodetalle.getPedidocliente().getId_pedidocliente());
       obj.put("id_articulo", String.valueOf(pedidodetalle.getArticulo().getId_articulo()));
        System.out.println("id_articulo"+pedidodetalle.getArticulo().getId_articulo());
       
    obj.put("nombre_articulo", pedidodetalle.getArticulo().getNombre_articulo());
       System.out.println("nombre_articulo"+pedidodetalle.getArticulo().getNombre_articulo());
    obj.put("cantidad_articulo", String.valueOf(pedidodetalle.getCantidad_articulo()));
       System.out.println("cantidad_articulo"+pedidodetalle.getCantidad_articulo());
    out.print(obj);
    out.flush();
%>