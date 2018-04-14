/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Ariel
 */
public class VisorGrafoFrame extends JFrame{
    private String datos[];
    JScrollPane jsp;
    JPanel panelDibujo;
    //PanelDibujo panelDibujo;
    int max=0;
    JTextField valor;
    int tam;
    Sonido teclado= new Sonido("sonidos/teclado-ordenador.wav");
    Sonido musica=new Sonido("sonidos/zoe.wav");//musica2
    boolean parar=false;
    public Sonido getTeclado(){
        return teclado;
    }
    public Sonido getMusica(){
        return musica;
    }
    
    public VisorGrafoFrame(String datos[], int tamX, int tamY){
        setTitle("Vista en forma de Grafo");
        setIconImage(new ImageIcon("src/imagenes/libreta32.png").getImage());
        setSize(tamX, tamY);//700, 650
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.datos=datos;
        tam=datos.length;
        initComponents();
        /*importante para parar los sonidos cuando se cierre la ventana*/
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                musica.parar();
                teclado.parar();
                parar=true;
            }
        });
    }
    public boolean getParar(){return parar;}
    
    private void initComponents() {

        //t.start();
        jsp = new JScrollPane();
        
        panelDibujo = new PanelDibujo(tam, datos, jsp,this);

        panelDibujo.setPreferredSize(new java.awt.Dimension(2000, 2000));

        javax.swing.GroupLayout panelDibujoLayout = new GroupLayout(panelDibujo);
        panelDibujo.setLayout(panelDibujoLayout);
        panelDibujoLayout.setHorizontalGroup(
            panelDibujoLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
        );
        panelDibujoLayout.setVerticalGroup(
            panelDibujoLayout.createParallelGroup(Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
        );

        jsp.setViewportView(panelDibujo);
        
        
        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jsp, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(jsp, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

    }
    public void anularScrollBarr(){
        jsp.getHorizontalScrollBar().setEnabled(false);
        jsp.getVerticalScrollBar().setEnabled(false);
    }
    /*public static void main(String args[]){
        
        new VisorGrafoFrame().setVisible(true);
    }*/
}
