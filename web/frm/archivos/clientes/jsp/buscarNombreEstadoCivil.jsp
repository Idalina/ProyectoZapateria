

<%@page import="Controladores.EstadosCivilesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String nombre_estadocivil=request.getParameter("bnombre_estadocivil");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=EstadosCivilesControlador.buscarNombre(nombre_estadocivil,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

