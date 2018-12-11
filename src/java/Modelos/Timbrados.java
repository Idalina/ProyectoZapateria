/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author alumno
 */
public class Timbrados {
    private int id_timbrado;
    private String numero_timbrado;
    private Date fecha_inicio_timbrado;
    private Date fecha_vencimiento_timbrado;
    private Date fecha_actual_timbrado;

    public Timbrados() {
    }

    public Timbrados(int id_timbrado, String numero_timbrado, Date fecha_inicio_timbrado, Date fecha_vencimiento_timbrado, Date fecha_actual_timbrado) {
        this.id_timbrado = id_timbrado;
        this.numero_timbrado = numero_timbrado;
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
        this.fecha_actual_timbrado = fecha_actual_timbrado;
    }

    public int getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(int id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public String getNumero_timbrado() {
        return numero_timbrado;
    }

    public void setNumero_timbrado(String numero_timbrado) {
        this.numero_timbrado = numero_timbrado;
    }

    public Date getFecha_inicio_timbrado() {
        return fecha_inicio_timbrado;
    }

    public void setFecha_inicio_timbrado(Date fecha_inicio_timbrado) {
        this.fecha_inicio_timbrado = fecha_inicio_timbrado;
    }

    public Date getFecha_vencimiento_timbrado() {
        return fecha_vencimiento_timbrado;
    }

    public void setFecha_vencimiento_timbrado(Date fecha_vencimiento_timbrado) {
        this.fecha_vencimiento_timbrado = fecha_vencimiento_timbrado;
    }

    public Date getFecha_actual_timbrado() {
        return fecha_actual_timbrado;
    }

    public void setFecha_actual_timbrado(Date fecha_actual_timbrado) {
        this.fecha_actual_timbrado = fecha_actual_timbrado;
    }
    
    
}
