
<%@page import="Controladores.ClientesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String ruc_cliente = (request.getParameter("ruc_cliente"));
    
    String tipo = "error";
    String mensaje = "Datos no repetidos.";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setRuc_cliente(ruc_cliente);
    
   ClientesControlador.buscarRuc(cliente);
    if (cliente.getId_cliente() == 0){
        tipo = "success";
        mensaje = "Ruc repetido.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    
    out.print(obj);
    out.flush();
%>
