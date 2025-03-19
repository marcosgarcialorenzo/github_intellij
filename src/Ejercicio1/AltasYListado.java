package Ejercicio1;
import java.io.*;

public class AltasYListado {

    Teclado t = new Teclado();
    final static String NOMBREFICHERO = "Productos.dat";

    public static void main(String[] args) throws IOException {
        AltasYListado cv = new AltasYListado();
        cv.menu();
    }

    private void menu() throws IOException {
        int opc = 0;
        do {
            System.out.println("1. Alta");
            System.out.println("2. Listar productos");
            System.out.println("3. Modificar");
            System.out.println("0. Salir");
            System.out.println("Dar opc");
            opc = t.leerInt();
            switch (opc) {
                case 1 -> alta();
                case 2 -> listarClientes();
                case 3 -> modificacion();
            }
        } while (opc != 0);
    }


    private void listarClientes() {
        int contProd = 0;
        int totPrecio = 0;
        try (RandomAccessFile f = new RandomAccessFile(NOMBREFICHERO, "r")) {
            Productos c = new Productos();
            boolean hayDatos = c.leer(f);
            while (hayDatos) {
                if (!c.borrado()) {
                    System.out.println(c);
                    contProd++;
                    totPrecio += c.precio;
                }
                hayDatos = c.leer(f);
            }
        } catch (IOException e) {
            System.out.println("Error lectura fichero");
        }
    }

    //Supongo que el fichero ya está abierto
    void irAPosicion(int idProd, RandomAccessFile f) throws IOException {
        f.seek(idProd * Productos.getTamanoRegistro());
    }

    //Supongo que el fichero ya está abierto
    boolean esValido(int idProd, RandomAccessFile f) throws IOException {
        return ((int) (f.length()) / Productos.getTamanoRegistro() > idProd);
    }



    private void alta() throws IOException {
        System.out.println("Dar producto");
        String nombre=t.leerString();
        try(RandomAccessFile f = new RandomAccessFile(NOMBREFICHERO, "rw")) {
            if (!buscarEnFichero(nombre, f)) {
                System.out.println("Precio");
                String precio = t.leerString();
                System.out.println("Cantidad");
                int cantidad = t.leerInt();
                Productos c = new Productos((int) (f.length() / Productos.getTamanoRegistro()), nombre, precio, cantidad);
                f.seek(f.length());
                c.escribir(f);
            }
        }catch (IOException e) {
            System.out.println("Error en el fichero");
        }
    }

    //supongo que el fichero está abierto
    private boolean buscarEnFichero(String nombre, RandomAccessFile f) throws IOException {
        boolean encontrado=false;
        Productos c= new Productos();
        File faux= new File (NOMBREFICHERO);
        if (faux.exists() ){
            boolean hayDatos = c.leer(f);
            while (hayDatos && !encontrado) {
                if (nombre.equalsIgnoreCase(c.nombre))
                    encontrado = true;
                else
                    hayDatos = c.leer(f);
            }
        }
        return encontrado;
    }

    private void modificacion() {
        try (RandomAccessFile f = new RandomAccessFile(NOMBREFICHERO, "rw")) {
            System.out.println("Dar id producto");
            int idProd = t.leerInt();
            if (esValido(idProd,f)) {
                Productos c = new Productos();
                irAPosicion(idProd, f);
                boolean hayDatos = c.leer(f);
                System.out.println(c);
                System.out.println("dar nueva cantidad");
                int edad = t.leerInt();
                c.cantidad = edad;
                irAPosicion(idProd, f);
                c.escribir(f);
            } else System.out.println("ID no válido");
        } catch( IOException e) {
            System.out.println("Error en el fichero");
        }
    }

    private void estadisticas() {
        int gastos[]= new int[11];
        for (int g:gastos)
            g=0;
        int contClientes = 0;
        int totGastos = 0;
        try (RandomAccessFile f = new RandomAccessFile(NOMBREFICHERO, "r")) {
            Productos c = new Productos();
            boolean hayDatos = c.leer(f);
            while (hayDatos) {
                if (!c.borrado()) {
                    gastos[(int)(c.cantidad/100)]++;
                }
                hayDatos = c.leer(f);
            }
            try(BufferedWriter bf= new BufferedWriter(new FileWriter("estadisticas.txt"))){
                for (int i=0; i<gastos.length;i++) {
                    String linea=i*100+":"+(i+1)*100+"->"+gastos[i];
                    bf.write(String.valueOf(linea));
                    bf.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error lectura fichero");
        }
    }
}