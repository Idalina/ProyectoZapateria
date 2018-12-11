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
public class Clientes {

    private int id_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private int ci_cliente;
    private String ruc_cliente;
    private String telefono_cliente;
    private String direccion_cliente;
    private Nacionalidades nacionalidad;
    private Ciudades ciudad;
    private EstadosCiviles estadocivil;

    public Clientes() {
    }

    public Clientes(int id_cliente, String nombre_cliente, String apellido_cliente, int ci_cliente, String ruc_cliente, String telefono_cliente, String direccion_cliente, Nacionalidades nacionalidad, Ciudades ciudad, EstadosCiviles estadocivil) {
        this.id_cliente = id_cliente;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.ci_cliente = ci_cliente;
        this.ruc_cliente = ruc_cliente;
        this.telefono_cliente = telefono_cliente;
        this.direccion_cliente = direccion_cliente;
        this.nacionalidad = nacionalidad;
        this.ciudad = ciudad;
        this.estadocivil = estadocivil;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public int getCi_cliente() {
        return ci_cliente;
    }

    public void setCi_cliente(int ci_cliente) {
        this.ci_cliente = ci_cliente;
    }

    public String getRuc_cliente() {
        return ruc_cliente;
    }

    public void setRuc_cliente(String ruc_cliente) {
        this.ruc_cliente = ruc_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public Nacionalidades getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidades nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public EstadosCiviles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadosCiviles estadocivil) {
        this.estadocivil = estadocivil;
    }

    
}
