<%@page import="Modelos.FormasCobros"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.FormasCobrosControlador"%>
<%
    int id_formacobro = Integer.parseInt(request.getParameter("id_formacobro"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    FormasCobros formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    FormasCobrosControlador.buscarId(formacobro);

    if (formacobro.getId_formacobro() != 0) {
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

    System.out.println("tipo" +  formacobro.getId_formacobro());
    System.out.println("mensaje" + formacobro.getNombre_formacobro());
 
    obj.put("id_formacobro", formacobro.getId_formacobro());
    obj.put("nombre_formacobro", formacobro.getNombre_formacobro());
    
    
    out.print(obj);
    out.flush();
%>