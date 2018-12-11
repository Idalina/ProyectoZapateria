
<%@page import="Modelos.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.UsuariosControlador"%>
<%@page import="Modelos.Usuarios"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String usuario_usuario = request.getParameter("usuario_usuario");
    String clave_usuario = request.getParameter("clave_usuario");

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setUsuario_usuario(usuario_usuario);
    usuario.setClave_usuario(clave_usuario);
    Roles rol=new Roles();
    rol.setId_rol(id_rol);
    usuario.setRol(rol);
    

    if (UsuariosControlador.modificar(usuario)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
