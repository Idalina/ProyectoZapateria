

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CiudadesControlador"%>
<%@page import="Modelos.Ciudades"%>

<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    
    if (CiudadesControlador.eliminarCiudad(ciudad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
