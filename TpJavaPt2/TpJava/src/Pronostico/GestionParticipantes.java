package Pronostico;
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

    /**
     * @param participantes
     * @return
     */
    int puntaje1 = 0;
    int puntaje2 = 0;
    public ArrayList<Participante> puntosParticipante(ArrayList<Participante> participantes){
        ArrayList<String> listaUsuarios = new ArrayList<String>();
        ArrayList<Participante> listaFinal = new ArrayList<Participante>();

        for (Participante user : participantes) {
            if (user.getNombre().equals("Mariana")) {
                puntaje1 = user.getPuntaje() + puntaje1;
            }
            if (!listaUsuarios.contains(user.getNombre())){
                listaUsuarios.add(user.getNombre());
            }

            if (user.getNombre().equals("Pedro")) {
                puntaje2 = user.getPuntaje() + puntaje2;
            }
            if (!listaUsuarios.contains(user.getNombre())){
                listaUsuarios.add(user.getNombre());
            }
        }
        Participante objParticipante1 = new Participante("Mariana");
        objParticipante1.setPuntaje(puntaje1);
        Participante objParticipante2 = new Participante("Pedro");
        objParticipante2.setPuntaje(puntaje2);
        listaFinal.add(objParticipante1);
        listaFinal.add(objParticipante2);

        return listaFinal;
    }
}

