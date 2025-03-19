/**
*   @author Jose Manuel Perez Lobato
*   @version 1.0
*/
import java.io.*;
class Teclado{
	 /**
	  * Lee un caracter y elimina del buffer de teclado todos los que estén en esa línea, incluido 
	  * el final de la línea.
	  * 
	  * @throws IOException
	  */
void leerFinLinea() throws IOException{
	char c=' ';
  while (c!='\n')
    c= (char) System.in.read();
}
/* Seria mejor utilizar siempre un BufferedReader y readLine() pero para que sirva como ejemplo uso el System.in.read() aunque hay que tener precaución con el salto de línea (leerFinLinea) por que si no se pone: si meten blancos despues del numero no se eliminan si se pone: en Unix y justo despues del numero dan return tendre que dar otra vez al return 
*/
/*int leerInt() throws IOException{
char c;
int numero=0;
  c= (char) System.in.read();
  while (c>='0' && c<='9'){
    numero=((int)c-(int)'0')+10*numero;
    c=(char) System.in.read();
  }
  leerFinLinea(c);
  return numero;
}*/
/*int leerInt() throws IOException {
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br= new BufferedReader(isr);
  String s=br.readLine();
  int i= Integer.parseInt(s);
  return (i);
}*/
/**
 * Lee un número entero de teclado y lo devuelve al invocador. Si lo leído no es un número entero repite la petición
 * hasta que se introduzca un número entero válido.
 *  Descarta el resto de la línea.
 * 
 * @throws IOException
 * @return int
 */
int leerInt() throws IOException {
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br= new BufferedReader(isr);
  String s;
  boolean fin= false;
  int i=0;
  do {
    s=br.readLine();
    try {
      i= Integer.parseInt(s);
      fin = true;
    } catch (NumberFormatException e) { }
  } while (!fin); 
  return (i);
}
/**
 * Lee un caracter de teclado y lo devuelve al invocador. Descarta el resto de la línea 
 * vaciando el buffer.
 * 
 * @throws IOException
 * @return char
 */
char leerChar () throws IOException {
char c;
  c=(char)System.in.read();
  //Si no se desea descartar el resto de la línea se debería eliminar la siguiente instrucción.
  leerFinLinea();
  return (c);
}
/**
 * Lee una línea de texto de teclado y la devuelve al invocador. 
 * 
 * @throws IOException
 * @return String
 */
String leerString() throws IOException {
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br= new BufferedReader(isr);
  String s=br.readLine();
  return (s);
}
/**
 * Lee un número double de teclado y lo devuelve al invocador. Si lo leído no es un número real se producirá
 * una excepción de tipo  java.lang.NumberFormatException
 * Descarta el resto de la línea.
 * 
 * @throws IOException
 * @throws  java.lang.NumberFormatException
 * @return double
 */
double leerDouble() throws IOException {
InputStreamReader isr=new InputStreamReader(System.in);
BufferedReader br= new BufferedReader(isr);
  String s=br.readLine();
  // La siguiente instrucción puede generar una excepción NumberFormatException.
  //Para evitarlo se puede hacer un tratamiento similar al realizado en leerInt
  double d= Double.valueOf(s).doubleValue();
  // tambien valdria double d= Double.parseDouble(s);
  return (d);
}

public static void main (String []args) throws IOException{

 Teclado t=new Teclado();

 System.out.println ("Dar char");
 char c=t.leerChar();
 System.out.println ("Char:"+c+":");
 System.out.println ("Dar int");
 int numero=t.leerInt();
 System.out.println ("Int:"+numero+":");
 System.out.println ("Dar double");
 double d =t.leerDouble();
 System.out.println ("Double:"+d+":");
 System.out.println ("Dar String");
 String s=t.leerString();
 System.out.println ("String:"+s+":");


}
}



