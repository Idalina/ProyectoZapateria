

<%@page import="Controladores.ClientesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    
    if (ClientesControlador.eliminarCliente(cliente)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
