package Vista;
import Modelo.Grafo2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class PanelDibujo extends JPanel {
    int x;
    int y;
    Grafo2 grafo;
    JScrollPane jsp;
    private VisorGrafoFrame visor;
    JButton anime; 
    JLabel expresar; 
    JTextArea frase;
    int diametro=40;//antes 30
    public Vector<Integer> xvs;
    public Vector<Integer> yvs;
    public String[] vgrafos;
    //public ArrayList<String> vgrafos;
    int indice=0;
    int tam; int controlador=0;
    Timer t;
    //Nota nota2= new Nota("Si desea ver el recorrido en profundidad del grafo, haga click sobre mi...\n=)           ",28);
    Nota nota2= new Nota("Buenos días Lic. Carmen Rosa Si desea ver el recorrido en profundidad del grafo, haga click sobre mi... =)      ",28);
    int c=0;
    Graphics graf;
    Color colorRecorrido=new Color(153,0,255);
    Sonido grito=new Sonido("sonidos/grito2.wav");
    
    public PanelDibujo(int tam,String[] datos, JScrollPane jsp, VisorGrafoFrame visor){
        this.jsp=jsp;
        this.visor=visor;
        grafo = new Grafo2(tam);//lo agregaste para tener el orden
        vgrafos=datos;
        grafo.crearEnlaces();//creo sus enlaces respectivos
        for(int i=0; i<=tam-1; i++){
             grafo.llenarDatos(i, "oso");//lleno los grafos con las palabras
        }
        //nota.separarTexto();//para darle saltos de linea
        xvs=new Vector<Integer>();
        yvs=new Vector<Integer>();
        //setDoubleBuffered(true);
        this.tam=tam;
        
        x=14*tam+180;
        if(tam>18)  y=14*tam+10;
        else        y=14*tam+110;
        anime = new JButton(new ImageIcon(getClass().getResource("/imagenes/drama2.gif")));
        anime.setBorder(null);
        anime.setBorderPainted(false);
        anime.setContentAreaFilled(false);
        anime.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anime.setBounds(15, 150, 145, 180);
        anime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visor.getTeclado().parar();
                grito.reproducir();
                if(controlador==0){
                t.stop();
                frase.setText("Que impaciente es usted, así\nque empezare cuanto antes\nobserve el grafo...");
                visor.getMusica().tocarContinuo();
                anime.setEnabled(false);
                new Hilo().start();
                if(tam>=24) frase.setText("No puedo mostrarle el recorrido\nporque no cabe en toda la\npantalla, mejor pregunte al\nque defiende esto... xD!");
                //
                }else if(controlador==1){
                    t.stop();
                    int valor=tam-2;
                    if(tam==1||tam==0)  valor=0;
                    else if(tam==2)     valor=1;
                    else if(tam==3)     valor=2;
                    visor.getTeclado().parar();
                    frase.setText("Recorrido terminado, como\npudo observar empecé en el\nnodo 0 y termine en el nodo\n"+valor+", me encanto mostrarle!");
                    //
                }
            }
        });
        add(anime);
        
        frase = new JTextArea("");
        frase.setBounds(38, 40, 195, 65);
        frase.setFont(new java.awt.Font("Tahoma", 4, 14));
        frase.setEditable(false);
        add(frase);
        
        expresar = new JLabel();
        expresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/expresar.png")));
        expresar.setBounds(5,0, 255, 170);
        //expresar.setVisible(false);
        add(expresar);
        t=new Timer(120, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pararT();
                frase.setText(nota2.getTexto()); 
                c++;
            }
        });
        t.start();
        visor.getTeclado().tocarContinuo();
        
    }
    public void pararT(){
        if(c==nota2.getTam()+20){
           t.stop();}//System.out.println("entro y c: "+c);}
    }
    
    public void paintComponent(Graphics grafico){
        super.paintComponents(grafico);
        Graphics2D g=(Graphics2D)grafico;
        
        /**/
        if(tam!=0){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            int radio = 14*tam;//antes 100
            float angulo = 360/tam;//antes 360/10
            angulo = (float) Math.toRadians(angulo);//System.out.println(angulo);
            for(int i=indice;i<tam;i++){
                int xv=(int)(Math.round(x+radio*Math.cos(i * angulo)));
                int yv=(int)(Math.round(y-radio*Math.sin(i * angulo)));
                xvs.add(xv);//
                yvs.add(yv);//
                indice++;
            }
        }
        /*para dibujar las aristas o lineas*/
        for(int i=0; i<tam;i++){
            //g.drawString("Los enlaces de los nodos tienen la siguiente forma",20,30);
            g.setStroke(new BasicStroke(2));
            g.drawLine(xvs.get(0)+diametro/2, yvs.get(0)+diametro/2, xvs.get(i)+diametro/2, yvs.get(i)+diametro/2);
            g.drawLine(xvs.get(tam-1)+diametro/2, yvs.get(tam-1)+diametro/2, xvs.get(i)+diametro/2, yvs.get(i)+diametro/2);
        }
        /*para dibujar los nodos con sus nombres*/
        pintarNodosyNom(g);
        /*para poner las etiquetas de inicio y fin*/
        g.setFont(new java.awt.Font("Tahoma", 1, 11));
        if(tam-1==0)
            g.drawString("INICIO y FIN", xvs.get(0)+diametro+4, yvs.get(0)+(diametro-4));
        else{
            g.drawString("INICIO", xvs.get(0)+diametro+4, yvs.get(0)+(diametro-4));
            g.drawString("FIN", xvs.get(tam-1)+diametro+4, yvs.get(tam-1)+(diametro-4));
        }
        //otras
        graf = getGraphics();
    }
    public void pintarNodosyNom(Graphics2D g){
        for(int j=0;j<tam;j++){
            g.setStroke(new BasicStroke(3));//cambia grosor de linea
            g.setColor(new Color(153,255,204)); 
            g.fillOval(xvs.get(j), yvs.get(j), diametro, diametro);//relleno
            g.setColor(new Color(0,153,153));
            g.drawOval(xvs.get(j),yvs.get(j), diametro, diametro);//contorno
            
            g.setFont(new java.awt.Font("Tahoma", 4, 10));//cambio tam de letra
            g.setColor(Color.WHITE);
            g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-5-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3)+1);
            g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-3-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3)-1);
            
            g.setColor(Color.BLACK);
            g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-4-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3));
            g.setColor(Color.BLUE);
            g.drawString(""+j, xvs.get(j)+(diametro/2)-5, yvs.get(j)+(diametro-4));
        }
    }
    public void pintarNodosAlFinal(Graphics2D g, int j){
        g.setStroke(new BasicStroke(3));//cambia grosor de linea
        g.setColor(new Color(255,204,255)); 
        g.fillOval(xvs.get(j), yvs.get(j), diametro, diametro);//relleno
        g.setColor(colorRecorrido);
        g.drawOval(xvs.get(j),yvs.get(j), diametro, diametro);//contorno
            
        g.setFont(new java.awt.Font("Tahoma", 4, 10));//cambio tam de letra
        g.setColor(Color.WHITE);
        g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-5-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3)+1);
        g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-3-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3)-1);
            
        g.setColor(Color.BLACK);
        g.drawString(""+vgrafos[j], xvs.get(j)+(diametro/2)-4-(vgrafos[j].length()*2), yvs.get(j)+((diametro/2)+3));
        g.setColor(Color.BLUE);
        g.drawString(""+j, xvs.get(j)+(diametro/2)-5, yvs.get(j)+(diametro-4));
    }
    long espera=1500;
    public class Hilo extends Thread{
        public void pintarRuta(){
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelDibujo.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<Integer> orden;
            Graphics2D g=(Graphics2D)graf;
            g.setStroke(new BasicStroke(3));
            g.setColor(colorRecorrido);
            if(tam==0||tam==1){
                g.drawOval(xvs.get(0),yvs.get(0), diametro, diametro);//caso simple
                controlador=1;
            }else if(tam>1&&tam<5){
                orden=grafo.buscarEnProfundidad(0, "1");
                for(int i=0; i<xvs.size()-1;i++){
                    g.setColor(colorRecorrido);
                    g.drawOval(xvs.get(orden.get(i)),yvs.get(orden.get(i)), diametro, diametro);
                    try {
                        Thread.sleep(espera);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelDibujo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g.drawLine(xvs.get(orden.get(i))+diametro/2, yvs.get(orden.get(i))+diametro/2, xvs.get(orden.get(i+1))+diametro/2, yvs.get(orden.get(i+1))+diametro/2);
                    pintarNodosAlFinal(g,orden.get(i));
                    
                }
                pintarNodosAlFinal(g,orden.get(tam-1));
                parar();
                controlador=1;
            /*no vuelvas a tocar esto de arriba*/    
            }else if(tam>=5&&tam<=23){
                orden=grafo.buscarEnProfundidad(0, "1");
                for(int i=0; i<3;i++){
                    g.setColor(colorRecorrido);
                    g.drawOval(xvs.get(orden.get(i)),yvs.get(orden.get(i)), diametro, diametro);
                    try {
                        Thread.sleep(espera);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelDibujo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g.drawLine(xvs.get(orden.get(i))+diametro/2, yvs.get(orden.get(i))+diametro/2, xvs.get(orden.get(i+1))+diametro/2, yvs.get(orden.get(i+1))+diametro/2);
                    pintarNodosAlFinal(g,orden.get(i));
                }
                //desde aqui te falta
                ArrayList<Integer> backtraking= new ArrayList<>();
                for(int k=3; k<tam;k++){
                    backtraking.add(orden.get(k));
                    backtraking.add(tam-1);
                }
                for(int j=0; j<backtraking.size()-2; j++){
                    g.setColor(colorRecorrido);
                    int num=backtraking.get(j);
                    int num2=backtraking.get(j+1);
                    g.drawOval(xvs.get(num),yvs.get(num), diametro, diametro);
                    try {
                        Thread.sleep(espera);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PanelDibujo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    g.drawLine(xvs.get(num)+diametro/2, yvs.get(num)+diametro/2, xvs.get(num2)+diametro/2, yvs.get(num2)+diametro/2);
                    pintarNodosAlFinal(g,num);
                }
                pintarNodosAlFinal(g,orden.get(tam-1));
                parar();
                controlador=1;
            }else{ 
                controlador=2;
                parar();
            }
        }
        public void run(){
            jsp.getHorizontalScrollBar().setEnabled(false);
            jsp.getVerticalScrollBar().setEnabled(false);
            visor.setResizable(false);
            pintarRuta();
            int valor=tam-2;
            if(tam==1||tam==0)  valor=0;
            else if(tam==2)     valor=1;
            else if(tam==3)     valor=2;
            //para animar el mensaje final
            if(controlador==1&&!visor.getParar()){
                nota2.setTexto("Recorrido terminado, como    pudo observar empecé en el  nodo 0 y termine en el nodo "+valor+", click me!...         ");
                t.restart();
                visor.getTeclado().tocarContinuo();
            }
            parar();
            visor.getMusica().parar();
            jsp.getHorizontalScrollBar().setEnabled(true);
            jsp.getVerticalScrollBar().setEnabled(true);//visor.setResizable(true);
            anime.setEnabled(true);
        }
        public void parar(){
            Thread.interrupted();
        }
    }
}
