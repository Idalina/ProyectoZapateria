

<%@page import="Controladores.TimbradosControlador"%>
<%@page import="Utiles.Utiles"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sfecha_inicio_timbrado = request.getParameter("fecha_inicio_timbrado");
    String sfecha_vencimiento_timbrado = request.getParameter("fecha_vencimiento_timbrado");
    String sfecha_actual_timbrado = request.getParameter("fecha_actual_timbrado");
    
    java.sql.Date fecha_inicio_timbrado = Utiles.stringToSqlDate(sfecha_inicio_timbrado);
    java.sql.Date fecha_vencimiento_timbrado = Utiles.stringToSqlDate(sfecha_vencimiento_timbrado);
    java.sql.Date fecha_actual_timbrado = Utiles.stringToSqlDate(sfecha_actual_timbrado);
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFecha_inicio_timbrado(fecha_inicio_timbrado);
    timbrado.setFecha_vencimiento_timbrado(fecha_vencimiento_timbrado);
    timbrado.setFecha_actual_timbrado(fecha_actual_timbrado);
    
    if (TimbradosControlador.agregar(timbrado)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

