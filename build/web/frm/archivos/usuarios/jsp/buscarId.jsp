<%@page import="Modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="Controladores.UsuariosControlador"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    UsuariosControlador.buscarId(usuario);

    if (usuario.getId_usuario() != 0) {
        tipo = "sucess";
        mensaje = "Datos encontrados";
        nuevo = "false";
    } else {
        tipo = "success";
        mensaje = "Datos  no encontrados";
        nuevo = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    System.out.println("tipo" +  usuario.getId_usuario());
    System.out.println("mensaje" + usuario.getNombre_usuario());
 
    obj.put("id_usuario", usuario.getId_usuario());
    obj.put("nombre_usuario", usuario.getNombre_usuario());
    obj.put("usuario_usuario",usuario.getUsuario_usuario());
    obj.put("clave_usuario",usuario.getClave_usuario());
    obj.put("id_rol",usuario.getRol().getId_rol());
    obj.put("nombre_rol",usuario.getRol().getNombre_rol());
    
    
    out.print(obj);
    out.flush();
%>