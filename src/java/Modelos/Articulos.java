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
public class Articulos {
    private int id_articulo;
    private String nombre_articulo;
    private String codigo_articulo;
    private int precio_compra;
    private int precio_venta;
    private int iva;
    private Colores color;
    private Marcas marca;
    private TiposCalzados tipocalzado;
    private Calces calce;

    public Articulos() {
    }

    public Articulos(int id_articulo, String nombre_articulo, String codigo_articulo, int precio_compra, int precio_venta, int iva, Colores color, Marcas marca, TiposCalzados tipocalzado, Calces calce) {
        this.id_articulo = id_articulo;
        this.nombre_articulo = nombre_articulo;
        this.codigo_articulo = codigo_articulo;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.iva = iva;
        this.color = color;
        this.marca = marca;
        this.tipocalzado = tipocalzado;
        this.calce = calce;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
    }

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public TiposCalzados getTipocalzado() {
        return tipocalzado;
    }

    public void setTipocalzado(TiposCalzados tipocalzado) {
        this.tipocalzado = tipocalzado;
    }

    public Calces getCalce() {
        return calce;
    }

    public void setCalce(Calces calce) {
        this.calce = calce;
    }

  

    
   
    
}
