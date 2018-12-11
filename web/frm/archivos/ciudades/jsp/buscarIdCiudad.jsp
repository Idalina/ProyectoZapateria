<%@page import="Modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.CiudadesControlador"%>
<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    CiudadesControlador.buscarIdCiudad(ciudad);

    if (ciudad.getId_ciudad() != 0) {
        tipo = "sucess";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos  no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    System.out.println("tipo" +  ciudad.getId_ciudad());
    System.out.println("mensaje" + ciudad.getNombre_ciudad());
 
    obj.put("id_ciudad", ciudad.getId_ciudad());
    obj.put("nombre_ciudad", ciudad.getNombre_ciudad());
    
    
    out.print(obj);
    out.flush();
%>