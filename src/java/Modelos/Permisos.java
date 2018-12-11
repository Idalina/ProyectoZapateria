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
public class Permisos {
     private int id_permiso;
    private Roles rol;
    private Formularios formulario;

    public Permisos() {
    }

    public Permisos(int id_permiso, Roles rol, Formularios formulario) {
        this.id_permiso = id_permiso;
        this.rol = rol;
        this.formulario = formulario;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Formularios getFormulario() {
        return formulario;
    }

    public void setFormulario(Formularios formulario) {
        this.formulario = formulario;
    }
    
}
