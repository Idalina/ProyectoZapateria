

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CalcesControlador"%>
<%@page import="Modelos.Calces"%>

<%
    int id_calce = Integer.parseInt(request.getParameter("id_calce"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Calces calce = new Calces();
    calce.setId_calce(id_calce);
    
    if (CalcesControlador.eliminarCalce(calce)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
