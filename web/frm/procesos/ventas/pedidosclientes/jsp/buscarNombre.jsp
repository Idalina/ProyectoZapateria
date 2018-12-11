<%@page import="Controladores.PedidosClientesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_pedido = request.getParameter("bnombre_pedido");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = PedidosClientesControlador.buscarNombre(nombre_pedido, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
     System.out.println("contenido"+contenido);
    out.print(obj);
    out.flush();
%>