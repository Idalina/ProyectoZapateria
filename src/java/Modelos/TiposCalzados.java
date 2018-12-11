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
public class TiposCalzados {
    private int id_tipocalzado;
    private String nombre_tipocalzado;
    private CategoriaTipoCalzados categoria_tipocalzado;

    public TiposCalzados() {
    }

    public TiposCalzados(int id_tipocalzado, String nombre_tipocalzado, CategoriaTipoCalzados categoria_tipocalzado) {
        this.id_tipocalzado = id_tipocalzado;
        this.nombre_tipocalzado = nombre_tipocalzado;
        this.categoria_tipocalzado = categoria_tipocalzado;
    }

    public int getId_tipocalzado() {
        return id_tipocalzado;
    }

    public void setId_tipocalzado(int id_tipocalzado) {
        this.id_tipocalzado = id_tipocalzado;
    }

    public String getNombre_tipocalzado() {
        return nombre_tipocalzado;
    }

    public void setNombre_tipocalzado(String nombre_tipocalzado) {
        this.nombre_tipocalzado = nombre_tipocalzado;
    }

    public CategoriaTipoCalzados getCategoria_tipocalzado() {
        return categoria_tipocalzado;
    }

    public void setCategoria_tipocalzado(CategoriaTipoCalzados categoria_tipocalzado) {
        this.categoria_tipocalzado = categoria_tipocalzado;
    }
    
}
