<%@page import="Controladores.PermisosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    String nombre_permiso = request.getParameter("bnombre_permiso");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "B�squeda exitosa.";
    String contenido = PermisosControlador.buscarNombre(nombre_permiso, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>