package Ejercicio1;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Productos {
    int codigo;   //-1 si el registro está borrado.
    String nombre;
    int cantidad;
    float precio;
    final static int TAMANONOMBRE=20;

    static int getTamanoRegistro(){
        return (4+TAMANONOMBRE*2*2 + 4+4);  // tama�o del array con el nombre *2 (por ser unicode) + tama�o de 1 int (4 bytes)
    }
    boolean borrado(){
        return codigo ==-1;
    }
    Productos(int idCod, String nomPro, String nomMasc, int e){
        codigo =idCod;
        nombre =nomPro;
        cantidad =e;
        precio =0;
    }
    Productos(){
        nombre ="NADA";
        cantidad =0;
    }

    char [] rellenarString (String nombre){
        char nombreB[]=new char[TAMANONOMBRE];
        for (int i=0; i<nombre.length() && i<TAMANONOMBRE; i++)
            nombreB[i]= nombre.charAt(i);
        for (int i=nombre.length(); i<TAMANONOMBRE ; i++)
            nombreB[i]=(char) 0;
        return nombreB;
    }

    void escribir(RandomAccessFile f) throws IOException {
        char nombreB[];
        f.writeInt(codigo);
        nombreB=rellenarString(nombre);
        f.writeChars(String.valueOf(nombreB));
        f.writeChars(String.valueOf(nombreB));
        f.writeInt(cantidad);
        f.writeFloat(precio);
    }
    
    boolean leer(RandomAccessFile f) throws IOException {
        //devuelve true si lee algo y false si no devuelve nada
        try {
            codigo =f.readInt();
            String nombAux = getString(f);
            nombre =nombAux;
            nombAux = getString(f);
            cantidad =f.readInt();
            precio =f.readFloat();
            return true;
        }catch (EOFException e) {
            return false;
        }
    }

    private static String getString(RandomAccessFile f) throws IOException {
        StringBuffer nombreB=new StringBuffer(TAMANONOMBRE);
        nombreB.setLength(TAMANONOMBRE);
        char car='a';
        int i=0,tamanoString=0;
        for (i=0; i<TAMANONOMBRE ;i++){
            car= f.readChar ();
            nombreB.setCharAt(i, car);
            if (car=='\0' && tamanoString==0)
                tamanoString= i;
        }
        nombreB.setLength(tamanoString);
        String nombAux=nombreB.toString();
        return nombAux;
    }

    @Override
    public String toString() {
        return "Producto{" +
           "idCod=" + codigo +
           ", producto='" + nombre + '\'' +
           ", cantidad=" + cantidad +
           ", precio=" + precio +
           '}';
    }

    void mostrar(){

        System.out.println ("nombre:"+ nombre +"  cantidad:"+ cantidad);
    }
    void mostrarArrayChar(char nombreB[]){
        for (int i=0;i<nombreB.length;i++)
            System.out.print(nombreB[i]);
    }
}
