package Modelos;

public class Calces {

    private int id_calce;
    private String numero_calce;
    private CategoriaCalces categoriacalce;
    

    public Calces() {
    }

    public Calces(int id_calce, String numero_calce, CategoriaCalces categoriacalce) {
        this.id_calce = id_calce;
        this.numero_calce = numero_calce;
        this.categoriacalce = categoriacalce;
    }

    public int getId_calce() {
        return id_calce;
    }

    public void setId_calce(int id_calce) {
        this.id_calce = id_calce;
    }

    public String getNumero_calce() {
        return numero_calce;
    }

    public void setNumero_calce(String numero_calce) {
        this.numero_calce = numero_calce;
    }

    public CategoriaCalces getCategoriacalce() {
        return categoriacalce;
    }

    public void setCategoriacalce(CategoriaCalces categoriacalce) {
        this.categoriacalce = categoriacalce;
    }

    

   
}
