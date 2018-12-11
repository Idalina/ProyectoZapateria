/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Administrator
 */
public class Stock {
 private int id_stock;
 private int id_articulo;
 private int cantidad_min;
 private int cantidad_max;
 private int cantidad_existente;
 private Articulos articulo;

    public Stock() {
    }

    public Stock(int id_stock, int id_articulo, int cantidad_min, int cantidad_max, int cantidad_existente, Articulos articulo) {
        this.id_stock = id_stock;
        this.id_articulo = id_articulo;
        this.cantidad_min = cantidad_min;
        this.cantidad_max = cantidad_max;
        this.cantidad_existente = cantidad_existente;
        this.articulo = articulo;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getCantidad_min() {
        return cantidad_min;
    }

    public void setCantidad_min(int cantidad_min) {
        this.cantidad_min = cantidad_min;
    }

    public int getCantidad_max() {
        return cantidad_max;
    }

    public void setCantidad_max(int cantidad_max) {
        this.cantidad_max = cantidad_max;
    }

    public int getCantidad_existente() {
        return cantidad_existente;
    }

    public void setCantidad_existente(int cantidad_existente) {
        this.cantidad_existente = cantidad_existente;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }
 

}
   
