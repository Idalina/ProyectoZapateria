<%@page import="Controladores.CajasControlador"%>
<%@page import="Controladores.CajasDetallesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Cajas"%>
<%

    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Cajas cajas = CajasControlador.buscarId( id_usuario);
    if (cajas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        cajas = new Cajas();
        cajas.setId_caja(0);
        java.sql.Date fecha_apertura = new java.sql.Date(new java.util.Date().getTime());
        cajas.setFecha_apertura(fecha_apertura);
        cajas.setMonto_apertura(0);
        cajas.setEstado_caja("CERRADO");
        mensaje = "Debe abrir la caja.";
    }

    int id_caja = 0;
    
    

    String contenido_detalle = CajasDetallesControlador.buscarIdCaja(id_caja);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_caja", String.valueOf(cajas.getId_caja()));
    obj.put("fecha_apertura", String.valueOf(cajas.getFecha_apertura()));
    obj.put("monto_apertura", String.valueOf(cajas.getMonto_apertura()));
    obj.put("monto_total", String.valueOf(cajas.getMonto_total()));
    obj.put("estado_caja", cajas.getEstado_caja());
    obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>
