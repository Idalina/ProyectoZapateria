
<%@page import="Modelos.EstadosCiviles"%>
<%@page import="Modelos.Ciudades"%>
<%@page import="Modelos.Nacionalidades"%>
<%@page import="Controladores.ClientesControlador"%>
<%@page import="Modelos.Clientes"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    int ci_cliente = Integer.parseInt(request.getParameter("ci_cliente"));
     String ruc_cliente=request.getParameter("ruc_cliente");
    String nombre_cliente = request.getParameter("nombre_cliente");
    String apellido_cliente = request.getParameter("apellido_cliente");
    String direccion_cliente = request.getParameter("direccion_cliente");
    String telefono_cliente = request.getParameter("telefono_cliente");
   int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
   int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
   int id_estadocivil= Integer.parseInt(request.getParameter("id_estadocivil"));
   

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    Nacionalidades nacionalidad=new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    Ciudades ciudad=new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    EstadosCiviles estadocivil=new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    cliente.setApellido_cliente(apellido_cliente);
    cliente.setCi_cliente(ci_cliente);
    cliente.setRuc_cliente(ruc_cliente);
    cliente.setDireccion_cliente(direccion_cliente);
    cliente.setTelefono_cliente(telefono_cliente);
    cliente.setCiudad(ciudad);
    cliente.setNacionalidad(nacionalidad);
    cliente.setEstadocivil(estadocivil);
    

    if (ClientesControlador.modificarCliente(cliente)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>
