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
    private JTextArea Busqueda;
    private JTextField txtBusqueda;
    private JTextArea Resumen;


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
        Ruta.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        panelSuperior.add(Ruta, gbc);


        JLabel lblBusqueda = new JLabel("Texto de búsqueda:");
        lblBusqueda.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelSuperior.add(lblBusqueda, gbc);

        txtBusqueda = new JTextField();
        txtBusqueda.setForeground(Color.white);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        panelSuperior.add(txtBusqueda, gbc);

        JButton Analizar = new JButton("Analizar");
        Analizar.setForeground(Color.white);
        Analizar.setBackground(Color.black);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelSuperior.add(Analizar, gbc);

        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 10, 10));
        panelCentro.setBackground(Color.white);

        Resumen = new JTextArea();
        Resumen.setEditable(false);
        Resumen.setFont(new Font("BOLD", Font.BOLD, 14));
        JScrollPane scrollResumen = new JScrollPane(Resumen);
        scrollResumen.setBorder(BorderFactory.createTitledBorder("Resumen de conteo"));
        
        Busqueda = new JTextArea();
        Busqueda.setEditable(false);
        Busqueda.setFont(new Font("BOLD", Font.BOLD, 14));
        JScrollPane scrollBusqueda = new JScrollPane(Busqueda);
        scrollResumen.setBorder(BorderFactory.createTitledBorder("Resumen de conteo"));

        

        panelCentro.add(scrollResumen);
        panelCentro.add(scrollBusqueda);

        add(panelCentro, BorderLayout.CENTER);

 

        Analizar.addActionListener(e ->{
                
               
                        }
        );
    }
}
