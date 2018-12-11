
<%@page import="Modelos.Marcas"%>
<%@page import="Controladores.MarcasControlador"%>
<%@page import="org.json.simple.JSONObject"%>


<%
    String nombre_marca = request.getParameter("nombre_marca");

    String tipo = "error";
    String mensaje = "La marca ya existe";
    String nuevo = "true";
    Marcas marca = new Marcas();
    marca.setNombre_marca(nombre_marca);
    MarcasControlador.buscarNombreRepetido(marca);

  
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    System.out.println("tipo" +  marca.getId_marca());
    System.out.println("mensaje" + marca.getNombre_marca());
 
    obj.put("id_marca", marca.getId_marca());
    obj.put("nombre_marca", marca.getNombre_marca());
    
    
    out.print(obj);
    out.flush();
%>