/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Calces;
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
public class CalcesControlador {
    public static boolean agregar(Calces calce){
        boolean valor=false;
        if(Conexion.conectar()){
            String sql="insert into calces(numero_calce,id_categoriacalce)"
                    +"values('"+calce.getNumero_calce() + "',"
                    + "'" + calce.getCategoriacalce().getId_categoriacalce() + "')";
            try{
                Conexion.getSt().executeUpdate(sql);
                valor=true;
            }catch(SQLException ex){
                System.err.println("Error:"+ex);
            }
        }
        return valor;
    }
    
     public static Calces buscarIdCalce(Calces calce){
     if (Conexion.conectar()){
         String sql="Select *from calces c,categorias_calces a where "
                 + "c.id_categoriacalce=a.id_categoriacalce and "
                 + "id_calce='" + calce.getId_calce() + "'";
         System.out.println("sql"+sql);
         try{
             ResultSet rs = Conexion.getSt().executeQuery(sql);
             if (rs.next()){
                 CategoriaCalces categoriacalce=new CategoriaCalces();
                 categoriacalce.setId_categoriacalce(rs.getInt("id_categoriacalce"));
                 categoriacalce.setNombre_categoriacalce(rs.getString("nombre_categoriacalce"));
                 calce.setNumero_calce(rs.getString("numero_calce"));
                 calce.setCategoriacalce(categoriacalce);
                 
             }else{
                 calce.setId_calce(0);
                 calce.setNumero_calce("");
                 CategoriaCalces categoriacalce=new CategoriaCalces();
                 categoriacalce.setId_categoriacalce(0);
                 categoriacalce.setNombre_categoriacalce("");
                 calce.setCategoriacalce(categoriacalce);
             }
         }catch(SQLException ex){
             System.err.println("Error: " + ex);
         }
     }
     return calce;
 }
    
   public static String buscarNombre(String nombre,int pagina){
       int offset=(pagina - 1)*Utiles.REGISTROS_PAGINA;
       String valor="";
       if(Conexion.conectar()){
           try{
               String sql="select * from calces c, categorias_calces cc"
                       + " where upper (numero_calce) like '%"+
                      nombre.toUpperCase() + "%'"
                       + " order by id_calce offset "+ offset + "limit " +
                       Utiles.REGISTROS_PAGINA;
               System.out.println("--->"+sql);
               try(PreparedStatement ps =Conexion.getConn().prepareStatement(sql)){
                   ResultSet rs=ps.executeQuery();
                   String tabla="";
                   while (rs.next()){
                       tabla+="<tr>"
                        +"<td>"+rs.getString("id_calce")+"</td>"
                        +"<td>"+rs.getString("numero_calce")+"</td>"
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
   public static boolean modificarCalce(Calces calce){
       boolean valor=false;
       if (Conexion.conectar()){
           String sql="update calces set numero_calce= '" + calce.getNumero_calce() + "'"
                   + ", id_categoriacalce='" + calce.getCategoriacalce().getId_categoriacalce() + "'"
                   +"where id_calce=" + calce.getId_calce();
           try{
               Conexion.getSt().executeUpdate(sql);
               valor=true;
           }catch (SQLException ex){
               System.out.println("Error: " + ex);
           }
       }
       return valor;
   }
   
   public static boolean eliminarCalce (Calces calce){
       boolean valor= false;
       if (Conexion .conectar()){
           String sql= "delete from calces where id_calce="+ calce.getId_calce();
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
