package model;

public class Actor {
    private int id;
    private String nombre;

    public Actor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
