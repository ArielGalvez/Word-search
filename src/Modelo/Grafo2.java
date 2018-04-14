package Modelo;
import java.util.ArrayList;
//import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 * Clase Grafo
 * para aclarar
 *  0 no se puede visitar
 *  n>0 existe enlace
 *   
 * @author Juan Sebastian
 */
public class Grafo2 {
    private int nodos;
	//private Random semilla = new Random();//esto es la misma moneda
	private int ocurrencias=0;
		
    private String[] datos; 
    private int [][] g;     
    private boolean[] visitiadoProfundidad;
    
    public Grafo2(int nodos) {
        this.nodos=nodos;
    	visitiadoProfundidad = new boolean[nodos];
    	datos = new String[nodos];
        g = new int [nodos][nodos];
        for(int i=0; i<nodos; i++){
        	g[i][i]=2;
        }
    }
    public void crearEnlaces(){
    	if(g.length>0){
    		for(int i=1; i<nodos; i++){
    			g[i][0]=1;
    			g[0][i]=1;
    		}
    		for(int j=1;j<nodos-1;j++){
    			g[j][nodos-1]=1;
    			g[nodos-1][j]=1;
    		}
    	}
    }
    public void llenarDatos(int pos, String valor){
    	if(datos[pos]==null&&pos>-1)
    		datos[pos]=valor;
    	else
    		System.out.println("El grafo con esa posicion ya tiene nombre");
    }
    public void llenarDatosTemporal(){
    	for(int i=0; i<nodos;i++){
    		datos[i]="var"+i;
    	}
    }
    
    public ArrayList<Integer> buscarEnProfundidad(int nodoI,String palabra) {
    	//agregaste esto porq no entraba al primero
    	if(nodoI==0)
    		buscarPalabras(datos[nodoI], palabra);
    	//Lista donde guardo los nodos recorridos
        ArrayList<Integer> recorridos = new ArrayList<Integer>();
        visitiadoProfundidad[nodoI] = true;//El nodo inicial ya está visitado
        //Cola de visitas de los nodos adyacentes
        ArrayList<Integer> cola = new ArrayList<Integer>();
        recorridos.add(nodoI);//Listo el nodo como ya recorrido
        cola.add(nodoI);//Se agrega el nodo a la cola de visitas
        while (!cola.isEmpty()) {//Hasta que visite todos los nodos
            int j = cola.remove(0);//Se saca el primer nodo de la cola
            //Se busca en la matriz que representa el grafo los nodos adyacentes
            for (int i = 0; i < g.length; i++) {
            	//Si es un nodo adyacente y no está visitado entonces
                if (g[j][i]>0 && !visitiadoProfundidad[i]) {//			aqui
                    cola.add(i);//Se agrega a la cola de visita
                    /**/
                    buscarPalabras(datos[i], palabra);
                    //Se recorren los hijos del nodo actual de visita y se agrega el recorrido al la lista
                    recorridos.addAll(buscarEnProfundidad(i, palabra));
                    visitiadoProfundidad[i] = true;//Se marca como visitado
                }
            }
        }
        return recorridos;//Se devuelve el recorrido del grafo en profundidad
    }
    public int getOcurrencias(){
    	return ocurrencias;
    }
    public void resetOcurrencias(){
    	ocurrencias=0;
    }
    public void buscarPalabras(String text, String palabra){
    	//String text = datos[i];
        try{
            Pattern p = Pattern.compile("(?i)" + palabra);
            Matcher m = p.matcher(text);
            while (m.find()) {
        	ocurrencias++;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No es posible buscar caracteres especiales");
        }
    }
    public void imprimirGrafo(){
    	int c=0;
    	System.out.print("_M|");
    	for(int k=0;k<g.length;k++){
    		System.out.print("_"+k+"|");
    	}
    	System.out.println("");
    	while(c<g.length){
    		System.out.print("_"+c+"| ");
    		for(int i = 0;i < g.length;i++){
    			System.out.print(g[i][c]+", ");
    		}
    		System.out.println("");
    		c++;
    	}	
    }
    public void imprimirArray(ArrayList<Integer> a){
    	for(int i=0;i<a.size();i++){
    		System.out.print(a.get(i)+" ");
    	}
    }
    
}