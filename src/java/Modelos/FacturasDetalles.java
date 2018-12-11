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
public class FacturasDetalles {
    private int id_ventadetalle;
    private int cantidad_venta;
    private int subtotal_venta;
    private TiposFacturas tipofactura;
    private Articulos articulo;
    private FacturasVentas facturaventa;

    public FacturasDetalles() {
    }

    public FacturasDetalles(int id_ventadetalle, int cantidad_venta, int subtotal_venta, TiposFacturas tipofactura, Articulos articulo, FacturasVentas facturaventa) {
        this.id_ventadetalle = id_ventadetalle;
        this.cantidad_venta = cantidad_venta;
        this.subtotal_venta = subtotal_venta;
        this.tipofactura = tipofactura;
        this.articulo = articulo;
        this.facturaventa = facturaventa;
    }

    public int getId_ventadetalle() {
        return id_ventadetalle;
    }

    public void setId_ventadetalle(int id_ventadetalle) {
        this.id_ventadetalle = id_ventadetalle;
    }

    public int getCantidad_venta() {
        return cantidad_venta;
    }

    public void setCantidad_venta(int cantidad_venta) {
        this.cantidad_venta = cantidad_venta;
    }

    public int getSubtotal_venta() {
        return subtotal_venta;
    }

    public void setSubtotal_venta(int subtotal_venta) {
        this.subtotal_venta = subtotal_venta;
    }

    public TiposFacturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(TiposFacturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public FacturasVentas getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(FacturasVentas facturaventa) {
        this.facturaventa = facturaventa;
    }


    
    
}
