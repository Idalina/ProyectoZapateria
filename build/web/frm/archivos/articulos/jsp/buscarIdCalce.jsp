<%@page import="Modelos.Calces"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CalcesControlador"%>
<%
    int id_calce = Integer.parseInt(request.getParameter("id_calce"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Calces calce = new Calces();
    calce.setId_calce(id_calce);
    CalcesControlador.buscarIdCalce(calce);
    
    if (calce.getId_calce() != 0) {
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
    
    System.out.println("tipo" + calce.getId_calce());
    System.out.println("mensaje" + calce.getNumero_calce());
    
    obj.put("id_calce", calce.getId_calce());
    obj.put("id_categoriacalce", calce.getCategoriacalce().getId_categoriacalce());
    obj.put("numero_calce", calce.getNumero_calce());
    obj.put("nombre_categoriacalce", calce.getCategoriacalce().getNombre_categoriacalce());
    
    out.print(obj);
    out.flush();
%>