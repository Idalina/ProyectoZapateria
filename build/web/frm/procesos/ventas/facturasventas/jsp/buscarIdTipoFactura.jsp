<%@page import="Modelos.TiposFacturas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.TiposFacturasControlador"%>
<%
    int id_tipofactura = Integer.parseInt(request.getParameter("id_tipofactura"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    TiposFacturas tipofactura = new TiposFacturas();
    tipofactura.setId_tipofactura(id_tipofactura);
    TiposFacturasControlador.buscarId(tipofactura);

    if (tipofactura.getId_tipofactura() != 0) {
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

    System.out.println("tipo" +  tipofactura.getId_tipofactura());
    System.out.println("mensaje" + tipofactura.getNombre_tipofactura());
 
    obj.put("id_tipofactura", tipofactura.getId_tipofactura());
    obj.put("nombre_tipofactura", tipofactura.getNombre_tipofactura());
    
    
    out.print(obj);
    out.flush();
%>