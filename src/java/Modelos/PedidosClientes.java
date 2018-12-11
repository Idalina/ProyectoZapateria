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
public class PedidosClientes {
    private int id_pedidocliente;
    private Date fecha_pedido;
    private Clientes cliente;
    private Estados estado;

    public PedidosClientes() {
    }

    public PedidosClientes(int id_pedidocliente, Date fecha_pedido, Clientes cliente, Estados estado) {
        this.id_pedidocliente = id_pedidocliente;
        this.fecha_pedido = fecha_pedido;
        this.cliente = cliente;
        this.estado = estado;
    }

    public int getId_pedidocliente() {
        return id_pedidocliente;
    }

    public void setId_pedidocliente(int id_pedidocliente) {
        this.id_pedidocliente = id_pedidocliente;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

   
}
