

<%@page import="Controladores.TiposFacturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String nombre_tipofactura=request.getParameter("bnombre_tipofactura");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=TiposFacturasControlador.buscarNombre(nombre_tipofactura,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

