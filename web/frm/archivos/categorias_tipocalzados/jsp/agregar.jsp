


<%@page import="Controladores.CategoriaTipoCalzadosControlador"%>
<%@page import="Modelos.CategoriaTipoCalzados"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_categoriatipocalzado = Integer.parseInt(request.getParameter("id_categoriatipocalzado"));
    String nombre_categoriatipocalzado = request.getParameter("nombre_categoriatipocalzado");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    CategoriaTipoCalzados categoriatipocalzado = new CategoriaTipoCalzados();
    categoriatipocalzado.setId_categoriatipocalzado(id_categoriatipocalzado);
    categoriatipocalzado.setNombre_categoriatipocalzado(nombre_categoriatipocalzado);

    if (CategoriaTipoCalzadosControlador.agregar(categoriatipocalzado)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);

    System.out.println("tipo" + tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
