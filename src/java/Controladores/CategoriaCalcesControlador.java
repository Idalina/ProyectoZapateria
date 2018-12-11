package Controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controladores.*;
import Modelos.CategoriaCalces;
import Utiles.Conexion;
import Utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class CategoriaCalcesControlador {
    public static boolean agregar(CategoriaCalces categoriacalce){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into categorias_calces(nombre_categoriacalce)"
                    +"values('"+categoriacalce.getNombre_categoriacalce()+"')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static CategoriaCalces buscarId(CategoriaCalces categoriacalce){
     if (Conexion.conectar()){
         String sql="Select *from categorias_calces where id_categoriacalce='" + categoriacalce.getId_categoriacalce() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 categoriacalce.setNombre_categoriacalce(rs.getString("nombre_categoriacalce"));
                 
             }else{
                 categoriacalce.setId_categoriacalce(0);
                 categoriacalce.setNombre_categoriacalce("");
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return categoriacalce;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from categorias_calces where upper (nombre_categoriacalce) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_categoriacalce offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_categoriacalce")+"</td>"
                        +"<td>"+rs.getString("nombre_categoriacalce")+"</td>"
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
   public static boolean modificarCategoriaCalce(CategoriaCalces categoriacalce){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update categorias_calces set nombre_categoriacalce= '" + categoriacalce.getNombre_categoriacalce() + "'"
                   +"where id_categoriacalce=" + categoriacalce.getId_categoriacalce();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminarCategoriaCalce (CategoriaCalces categoriacalce){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from categorias_calces where id_categoriacalce="+ categoriacalce.getId_categoriacalce();
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
