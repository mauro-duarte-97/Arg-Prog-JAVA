package equipo;

import java.util.ArrayList;

public class Ronda {
    private String nro;
    private ArrayList<Partido> partidos;

    public Ronda(String nro, ArrayList<Partido> partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public String getNro() {
        return this.nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public ArrayList<Partido> getPartido() {
        return this.partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void puntos(int puntos) {
        System.out.println("La sumatoria de puntos del pronostico fue: " + puntos);
    }
}
