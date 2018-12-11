<%@page import="Modelos.Estados"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.EstadosControlador"%>
<%
    int id_estado = Integer.parseInt(request.getParameter("id_estado"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Estados estado = new Estados();
    estado.setId_estado(id_estado);
    EstadosControlador.buscarId(estado);

    if (estado.getId_estado() != 0) {
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

    System.out.println("tipo" +  estado.getId_estado());
    System.out.println("mensaje" + estado.getNombre_estado());
 
    obj.put("id_estado", estado.getId_estado());
    obj.put("nombre_estado", estado.getNombre_estado());
    
    
    out.print(obj);
    out.flush();
%>