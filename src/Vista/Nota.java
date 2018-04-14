/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


/**
 *
 * @author Ariel
 */
public class Nota {
    private int contador;int limite; int multi;
    private String texto;
    private String textoAux;
    private int tam=0;
    public Nota(String texto, int limite){
        contador=1;
        this.limite=limite;
        multi=2;
        this.texto=texto;
        separarTexto();
    }
    public void setTexto(String texto){
        contador=1;
        this.limite=limite;
        multi=2;
        this.texto=texto;
        separarTexto();
    }
    public void separarTexto(){
        String aux=""; int limAux=limite;
        for(int i=0; i<texto.length();i++){
            aux=aux+texto.charAt(i);
            if(i==limAux){
               aux=aux+"\n";
               limAux=limite*multi;
               multi++;
            }
        }
        texto=aux;
        tam=aux.length();
    }
    public String getTexto(){
        if(contador==tam) contador=1;
        textoAux=texto.substring(0, contador);
        contador++;
        //if(contador==tam) contador=1;
        return textoAux;
    }
    public int getTam(){
        return tam;
    }
    public static void main(String[] args){
        String t="Pablito clavo un clavito pero nada mas que eso, solo por joder seguire haber que pasa";
        Nota not= new Nota(t,28);
        //not.separarTexto();
        for(int i=0; i<t.length();i++){
            System.out.println(not.getTexto());
            System.out.println("=================");
        }
    }
}
