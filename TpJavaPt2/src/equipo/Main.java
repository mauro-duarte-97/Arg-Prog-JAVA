package equipo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

// CARGA DE fila Y CREACION DE OBJ: EQUIPO, PARTIDO, RONDA

        String ruta = "C:/Users/m_d_1/OneDrive/Escritorio/Programacion/Trabajos_Java/TpJava/TpJava/Resultados.csv";
        String ruta2 = "C:/Users/m_d_1/OneDrive/Escritorio/Programacion/Trabajos_Java/TpJava/TpJava/Pronostico.csv";
        LectorTexto lector = new LectorTexto();
        ArrayList<String> hojaPartido = lector.leer_csv(ruta);
        ArrayList<String> hojaPronostico = lector.leer_csv(ruta2);
        String[] listaParti;
        String[] listaProno;
        String nroRonda = "0";
        ArrayList<Partido> listaPartidos = new ArrayList<Partido>();
        ArrayList<Ronda> listaRondas = new ArrayList<Ronda>();
        ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
        Ronda objRonda = new Ronda(nroRonda, listaPartidos);
        GestionRondas objGesRonda = new GestionRondas(listaRondas);
        GestionParticipantes objGesParticipantes = new GestionParticipantes(listaParticipantes);
        

        if (hojaPartido == null) {
            System.out.println("El resultado estaba vacio");
            return;
        }
       
        for (String filaPartido : hojaPartido) {
            listaParti = filaPartido.split(",");

            if((listaParti.length > 5) || (listaParti.length < 4) 
                || ((Integer.parseInt(listaParti[2]) % 1 != 0) && (Integer.parseInt(listaParti[3]) % 1 != 0))){
                System.out.println("Hay un error con el CSV de resultados, corrijalo y carguelo de nuevo");
                return;
            }
//Si la ronda es >1 setea el nroRonda en la ronda actual y la agrego a la lista de rondas
            if (!listaParti[0].equals(nroRonda)) {
                nroRonda = listaParti[0];
                listaRondas.add(objRonda);
            }
            Equipo objEquipo1 = new Equipo(listaParti[1]);
            Equipo objEquipo2 = new Equipo(listaParti[4]);
            Partido nuevObjPartido = new Partido(objEquipo1, objEquipo2, Integer.parseInt(listaParti[2]),
            Integer.parseInt(listaParti[3]));
            listaPartidos.add(nuevObjPartido);
            objRonda.setNro(nroRonda);
        }    
        objRonda.setPartidos(listaPartidos);
        objGesRonda.setRondas(listaRondas);

//  PRONOSTICO

        Pronostico objPronostico = new Pronostico(null, null, null, null);
        ResultadoEnum resultadoPartido;
      
        if (hojaPronostico == null) {
            System.out.println("El pronostico estaba vacio");
            return;
        }

// RECORRE PRONO Y CARGA DE PARTICIPANTE Y CARGA LISTA PARTICIPANTES
// 1er FOR
        int cnt = 0;
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
            for (Ronda round : objGesRonda.getRonda()){

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

                        }System.out.println("\n" + "Resultado del Partido: " + resultadoPartido);
                         System.out.println("Resultado de Pronostico: " + objPronostico.getResultado());
                         
                        if (resultadoPartido == objPronostico.getResultado()) {
                            objParticipante.setPuntaje(1);
                            System.out.println("Sumas puntos, puntos de: " + objParticipante.getNombre() + " " + objParticipante.getPuntaje());
       
                        }else {

                            cnt = cnt + 1;
                            System.out.println("No sumas puntos, desaciertos de: " + objParticipante.getNombre() + " " + cnt + "\n");
                        }
                        break;
                    }
                }
            }
        }
        ArrayList<Participante> listaFinal = new ArrayList<Participante>();
        listaFinal = objGesParticipantes.puntosParticipante(listaParticipantes);
        objGesParticipantes.setParticipantes(listaFinal);
        for(Participante user : objGesParticipantes.getParticipantes()){
            System.out.println("La cantidad de pronosticos acertados de " + user.getNombre() + " fue " + user.getPuntaje() + " y sus puntos totales son: " + user.getPuntaje());
        }
        
    }   
}

