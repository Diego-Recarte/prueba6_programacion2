/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba6_programacion2;

/**
 *
 * @author denam
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class GUIarchivos extends JFrame{

    
    private JTextField Ruta;
    private JTextField Busqueda;
    private JTextArea Resumen;
    private JLabel error;
    private Timer tempo;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaResultados;

    private int contTxt;
    private int contJava;
    private int contPdf;
    private int contOtros;

    public GUIarchivos () {
        setTitle("Archivos");
        setSize(800, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.BLACK);
        InitComponentes();
        inicializarTimer();
    }
    public void inicializarTimer() {
        tempo = new Timer(2100, ev -> {
            error.setText("");
            repaint();
            tempo.stop();
        });
    }
        
        
    private void InitComponentes(){
            JPanel panelSuperior = new JPanel(new GridBagLayout());
            panelSuperior.setBackground(Color.black);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel lblRuta = new JLabel("Directorio raíz:");
            lblRuta.setForeground(Color.white);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            panelSuperior.add(lblRuta, gbc);

            Ruta = new JTextField();
            Ruta.setForeground(Color.black);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 1;
            panelSuperior.add(Ruta, gbc);


            JLabel lblBusqueda = new JLabel("Texto de búsqueda:");
            lblBusqueda.setForeground(Color.white);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panelSuperior.add(lblBusqueda, gbc);

            Busqueda = new JTextField();
            Busqueda.setForeground(Color.black);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1;
            panelSuperior.add(Busqueda, gbc);
            
              error = new JLabel("");
              error.setFont(new Font("BOLD", Font.BOLD, 9));
             error.setMaximumSize(new Dimension (150, 30));
             error.setMinimumSize(new Dimension (150, 30));
             error.setPreferredSize(new Dimension (150, 30));
             error.setBackground(Color.black);
             error.setForeground(Color.red);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 0;
            panelSuperior.add(error, gbc);

            JButton Analizar = new JButton("Analizar");
            Analizar.setForeground(Color.white);
            Analizar.setBackground(Color.black);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.weightx = 0;
            panelSuperior.add(Analizar, gbc);

            add(panelSuperior, BorderLayout.NORTH);

            JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));
            panelCentro.setBackground(Color.black);
            panelCentro.setBackground(Color.white);

            Resumen = new JTextArea();
            Resumen.setEditable(false);
            Resumen.setFont(new Font("BOLD", Font.BOLD, 14));
            JScrollPane scrollResumen = new JScrollPane(Resumen);
            scrollResumen.setBorder(BorderFactory.createTitledBorder("Resumen de conteo"));

            modeloLista = new DefaultListModel<>();
            listaResultados = new JList<>(modeloLista);
            JScrollPane scrollLista = new JScrollPane(listaResultados);
            scrollLista.setBorder(BorderFactory.createTitledBorder("Archivos encontrados"));

            panelCentro.add(scrollResumen);
            panelCentro.add(scrollLista);

            add(panelCentro, BorderLayout.CENTER);



            Analizar.addActionListener(e ->{

                    analizarDirectorio();
                            }
            );
    }
    
    
     private void analizarDirectorio() {
        String ruta = Ruta.getText().trim();
        String texto = Busqueda.getText().trim().toLowerCase();

        File directorioRaiz = new File(ruta);

        if (!directorioRaiz.exists()) {
            error.setText("No existe esa carpeta");
            tempo.start();
            return;
        }

        if (!directorioRaiz.isDirectory()) {
            error.setText("No es carpeta");
            tempo.start();
                    
            return;
        }

        contTxt = 0;
        contJava = 0;
        contPdf = 0;
        contOtros = 0;
        modeloLista.clear();
        Resumen.setText("");

        contarArchivosPorExtension(directorioRaiz);
        buscarArchivosPorNombre(directorioRaiz, texto);

        Resumen.append("Testos: " + contTxt + " archivos\n");
        Resumen.append("");
        Resumen.append("Java: " + contJava + " archivos\n");
        Resumen.append("");
        Resumen.append("PDF: " + contPdf + " archivos\n");
        Resumen.append("");
        Resumen.append("otro: " + contOtros + " archivos\n");

        if (modeloLista.isEmpty()) {
            modeloLista.addElement("No se encontraron archivos que coincidan con la busqueda");
        }
    }
     
    private void contarArchivosPorExtension(File directorio) {
        File[] archivo = directorio.listFiles();

        if (archivo == null) {
            return;
        }

        for (File a : archivo) {
            if (a.isDirectory()) {
                contarArchivosPorExtension(a);
            } else {
                String nombre = a.getName().toLowerCase();

                if (nombre.endsWith(".txt")) {
                    contTxt++;
                } else if (nombre.endsWith(".java")) {
                    contJava++;
                } else if (nombre.endsWith(".pdf")) {
                    contPdf++;
                } else {
                    contOtros++;
                }
            }
        }
    }
     
    private void buscarArchivosPorNombre(File directorio, String textoBusqueda) {
        File[] archivo = directorio.listFiles();

        if (archivo == null) {
            return;
        }

        for (File a : archivo) {
            if (a.isDirectory()) {
                buscarArchivosPorNombre(a, textoBusqueda);
            } else {
                String nombreArchivo = a.getName().toLowerCase();

                if (nombreArchivo.contains(textoBusqueda)) {
                    modeloLista.addElement(a.getPath());
                }
            }
        }
    }
    
    
    
      
      
      
      
      
      

    
     
     

    
}
