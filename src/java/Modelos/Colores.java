
package Modelos;

public class Colores {
    
    private int id_color;
    private String nombre_color;

    public Colores() {
    }

    public Colores(int id_color, String nombre_color) {
        this.id_color = id_color;
        this.nombre_color = nombre_color;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }
    
    
}
