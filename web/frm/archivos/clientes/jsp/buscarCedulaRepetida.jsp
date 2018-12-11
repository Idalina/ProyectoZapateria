
<%@page import="Controladores.ClientesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int ci_cliente = Integer.parseInt(request.getParameter("ci_cliente"));

    String tipo = "error";
    
    Clientes cliente = new Clientes();
    cliente.setCi_cliente(ci_cliente);
    ClientesControlador.buscarCedula(cliente);

   
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
   

    obj.put("ci_cliente", cliente.getCi_cliente());
    System.out.println(cliente.getCi_cliente());

    out.print(obj);
    out.flush();
%>