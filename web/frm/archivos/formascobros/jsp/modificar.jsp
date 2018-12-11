
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.FormasCobrosControlador"%>
<%@page import="Modelos.FormasCobros"%>
<%
    int id_formacobro = Integer.parseInt(request.getParameter("id_formacobro"));
    String nombre_formacobro = request.getParameter("nombre_formacobro");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    FormasCobros formacobro = new FormasCobros();
    formacobro.setId_formacobro(id_formacobro);
    formacobro.setNombre_formacobro(nombre_formacobro);

    if (FormasCobrosControlador.modificar(formacobro)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
