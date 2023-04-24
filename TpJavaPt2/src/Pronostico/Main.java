package Pronostico;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String rutaResultados = "";
        String rutaPronostico = "";
        if (args.length > 0) {
            rutaResultados = args[0];
            rutaPronostico = args[1];
          } else {
            System.out.println("Maneja el caso en que no se pasaron argumentos");
          }
//Lectura de csv y carga de datos en ArrayList          

        LectorTexto lector = new LectorTexto();
        ArrayList<String> hojaPartido = lector.leer_csv(rutaResultados);
        ArrayList<String> hojaPronostico = lector.leer_csv(rutaPronostico); 

//Creacion de objetos y muestra de resultados        

        ArrayList<Participante> listaParticipantes = new ArrayList<Participante>();
        GestionRondas objGesRondas = new GestionRondas(null);
        GestionParticipantes objGesParticipantes = new GestionParticipantes(listaParticipantes);
        objGesRondas.cargarResultadosPartidos(hojaPartido);
        objGesParticipantes.cargaParticipantes(hojaPronostico, objGesRondas, listaParticipantes);  
        objGesParticipantes.puntosParticipante();
        for(Participante user : objGesParticipantes.getParticipantes()){
            System.out.println("La cantidad de pronosticos acertados de " + user.getNombre() + " fue " + user.getPuntaje() + " y sus puntos totales son: " + user.getPuntaje());
        }
    }   
}

