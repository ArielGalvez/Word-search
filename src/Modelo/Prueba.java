/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.util.ArrayList;

public class Prueba {
    //Para probar los métodos se ejecuta el siguiente código:
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grafo2 g=new Grafo2(11);
        g.crearEnlaces();
        //g.llenarDatosTemporal();
        g.llenarDatos(0, "pepe");
        g.llenarDatos(1, "pepe");
        g.llenarDatos(2, "pepe");
        g.llenarDatos(3, "Pepe");
        g.llenarDatos(4, "poco");
        g.llenarDatos(5, "poco");
        g.llenarDatos(6, "poco");
        g.llenarDatos(7, "poco");
        g.llenarDatos(8, "poco");
        g.llenarDatos(9, "poco");
        g.llenarDatos(10, "poco");
        //g.imprimirGrafo();
        ArrayList<Integer> en=g.buscarEnProfundidad(0,"pe");//NodoI simpre tiene q ser 0.. porq si no genera errores
        System.out.println("aparece: "+g.getOcurrencias());
        System.out.println("Recorrido en profundidad de un grafo representado como matriz: ");
        g.imprimirArray(en);
    }

}


