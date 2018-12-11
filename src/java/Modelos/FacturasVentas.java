/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class FacturasVentas {

    private int id_factura;
    private int id_tipofactura;
    private Date fecha_venta;
    private Clientes cliente;
    private TiposFacturas tipofactura;
    private PedidosClientes pedidocliente;
    private FormasCobros formacobro;

    public FacturasVentas() {
    }

    public FacturasVentas(int id_factura, int id_tipofactura, Date fecha_venta, Clientes cliente, TiposFacturas tipofactura, PedidosClientes pedidocliente, FormasCobros formacobro) {
        this.id_factura = id_factura;
        this.id_tipofactura = id_tipofactura;
        this.fecha_venta = fecha_venta;
        this.cliente = cliente;
        this.tipofactura = tipofactura;
        this.pedidocliente = pedidocliente;
        this.formacobro = formacobro;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_tipofactura() {
        return id_tipofactura;
    }

    public void setId_tipofactura(int id_tipofactura) {
        this.id_tipofactura = id_tipofactura;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public TiposFacturas getTipofactura() {
        return tipofactura;
    }

    public void setTipofactura(TiposFacturas tipofactura) {
        this.tipofactura = tipofactura;
    }

    public PedidosClientes getPedidocliente() {
        return pedidocliente;
    }

    public void setPedidocliente(PedidosClientes pedidocliente) {
        this.pedidocliente = pedidocliente;
    }

    public FormasCobros getFormacobro() {
        return formacobro;
    }

    public void setFormacobro(FormasCobros formacobro) {
        this.formacobro = formacobro;
    }

    
}
