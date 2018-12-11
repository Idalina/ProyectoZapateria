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
public class CategoriaTipoCalzados {
    private int id_categoriatipocalzado;
    private String nombre_categoriatipocalzado;

    public CategoriaTipoCalzados() {
    }

    public CategoriaTipoCalzados(int id_categoriatipocalzado, String nombre_categoriatipocalzado) {
        this.id_categoriatipocalzado = id_categoriatipocalzado;
        this.nombre_categoriatipocalzado = nombre_categoriatipocalzado;
    }

    public int getId_categoriatipocalzado() {
        return id_categoriatipocalzado;
    }

    public void setId_categoriatipocalzado(int id_categoriatipocalzado) {
        this.id_categoriatipocalzado = id_categoriatipocalzado;
    }

    public String getNombre_categoriatipocalzado() {
        return nombre_categoriatipocalzado;
    }

    public void setNombre_categoriatipocalzado(String nombre_categoriatipocalzado) {
        this.nombre_categoriatipocalzado = nombre_categoriatipocalzado;
    }

    
}
