
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EstadosCivilesControlador"%>
<%@page import="Modelos.EstadosCiviles"%>
<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);

    if (EstadosCivilesControlador.modificar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
