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
    public void cargaParticipantes(ArrayList<String> hojaPronostico, GestionRondas objGesRondas, ArrayList<Participante> listaParticipantes){
        String[] listaProno;
        Pronostico objPronostico = new Pronostico(null, null, null, null);
        ResultadoEnum resultadoPartido;
        if (hojaPronostico == null) {
            System.out.println("El pronostico estaba vacio");
            return;
        }

// RECORRE PRONO Y CARGA DE PARTICIPANTE Y CARGA LISTA PARTICIPANTES
// 1er FOR

        for (String filaPronostico : hojaPronostico) {
            listaProno = filaPronostico.split(",");
            Equipo eq1 = new Equipo(null);
            Equipo eq2 = new Equipo(null ); 
            eq1.setNombre(listaProno[1]);
            eq2.setNombre(listaProno[5]);
            
            Participante objParticipante = new Participante(listaProno[0]);
            objPronostico.setParticipante(listaProno[0]);
            listaParticipantes.add(objParticipante);

// SETEA LOS RESULTADOS DEL PRONOSTICO

            if ((listaProno[2].equals("X"))){
                objPronostico.setResultado(ResultadoEnum.GANADOR);
            } else if ((listaProno[4].equals("X"))){
                objPronostico.setResultado(ResultadoEnum.PERDEDOR);
            } else {
                objPronostico.setResultado(ResultadoEnum.EMPATE);
            }
//RECORRIDO DE RONDAS PARA COMPARAR CON PRONOSTICOS     
// 2do FOR            
            for (Ronda round : objGesRondas.getRonda()){

//RECORRIDO DE PARTIDOS PARA COMPARAR CON PRONOSTICOS
// 3ro FOR      
        
                for (Partido rdPartido : round.getPartido()) {
//SETEO EL PARTIDO Y EL EQUIPO DEL PRONOSTICO LO GUARDAMOS Y COMPARAMOS
                    if (eq1.getNombre().equals(rdPartido.getEquipo1().getNombre())&& eq2.getNombre().equals(rdPartido.getEquipo2().getNombre())) {

                        objPronostico.setPartido(rdPartido);
                        objPronostico.setEquipo(rdPartido.getEquipo1()); 
                        if (rdPartido.getGolesEquipo1() > rdPartido.getGolesEquipo2()) {
                            resultadoPartido = ResultadoEnum.GANADOR;

                        } else if (rdPartido.getGolesEquipo1() < rdPartido.getGolesEquipo2()) {
                            resultadoPartido = ResultadoEnum.PERDEDOR;

                        } else {
                            resultadoPartido = ResultadoEnum.EMPATE;

                        }

                        if (resultadoPartido == objPronostico.getResultado()) {
                            objParticipante.setPuntaje(1);
                            System.out.println("Sumas puntos, puntos de: " + objParticipante.getNombre() + " " + objParticipante.getPuntaje());
       
                        }else {
                            System.out.println("No sumas puntos, fallo: " + objParticipante.getNombre() + "\n");
                        }
                        break;
                    }
                }
            }
        }
        this.participantes = listaParticipantes;
    }






    /**
     * @param participantes
     * @return
     */
    int puntaje1 = 0;
    int puntaje2 = 0;
    public void puntosParticipante(){
        ArrayList<String> listaUsuarios = new ArrayList<String>();
        ArrayList<Participante> listaFinal = new ArrayList<Participante>();

        for (Participante user : this.participantes) {
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
        this.participantes = listaFinal;
        
    }
}

