
<%@page import="Modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    HttpSession sesion=request.getSession();
    
    int id_usuario = 0;
    String usuario_usuario="";
    String nombre_usuario="";
    
    String activo="false";
    String mensaje="La sesi�n est� cerrada.";
    

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if(usuarioLogueado!=null){
      
        
        id_usuario = usuarioLogueado.getId_usuario();
        usuario_usuario = usuarioLogueado.getUsuario_usuario();
        nombre_usuario = usuarioLogueado.getNombre_usuario();
        
        activo = "true";
        mensaje="La sesi�n est� abierta.";
    }

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("activo", activo);
    
  
    
    obj.put("id_usuario",id_usuario);
    obj.put("usuario_usuario",usuario_usuario);
    obj.put("nombre_usuario",nombre_usuario);

    out.print(obj);
    out.flush();
%>