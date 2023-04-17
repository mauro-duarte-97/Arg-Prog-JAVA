package equipo;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String ruta = "Resultados.csv";
        String ruta2 = "Pronostico.csv";
        LectorTexto lector = new LectorTexto();
        ArrayList<String> filaPartido = lector.leer_csv(ruta);
        ArrayList<String> filaPronostico = lector.leer_csv(ruta2);
        String[] listaParti;
        String[] listaProno;
        ArrayList<Partido> listPartidos = new ArrayList<Partido>();
        Ronda objRonda = new Ronda("1", listPartidos);
        int puntos = 0;
        if (filaPartido != null) {
            for (String datosPartido : filaPartido) {
                listaParti = datosPartido.split(",");
                Equipo objEquipo1 = new Equipo(listaParti[0], "Equipo 1");
                Equipo objEquipo2 = new Equipo(listaParti[3], "Equipo 2");
                Partido nuevObjPartido = new Partido(objEquipo1, objEquipo2, Integer.parseInt(listaParti[1]),
                        Integer.parseInt(listaParti[2]));
                listPartidos.add(nuevObjPartido);
            }
            objRonda.setPartidos(listPartidos);
        }

        Pronostico objPronostico = new Pronostico(null, null, null);
        ResultadoEnum resultadoProno;

        if (filaPronostico != null) {

            for (String datosPronostico : filaPronostico) {
                listaProno = datosPronostico.split(",");
                String equipo1 = (listaProno[0]);
                String equipo2 = (listaProno[4]);

                if ((listaProno[1].equals("X")) || (listaProno[1].equals("x"))) {
                    resultadoProno = ResultadoEnum.GANADOR;
                } else if ((listaProno[3].equals("X")) || (listaProno[3].equals("x"))) {
                    resultadoProno = ResultadoEnum.PERDEDOR;
                } else {
                    resultadoProno = ResultadoEnum.EMPATE;
                }

                for (Partido rondaPartido : objRonda.getPartido()) {

                    if (equipo1.equals(rondaPartido.getEquipo1().getNombre())
                            && equipo2.equals(rondaPartido.getEquipo2().getNombre())) {

                        objPronostico.setPartido(rondaPartido);
                        objPronostico.setEquipo(rondaPartido.getEquipo1());

                        if (rondaPartido.getGolesEquipo1() > rondaPartido.getGolesEquipo2()) {
                            objPronostico.setResultado(ResultadoEnum.GANADOR);

                        } else if (rondaPartido.getGolesEquipo1() < rondaPartido.getGolesEquipo2()) {
                            objPronostico.setResultado(ResultadoEnum.PERDEDOR);
                        } else {
                            objPronostico.setResultado(ResultadoEnum.EMPATE);
                        }
                        if (resultadoProno == objPronostico.getResultado()) {
                            puntos = objPronostico.puntos(puntos);
                        }
                    }
                }

            }

        }
        objRonda.puntos(puntos);
    }
}
