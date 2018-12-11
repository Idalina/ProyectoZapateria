
package Controladores;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utiles.Conexion;
import java.sql.ResultSet;
import Modelos.Nacionalidades;
import Utiles.Utiles;
public class NacionalidadesControlador {
    public static boolean agregar(Nacionalidades nacionalidad){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into nacionalidades(nombre_nacionalidad)"
                    +"values('"+nacionalidad.getNombre_nacionalidad()+"')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static Nacionalidades buscarId(Nacionalidades nacionalidad){
     if (Conexion.conectar()){
         String sql="Select * from nacionalidades where id_nacionalidad='" + nacionalidad.getId_nacionalidad() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                 
             }else{
                 nacionalidad.setId_nacionalidad(0);
                 nacionalidad.setNombre_nacionalidad("");
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return nacionalidad;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from nacionalidades where upper (nombre_nacionalidad) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_nacionalidad offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_nacionalidad")+"</td>"
                        +"<td>"+rs.getString("nombre_nacionalidad")+"</td>"
                        +"</tr>";       
                   }
                   if(tabla.equals("")){
                       tabla="<tr><td colspan=2>No existen registros...</td></tr>";
                   }
                   ps.close();
                   valor=tabla;
               }catch (SQLException ex){
                   System.err.println("Error:"+ex);
               }
               Conexion.cerrar();
           }catch (Exception ex){
               System.err.println("Error:"+ex);
           }
       }
       Conexion.cerrar();
       return valor;
   } 
   public static boolean modificarNacionalidad(Nacionalidades nacionalidad){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update nacionalidades set nombre_nacionalidad= '" + nacionalidad.getNombre_nacionalidad() + "'"
                   +"where id_nacionalidad=" + nacionalidad.getId_nacionalidad();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminarNacionalidad (Nacionalidades nacionalidad){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from nacionalidades where id_nacionalidad="+ nacionalidad.getId_nacionalidad();
            System.out.println("--->"+sql);
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.err.println("Error:" + ex);
           }
       }
       return valor;
   }
}
