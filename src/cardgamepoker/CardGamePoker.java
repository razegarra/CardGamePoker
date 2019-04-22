/*
 * Módulo para realizar una gama de jugadas de poker desde un archivo txt
 */
package cardgamepoker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Richard Zegarra
 */
public class CardGamePoker {

    static int ganador1=0;
    static int ganador2=0;
    static int jugadas=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<String[]> plays = new ArrayList<String[]>();
        plays = leerArchivo();

        for (String[] a : plays) {
            jugar(a);
        }
    }

    public static void jugar(String[] args) {
        try {

            System.out.println("=================================");
            jugadas = jugadas + 1;
            System.out.println("Iniciando jugada Nº " + jugadas + " : " + Arrays.toString(args));

            List<Card> jugador1 = new ArrayList<>();
            List<Card> jugador2 = new ArrayList<>();
            jugador1.add(new Card(args[0]));
            jugador1.add(new Card(args[1]));
            jugador1.add(new Card(args[2]));
            jugador1.add(new Card(args[3]));
            jugador1.add(new Card(args[4]));
            jugador2.add(new Card(args[5]));
            jugador2.add(new Card(args[6]));
            jugador2.add(new Card(args[7]));
            jugador2.add(new Card(args[8]));
            jugador2.add(new Card(args[9]));

            GamePunctuation gp1 = new GamePunctuation(jugador1);
            GamePunctuation gp2 = new GamePunctuation(jugador2);

            if (gp1.puntaje > gp2.puntaje) {
                ganador1 = ganador1 + 1;
                System.out.println("Ganador jugador 1 !!!...");
            } else if (gp1.puntaje < gp2.puntaje) {
                ganador2 = ganador2 + 1;
                System.out.println("Ganador jugador 2 !!!...");
            }

            double g1 = (new Double(ganador1) / new Double(jugadas)) * 100;
            double g2 = (new Double(ganador2) / new Double(jugadas)) * 100;

            System.out.println("Estadistica: ");
            System.out.println("Jugador 1: gano " + ganador1 + " veces - Pocentaje:" + Math.round(g1) + "%");
            System.out.println("Jugador 2: gano " + ganador2 + " veces - Pocentaje:" + Math.round(g2) + "%");
            System.out.println("Empates: " + (jugadas - ganador1 - ganador2)+ " veces ");
            System.out.println("");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String[]> leerArchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        List<String[]> llenar = new ArrayList<>();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("pokerdata.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                llenar.add(linea.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return llenar;
    }

}
