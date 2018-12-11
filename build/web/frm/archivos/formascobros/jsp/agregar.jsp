
<%@page import="Modelos.FormasCobros"%>
<%@page import="Controladores.FormasCobrosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_formacobro = Integer.parseInt(request.getParameter("id_formacobro"));
    String nombre_formacobro = request.getParameter("nombre_formacobro");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FormasCobros formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    formacobro.setNombre_formacobro(nombre_formacobro);

    if (FormasCobrosControlador.agregar(formacobro)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
