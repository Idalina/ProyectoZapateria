
<%@page import="Modelos.CategoriaCalces"%>
<%@page import="Modelos.Calces"%>
<%@page import="Controladores.CalcesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    int id_calce = Integer.parseInt(request.getParameter("id_calce"));
    int id_categoriacalce = Integer.parseInt(request.getParameter("id_categoriacalce"));
    String numero_calce = request.getParameter("numero_calce");
   

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    CategoriaCalces categoriacalce=new CategoriaCalces();
    categoriacalce.setId_categoriacalce(id_categoriacalce);
    
        
    Calces calce = new Calces();
    calce.setId_calce(id_calce);
    calce.setNumero_calce(numero_calce);
    calce.setCategoriacalce(categoriacalce);

    if (CalcesControlador.agregar(calce)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
