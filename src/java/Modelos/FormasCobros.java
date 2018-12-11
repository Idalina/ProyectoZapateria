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
public class FormasCobros {
    private int id_formacobro;
    private String nombre_formacobro;

    public FormasCobros() {
    }

    public FormasCobros(int id_formacobro, String nombre_formacobro) {
        this.id_formacobro = id_formacobro;
        this.nombre_formacobro = nombre_formacobro;
    }

    public int getId_formacobro() {
        return id_formacobro;
    }

    public void setId_formacobro(int id_formacobro) {
        this.id_formacobro = id_formacobro;
    }

    public String getNombre_formacobro() {
        return nombre_formacobro;
    }

    public void setNombre_formacobro(String nombre_formacobro) {
        this.nombre_formacobro = nombre_formacobro;
    }
    

}
