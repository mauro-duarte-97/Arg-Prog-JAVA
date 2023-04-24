package equipo;
import java.util.ArrayList;
public class GestionRondas {
    private ArrayList<Ronda> rondas;

    public GestionRondas(ArrayList<Ronda> rondasTot){
        this.rondas = rondasTot;
    }
    public ArrayList<Ronda> getRonda() {
        return this.rondas;
    }

    public void setRondas(ArrayList<Ronda> rondasTot) {
        this.rondas = rondasTot;
    }
}
