

<%@page import="Controladores.CalcesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String numero_calce=request.getParameter("bnombre_calce");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=CalcesControlador.buscarNombre(numero_calce,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

