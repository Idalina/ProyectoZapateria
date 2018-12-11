/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author ALUMNO
 */
public class TiposFacturas {
    private int id_tipofactura;
    private String nombre_tipofactura;

    public TiposFacturas() {
    }

    public TiposFacturas(int id_tipofactura, String nombre_tipofactura) {
        this.id_tipofactura = id_tipofactura;
        this.nombre_tipofactura = nombre_tipofactura;
    }

    public int getId_tipofactura() {
        return id_tipofactura;
    }

    public void setId_tipofactura(int id_tipofactura) {
        this.id_tipofactura = id_tipofactura;
    }

    public String getNombre_tipofactura() {
        return nombre_tipofactura;
    }

    public void setNombre_tipofactura(String nombre_tipofactura) {
        this.nombre_tipofactura = nombre_tipofactura;
    }

   

    

    
}
