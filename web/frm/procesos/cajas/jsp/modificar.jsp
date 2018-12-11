<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CajasControlador"%>
<%@page import="Modelos.Cajas"%>
<%@page import="Modelos.Usuarios"%>
<%@page import="Utiles.Utiles"%>
<%
    /*int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
    int monto_apertura = Integer.parseInt(request.getParameter("monto_apertura"));
    int monto_total = Integer.parseInt(request.getParameter("monto_total"));*/
    String estado_caja = "CERRADO";
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    Cajas caja = new Cajas();
    caja.setEstado_caja(estado_caja);
    caja.setUsuario(usuario);

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    if (CajasControlador.modificar(caja)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>