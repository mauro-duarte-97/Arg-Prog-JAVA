package equipo;

public class Participante {
    String nombre;
    int puntaje;

    public Participante(String nombre){
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje = puntaje + puntos;
    }
}
