package Vista;
import Modelo.Grafo2;
import java.awt.Color;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;


/**
 *
 * @author
 */
public class Vista1 extends JFrame{
	
    JFileChooser seleccionado;
    File archivo;
    GestionA gestion;
    private int contador;
    private long tiempo;
    
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton btnVerTiempo;
    private javax.swing.JButton btnVerGrafo;
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel resultado;
    private javax.swing.JTextField textoAbuscar;
    
    Sonido sonido= new Sonido("sonidos/boton.wav");//boton
    
    public Vista1(){
        setLayout(null);
	setIconImage(new ImageIcon("src/imagenes/libreta32.png").getImage());
        seleccionado = new JFileChooser();
        gestion = new GestionA();
        //seleccionado.setIconImage(new ImageIcon("src/imagenes/libreta32.png").getImage());
        label = new JLabel("Búsqueda de palabras en texto");
        areaTexto = new javax.swing.JTextArea();
        //areaTexto.setBackground(new Color(230,245,255));
        //areaTexto.setForeground(Color.WHITE);
        btnCargar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        textoAbuscar = new javax.swing.JTextField();
        resultado = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnVerTiempo = new javax.swing.JButton();
        btnVerGrafo = new javax.swing.JButton();
        //seleccionado.setIconImage(new ImageIcon("src/imagenes/icono2.png").getImage());
        //areaTexto.setToolTipText("Escriba un texto o abra uno");
	textoAbuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textoAbuscar.setToolTipText("Palabra a buscar");
        label.setFont(new java.awt.Font("Times New Roman", 3, 26)); // NOI18N
        label.setBounds(200, 50, 330, 35);
        add(label);
        
        jScrollPane1= new JScrollPane(areaTexto);
        jScrollPane1.setBounds(120, 170, 530, 480);
        add(jScrollPane1);
        
        btnCargar.setIcon(new ImageIcon(getClass().getResource("/imagenes/open.png")));
        btnCargar.setToolTipText("Abrir");
        btnCargar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/openP.png")));
        btnCargar.setBorder(null);
        btnCargar.setBorderPainted(false);
        btnCargar.setContentAreaFilled(false);
        btnCargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCargar.setBounds(170, 90, 28, 28);
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                btnCargarActionPerformed(evt);
            }
        });
        add(btnCargar);
        
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new.png"))); // NOI18N
        btnEliminar.setToolTipText("Nuevo");
        btnEliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/newP.png")));
        btnEliminar.setBorder(null);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setBounds(240, 90, 28, 28);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar);
        
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/save.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveP.png")));
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setBounds(310, 90, 28, 28);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                btnGuardarActionPerformed(evt);
            }
        });
        add(btnGuardar);
        
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/help.png"))); // NOI18N
        btnVer.setToolTipText("Acerca de");
        btnVer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/helpP.png")));
        btnVer.setBorder(null);
        btnVer.setBorderPainted(false);
        btnVer.setContentAreaFilled(false);
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.setBounds(380, 90, 28, 28);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                Mensaje msn = new Mensaje(null, true);
                msn.ver();
            }
        });
        add(btnVer);
        
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N
        btnSalir.setToolTipText("Salir");
        btnSalir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelP.png")));
        btnSalir.setBorder(null);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setBounds(590, 90, 28, 28);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                int rpta = javax.swing.JOptionPane.showConfirmDialog(null, "Confirma salir?", "Dialogo de confirmación", 2);
                if (rpta == 0) 
                    System.exit(0);
            }
        });
        add(btnSalir);
        
        btnVerTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reloj.png"))); // NOI18N
        btnVerTiempo.setToolTipText("Ver tiempo");
        btnVerTiempo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/relojP.png")));
        btnVerTiempo.setBorder(null);
        btnVerTiempo.setBorderPainted(false);
        btnVerTiempo.setContentAreaFilled(false);
        btnVerTiempo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerTiempo.setBounds(450, 90, 28, 28);
        btnVerTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                if(textoAbuscar.getText().equals(""))
                    tiempo=0;
                double tSeg=tiempo*0.000000001;
                JOptionPane.showMessageDialog(null, "El tiempo para la ultima búsqueda fue de:\n"+tiempo+" nano segundos\n"
                        + "que equivalen a "+tSeg+" segundos\n1s   = 1 000 ms\n1s   = 1 000 000 000 ns\n1ms= 1 000 000 ns");
            }
        });
        add(btnVerTiempo);
        /**/
        btnVerGrafo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grafo.png"))); // NOI18N
        btnVerGrafo.setToolTipText("Ver grafo");
        btnVerGrafo.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/grafoP.png")));
        btnVerGrafo.setBorder(null);
        btnVerGrafo.setBorderPainted(false);
        btnVerGrafo.setContentAreaFilled(false);
        btnVerGrafo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerGrafo.setBounds(520, 90, 28, 28);
        btnVerGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                String p=areaTexto.getText().replace("\n"," ");//remplazo los saltos d linea por espacios
                String[] separados= p.split(" ");//quito los espacios y los convierto en un arreglo
                if(separados.length<46){
                    int tamX=700, tamY=650;
                    if(separados.length<9){tamX=550;tamY=450;}
                    VisorGrafoFrame visor= new VisorGrafoFrame(separados,tamX,tamY);//creo la vista de los grafos
                    visor.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Para poder ver el grafo la cantidad de palabras\n no debe ser mayor a 45");
                }
            }
        });
        add(btnVerGrafo);
        /**/
        textoAbuscar.setBounds(120, 132, 110, 32);
        add(textoAbuscar);
        
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setToolTipText("Bucar");
        btnBuscar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarP.png")));
        btnBuscar.setBorder(null);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        //btnBuscar.setFocusable(false);
        //btnBuscar.setHorizontalTextPosition(0);
        //btnBuscar.setVerticalTextPosition(3);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setBounds(240, 134, 28, 28);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sonido.reproducir();
                btnBuscarActionPerformed(evt);
            }
        });
        add(btnBuscar);
        
        resultado.setFont(new java.awt.Font("Tahoma", 1, 12));
        resultado.setBounds(300, 132, 370, 32);
        add(resultado);
        
        //hasta aqui
        JLabel labelFondo;
        labelFondo = new JLabel(new ImageIcon("src/imagenes/nota2.png"));
        labelFondo.setBounds(0,0,710,700);
        add(labelFondo);
        setUndecorated(true);
        setVisible(true);
        setBounds(0,0,710,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
        try {//para quitar el frame
             Class clazz =  Class.forName("com.sun.awt.AWTUtilities");
             Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
             method.invoke(clazz,this , false);
        } catch (Exception e) {
             System.out.println("error5"+e.getMessage());
             e.printStackTrace();
        }
    }
    /**/
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        int rpta = javax.swing.JOptionPane.showConfirmDialog(null, "Desea eliminar todo?", "Dialogo de confirmación", 2);
            if (rpta == 0){
                areaTexto.setText("");
                textoAbuscar.setText("");
                resultado.setText("");}
    }                                           

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        long time_start, time_end;
        tiempo=0;
        time_start = System.nanoTime();//inicio
        String p=areaTexto.getText().replace("\n"," ");//remplazo los saltos d linea por espacios
        String[] separados= p.split(" ");//quito los espacios y los convierto en un arreglo
        Grafo2 grafo = new Grafo2(separados.length);//creo el grafo
        grafo.crearEnlaces();//creo sus enlaces respectivos
        for(int i=0; i<=separados.length-1; i++){
             grafo.llenarDatos(i, separados[i]);//lleno los grafos con las palabras
        }
        grafo.buscarEnProfundidad(0, textoAbuscar.getText());//aplico busqueda en profundidad, si o si tiene q ser el otro parametro
        // TODO add your handling code here:
        contador=grafo.getOcurrencias();//recupero la cantidad de veces q aparece la palabra
        pintarPalabra(areaTexto, textoAbuscar.getText());//coloreo las palabras con el metodo
        mostrar(textoAbuscar.getText(),contador);//finalmente muestro el resultado con otro metodo
        time_end = System.nanoTime();//fin
        tiempo=time_end - time_start;
    }                                         

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        if(seleccionado.showDialog(this, "ABRIR TEXTO") == JFileChooser.APPROVE_OPTION){
            setIconImage(new ImageIcon("src/imagenes/libreta32.png").getImage());
            archivo = seleccionado.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("txt")){
                    String contenido = gestion.AbrirATexto(archivo);
                    areaTexto.setText(contenido);
                }else
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo de texto");   
            }
        }
    }                                         

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        if(seleccionado.showDialog(this, "GUARDAR TEXTO") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("txt")){
                String contenido = areaTexto.getText();
                String respuesta = gestion.GuardarATexto(archivo, contenido);
                if(respuesta!=null){
                    JOptionPane.showMessageDialog(null, respuesta);
                }else{
                    JOptionPane.showMessageDialog(null, "Error al guardar texto");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El texto se debe guardar en un formato de .txt");
            }
        }
    }                                          
//para colorear
    public void pintarPalabra(JTextArea area1, String texto) {
        if(area1==null)return;
        try{
        if (texto.length() >= 1 && area1.getText().length()>=1) {
            DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            Highlighter h = area1.getHighlighter();
            h.removeAllHighlights();
            String text = area1.getText();
            String caracteres = texto;
            Pattern p = Pattern.compile("(?i)" + caracteres);
            Matcher m = p.matcher(text);
            while (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), highlightPainter);
                    //contador++;
                } catch (BadLocationException ex) {
                    //Logger.getLogger(Color.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(area1, "La palabra a buscar no puede ser vacia \n y mucho menos el texto");
            resultado.setText("");
        }
        }catch (Exception e) {}
    }
    private void mostrar(String pal, int veces){
        if(!pal.equals("")){
        if(veces==0&&pal.length()>=1)
            resultado.setText("La palabra ''"+pal+"'' no existe en el texto");
        else if(veces==1)
            resultado.setText("La palabra ''"+pal+"'' se repite una vez");
        else if(veces>1)
            resultado.setText("La palabra ''"+pal+"'' se repite "+veces+" veces");}
    }

    public static void main(String[] args) {
	Vista1 main= new Vista1();

    }
    
}