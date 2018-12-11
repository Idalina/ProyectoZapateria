
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.TiposFacturasControlador"%>
<%@page import="Modelos.TiposFacturas"%>
<%
    int id_tipofactura = Integer.parseInt(request.getParameter("id_tipofactura"));
    String nombre_tipofactura = request.getParameter("nombre_tipofactura");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    TiposFacturas tipofactura = new TiposFacturas();
    tipofactura.setId_tipofactura(id_tipofactura);
    tipofactura.setNombre_tipofactura(nombre_tipofactura);

    if (TiposFacturasControlador.modificar(tipofactura)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
