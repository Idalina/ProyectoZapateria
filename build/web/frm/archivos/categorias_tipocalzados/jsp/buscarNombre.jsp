



<%@page import="Controladores.CategoriaTipoCalzadosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    String nombre_categoriatipocalzado=request.getParameter("bnombre_categoriatipocalzado");
    int pagina=Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje="Busqueda exitosa";
    String contenido=CategoriaTipoCalzadosControlador.buscarNombre(nombre_categoriatipocalzado,pagina);
    
    JSONObject obj=new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    
out.print(obj);
out.flush();

%>

