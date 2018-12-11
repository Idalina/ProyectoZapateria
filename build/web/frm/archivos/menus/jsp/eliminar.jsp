<%@page import="Controladores.MenusControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="Modelos.Menus"%>
<%
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));

  
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    
    if (MenusControlador.eliminar(menu)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>