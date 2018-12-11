

<%@page import="org.json.simple.JSONObject"%>
<%@page import="Controladores.UsuariosControlador"%>
<%@page import="Modelos.Usuarios"%>

<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
   
    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    
    if (UsuariosControlador.eliminar(usuario)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
