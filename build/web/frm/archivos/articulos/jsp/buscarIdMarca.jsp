
<%@page import="Controladores.MarcasControlador"%>
<%@page import="Modelos.Marcas"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    MarcasControlador.buscarId(marca);

    if (marca.getId_marca() != 0) {
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

    System.out.println("tipo" +  marca.getId_marca());
    System.out.println("mensaje" + marca.getNombre_marca());
 
    obj.put("id_marca", marca.getId_marca());
    obj.put("nombre_marca", marca.getNombre_marca());
    
    
    out.print(obj);
    out.flush();
%>