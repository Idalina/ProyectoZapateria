<%@page import="Modelos.EstadosCiviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.EstadosCivilesControlador"%>
<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    EstadosCivilesControlador.buscarId(estadocivil);

    if (estadocivil.getId_estadocivil() != 0) {
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

    System.out.println("tipo" +  estadocivil.getId_estadocivil());
    System.out.println("mensaje" + estadocivil.getNombre_estadocivil());
 
    obj.put("id_estadocivil", estadocivil.getId_estadocivil());
    obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
    
    
    out.print(obj);
    out.flush();
%>