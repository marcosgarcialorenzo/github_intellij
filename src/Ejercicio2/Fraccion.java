package Ejercicio2;

public class Fraccion {
    public int numerador;
    public int denominador;

    public Fraccion(int numerador, int denominador){
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public static void main (String[] args){
        Fraccion vf[] = new Fraccion[10];
        for (int i = 0; i <vf.length; ) {
            int numerador = (int)(Math.random()*10);
            int denominador = (int)(Math.random()*10);
            vf[i] = new Fraccion(numerador, denominador);
        }
    }
}
