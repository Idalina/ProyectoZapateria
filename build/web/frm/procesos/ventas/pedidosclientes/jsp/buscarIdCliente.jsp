
<%@page import="Controladores.ClientesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    ClientesControlador.buscarId(cliente);

    if (cliente.getId_cliente() != 0) {
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

    System.out.println("tipo" +  cliente.getId_cliente());
    System.out.println("mensaje" + cliente.getNombre_cliente());
 
    obj.put("id_cliente", cliente.getId_cliente());
    obj.put("nombre_cliente", (cliente.getNombre_cliente()+" "+cliente.getApellido_cliente()));
    obj.put("apellido_cliente", cliente.getApellido_cliente());
    obj.put("ci_cliente", cliente.getCi_cliente());
    obj.put("direccion_cliente", cliente.getDireccion_cliente());
    obj.put("telefono_cliente", cliente.getTelefono_cliente());
    obj.put("id_nacionalidad",cliente.getNacionalidad().getId_nacionalidad());
    obj.put("nombre_nacionalidad",cliente.getNacionalidad().getNombre_nacionalidad());
    obj.put("id_ciudad",cliente.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad",cliente.getCiudad().getNombre_ciudad());
    obj.put("id_estadocivil",cliente.getEstadocivil().getId_estadocivil());
    obj.put("nombre_estadocivil",cliente.getEstadocivil().getNombre_estadocivil());
   
    
    
    
    out.print(obj);
    out.flush();
%>