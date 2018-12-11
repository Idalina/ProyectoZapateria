

<%@page import="Controladores.CategoriaCalcesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String nombre_categoriacalce=request.getParameter("bnombre_categoriacalce");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=CategoriaCalcesControlador.buscarNombre(nombre_categoriacalce,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

