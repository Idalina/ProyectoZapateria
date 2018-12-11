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
public class Pedidosdetalles {
    private int id_pedidodetalle;
    private int cantidad_articulo;
    private Articulos articulo;
    private PedidosClientes pedidocliente;

    public Pedidosdetalles() {
    }

    public Pedidosdetalles(int id_pedidodetalle, int cantidad_articulo, Articulos articulo, PedidosClientes pedidocliente) {
        this.id_pedidodetalle = id_pedidodetalle;
        this.cantidad_articulo = cantidad_articulo;
        this.articulo = articulo;
        this.pedidocliente = pedidocliente;
    }

    public int getId_pedidodetalle() {
        return id_pedidodetalle;
    }

    public void setId_pedidodetalle(int id_pedidodetalle) {
        this.id_pedidodetalle = id_pedidodetalle;
    }

    public int getCantidad_articulo() {
        return cantidad_articulo;
    }

    public void setCantidad_articulo(int cantidad_articulo) {
        this.cantidad_articulo = cantidad_articulo;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public PedidosClientes getPedidocliente() {
        return pedidocliente;
    }

    public void setPedidocliente(PedidosClientes pedidocliente) {
        this.pedidocliente = pedidocliente;
    }
    
}
