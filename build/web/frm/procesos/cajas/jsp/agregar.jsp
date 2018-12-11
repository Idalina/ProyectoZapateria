
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.CajasControlador"%>
<%@page import="Modelos.Cajas"%>
<%@page import="Modelos.Usuarios"%>
<%@page import="Utiles.Utiles"%>
<%

    
    String sfecha_apertura = request.getParameter("fecha_apertura");
    java.sql.Date fecha_apertura = Utiles.stringToSqlDate(sfecha_apertura);
   int monto_apertura=Integer.parseInt(request.getParameter("monto_apertura"));
    String estado_caja = "ABIERTO";
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);

    Cajas caja = new Cajas();
   
    caja.setFecha_apertura(fecha_apertura);
    caja.setMonto_apertura(monto_apertura);
    caja.setEstado_caja(estado_caja);
    caja.setUsuario(usuario);

    if (CajasControlador.agregar(caja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_caja", String.valueOf(caja.getId_caja()));
    out.print(obj);
    out.flush();

%>