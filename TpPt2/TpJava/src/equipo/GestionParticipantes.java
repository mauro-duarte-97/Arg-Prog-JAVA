package equipo;
import java.util.ArrayList;
public class GestionParticipantes {
    private ArrayList<Participante> participantes;

    public GestionParticipantes(ArrayList<Participante> participantesTot){
        this.participantes = participantesTot;
    }
    public ArrayList<Participante> getParticipantes() {
        return this.participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantesTot) {
        this.participantes = participantesTot;
    }
}

