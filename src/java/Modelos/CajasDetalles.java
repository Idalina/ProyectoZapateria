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
public class CajasDetalles {
    private int id_cobrodetalle;
    private int importe;
    private FacturasVentas facturaventa;
    private FormasCobros formacobro;
    private Cajas caja;

    public CajasDetalles() {
    }

    public CajasDetalles(int id_cobrodetalle, int importe, FacturasVentas facturaventa, FormasCobros formacobro, Cajas caja) {
        this.id_cobrodetalle = id_cobrodetalle;
        this.importe = importe;
        this.facturaventa = facturaventa;
        this.formacobro = formacobro;
        this.caja = caja;
    }

    public int getId_cobrodetalle() {
        return id_cobrodetalle;
    }

    public void setId_cobrodetalle(int id_cobrodetalle) {
        this.id_cobrodetalle = id_cobrodetalle;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public FacturasVentas getFacturaventa() {
        return facturaventa;
    }

    public void setFacturaventa(FacturasVentas facturaventa) {
        this.facturaventa = facturaventa;
    }

    public FormasCobros getFormacobro() {
        return formacobro;
    }

    public void setFormacobro(FormasCobros formacobro) {
        this.formacobro = formacobro;
    }

    public Cajas getCaja() {
        return caja;
    }

    public void setCaja(Cajas caja) {
        this.caja = caja;
    }

  
}
