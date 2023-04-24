package Pronostico;
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
    public void cargarResultadosPartidos( ArrayList<String> hojaPartido){      
        String[] listaParti;
        String nroRonda = "0";
        Ronda objRonda = new Ronda(nroRonda, null);
        ArrayList<Ronda> listaRondas = new ArrayList<Ronda>();
        ArrayList<Partido> listaPartidos = new ArrayList<Partido>();
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
        this.rondas = listaRondas;
    }
}
