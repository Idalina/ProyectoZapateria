/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Stock;
import Utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class StockControlador {

    public static boolean agregar(Stock stock) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "Select * from stock where  "
                    + "id_articulo='" + stock.getArticulo().getId_articulo() + "'";
            System.out.println("sql" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (!(rs.next())) {
                    String sql2 = "insert into stock(id_articulo,cantidad_min,catidad_max,cantidad_existente)"
                            + "values('" + stock.getArticulo().getId_articulo() + "',"
                            + "'" + stock.getCantidad_min() + "',"
                            + "'" + stock.getCantidad_max() + "',"
                            + "'" + stock.getCantidad_existente() + "',"
                            +"')";
                    System.out.println("sql" + sql2);
                    try {
                        Conexion.getSt().executeUpdate(sql2);
                        valor = true;
                    } catch (SQLException ex2) {
                        System.err.println("Error:" + ex2);
                    }

                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }
        }
        return valor;
    }



}
