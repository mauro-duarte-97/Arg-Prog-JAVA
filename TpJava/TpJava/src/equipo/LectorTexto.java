package equipo;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class LectorTexto {

    public ArrayList<String> leer_csv(String ruta) { // METODO LEER CSV

        try {
            ArrayList<String> palabras_spliteadas = new ArrayList<String>();
            File partidos_file = new File(ruta);
            Scanner lector = new Scanner(partidos_file);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                palabras_spliteadas.add(linea);
            }
            lector.close();
            return palabras_spliteadas;
        } catch (FileNotFoundException e) {
            System.out.println("F");
            e.printStackTrace();
            return null;
        }
    }
}