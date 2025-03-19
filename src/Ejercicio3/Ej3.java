package Ejercicio3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ej3 {

    public static String cambio1 (File fichero) {
        String texto = fichero.toString();
        String sust = texto.replace("<h3>", "<h4>");
        return texto;
    }
    public static String cambio2 (File fichero) {
        String texto2 = fichero.toString();
        String sust2 = texto2.replace("</h3>", "</h4>");
        return sust2;
    }

//    public String cambioH3 (File fichero) throws Exception {
//        int cont = 0;
//        if (fichero.exists()) {
//            try (BufferedReader testo = new BufferedReader(new FileReader(fichero))) {
//                String linea;
              Pattern patronPalabra = Pattern.compile("<h3>");
//                while ((linea = testo.readLine()) != null) {
//                    // Dividir la línea en palabras usando espacios en blanco y puntuación
//                    for(int i=0; i<fichero.length; i++) {
//                        String palabra = palabras[i];
//                        Matcher coincidencia = patronPalabra.matcher(palabra);
//                        if (coincidencia.matches()) {
//
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error al leer el fichero");
//            }
//        }else throw new Exception("El fichero no existe o no es un fichero válido.");
//        return String
//    }
}

