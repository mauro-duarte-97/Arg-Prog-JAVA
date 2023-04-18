package equipo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

// CARGA DE DATOS Y CREACION DE OBJ: EQUIPO, PARTIDO, RONDA

        String ruta = "Resultados.csv";
        String ruta2 = "Pronostico.csv";
        LectorTexto lector = new LectorTexto();
        ArrayList<String> filaPartido = lector.leer_csv(ruta);
        ArrayList<String> filaPronostico = lector.leer_csv(ruta2);
        String[] listaParti;
        String[] listaProno;
        ArrayList<Partido> listPartidos = new ArrayList<Partido>();
        ArrayList<Ronda> listRondas = new ArrayList<Ronda>();
        ArrayList<Participante> listParticipantes = new ArrayList<Participante>();
        Ronda objRonda = new Ronda(null, listPartidos);
        GestionRondas objGesRonda = new GestionRondas(listRondas);
        GestionParticipantes objGesParticipantes = new GestionParticipantes(listParticipantes);
        String nroRonda = "1";

        if (filaPartido == null) {
            System.out.println("El resultado estaba vacio");
            return;
        }
        
        for (String datosPartido : filaPartido) {
            listaParti = datosPartido.split(",");

            if((listaParti.length > 5) || (listaParti.length < 4) 
                || ((Integer.parseInt(listaParti[2]) % 1 != 0) && (Integer.parseInt(listaParti[3]) % 1 != 0))){
                System.out.println("Hay un error con el CSV de resultados, corrijalo y carguelo de nuevo");
                return;
            }
            if (!listaParti[0].equals(nroRonda)) {
                nroRonda = listaParti[0];
                listRondas.add(objRonda);
            }
            Equipo objEquipo1 = new Equipo(listaParti[1], "Equipo 1");
            Equipo objEquipo2 = new Equipo(listaParti[4], "Equipo 2");
            Partido nuevObjPartido = new Partido(objEquipo1, objEquipo2, Integer.parseInt(listaParti[2]),
                Integer.parseInt(listaParti[3]));
            listPartidos.add(nuevObjPartido);
            objRonda.setNro(nroRonda);
        }    
        
        objRonda.setPartidos(listPartidos);
        objGesRonda.setRondas(listRondas);
        System.out.println("HASTA LOS RESULTADOS TODO JOYA");

//  PRONOSTICO

        Pronostico objPronostico = new Pronostico(null, null, null, nroRonda);
        Participante objParticipante = new Participante(null);
        ResultadoEnum resultadoProno;
        Equipo eq1 = new Equipo(null, "Equipo 1");
        Equipo eq2 = new Equipo(null, "Equipo 2");; 
        int count = 0;
        if (filaPronostico == null) {
            System.out.println("El pronostico estaba vacio");
            return;
        }

// RECORRE PRONO Y CARGA DE PARTICIPANTE Y CARGA LISTA PARTICIPANTES

        for (String datosPronostico : filaPronostico) {
            listaProno = datosPronostico.split(",");
            eq1.setNombre(listaProno[1]);
            eq2.setNombre(listaProno[5]);
            objParticipante.setNombre(listaProno[0]);
            objPronostico.setParticipante(listaProno[0]);
            listParticipantes.add(objParticipante);

// SETEA LOS RESULTADOS DEL PRONOSTICO

            if ((listaProno[2].equals("X")) || (listaProno[2].equals("x"))) {
                objPronostico.setResultado(ResultadoEnum.GANADOR);
            } else if ((listaProno[4].equals("X")) || (listaProno[4].equals("x"))) {
                objPronostico.setResultado(ResultadoEnum.PERDEDOR);
            } else {
                objPronostico.setResultado(ResultadoEnum.EMPATE);
            }                   
            System.out.println("HASTA LA CARGA DE PRONOSTICOS TODO JOYA");

//RECORRIDO DE RONDAS PARA COMPARAR CON PRONOSTICOS  

            for (Ronda round : objGesRonda.getRonda()){
                System.out.println("AHI VA UNA RONDA");

//RECORRIDO DE PARTIDOS PARA COMPARAR CON PRONOSTICOS

                for (Partido rdPartido : round.getPartido()) {

//SETEO EL PARTIDO Y EL EQUIPO DEL PRONOSTICO LO GUARDAMOS Y COMPARAMOS

                    if (eq1.equals(rdPartido.getEquipo1())&& eq2.equals(rdPartido.getEquipo2())) {

                        objPronostico.setPartido(rdPartido);
                        objPronostico.setEquipo(rdPartido.getEquipo1());

                        if (rdPartido.getGolesEquipo1() > rdPartido.getGolesEquipo2()) {
                            resultadoProno = ResultadoEnum.GANADOR;

                        } else if (rdPartido.getGolesEquipo1() < rdPartido.getGolesEquipo2()) {
                            resultadoProno = ResultadoEnum.PERDEDOR;
                        } else {
                            resultadoProno = ResultadoEnum.EMPATE;
                        }
                        if (resultadoProno == objPronostico.getResultado()) {
                            objParticipante.sumarPuntos(1);
                        }
                    }
                }
            }
        

        String[] array = listParticipantes.toArray(new String[0]);
        ArrayList<Participante> arrayAux = new ArrayList<>(null);
        for(Participante user : listParticipantes){
            if ((count > 1) && (array[count]) != array[count - 1]) {
                arrayAux.add(listParticipantes.get(count -1));
                user.sumarPuntos(user.getPuntaje());
                count = count + 1;
                continue;
            }
            user.sumarPuntos(user.getPuntaje());
            count = count + 1;
        }
        objGesParticipantes.setParticipantes(arrayAux);
        for(Participante user : objGesParticipantes.getParticipantes()){
            System.out.println("Los puntos de acierto del pronostico de " + user.getNombre() + " fueron: " + user.getPuntaje());
        }
    }   
}
}
