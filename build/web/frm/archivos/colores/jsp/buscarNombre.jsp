

<%@page import="Controladores.ColoresControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String nombre_color=request.getParameter("bnombre_color");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=ColoresControlador.buscarNombre(nombre_color,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

