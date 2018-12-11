
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.EstadosControlador"%>
<%@page import="Modelos.Estados"%>
<%
    int id_estado = Integer.parseInt(request.getParameter("id_estado"));
    String nombre_estado = request.getParameter("nombre_estado");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Estados estado = new Estados();
    estado.setId_estado(id_estado);
    estado.setNombre_estado(nombre_estado);

    if (EstadosControlador.modificar(estado)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
