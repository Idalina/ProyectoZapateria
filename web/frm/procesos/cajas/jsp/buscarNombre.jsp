

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CajasControlador"%>
<%
    String nombre_caja = request.getParameter("bnombre_caja");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Búsqueda exitosa.";
    String contenido = CajasControlador.buscarNombre(nombre_caja, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    System.out.println("contenido"+contenido);
    out.print(obj);
    out.flush();
%>