/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.simulacionecosistema.InterfacesGraficas;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Usuario-Asus
 */
/**
 * Clase independiente para mostrar el desglose de nacimientos y muertes
 * tras un evento ambiental utilizando pestañas.
 */
public class VentanaReporteDetallado extends JFrame {

    /**
     * Constructor que recibe arreglos simples de String para evitar dependencias de List.
     * @param nombreEvento Nombre del desastre o evento ocurrido.
     * @param nacimientos Arreglo de Strings con los datos de crecimiento.
     * @param muertes Arreglo de Strings con los datos de decesos/extinciones.
     */
    public VentanaReporteDetallado(String nombreEvento, String[] nacimientos, String[] muertes) {
        // Configuración básica de la ventana
        setTitle("Reporte de Impacto: " + nombreEvento);
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout());

        // Panel de pestañas (Componente estándar de Swing)
        JTabbedPane pestañas = new JTabbedPane();

        // 1. Pestaña de Nacimientos
        JTextArea areaNac = crearAreaTextoPersonalizada(new Color(230, 255, 230));
        if (nacimientos == null || nacimientos.length == 0) {
            areaNac.setText("\n   No se registraron nacimientos en este evento.");
        } else {
            for (String registro : nacimientos) {
                if (registro != null) areaNac.append(" [✓] " + registro + "\n");
            }
        }
        pestañas.addTab("Nacimientos", new JScrollPane(areaNac));

        // 2. Pestaña de Muertes
        JTextArea areaMue = crearAreaTextoPersonalizada(new Color(255, 235, 235));
        areaMue.setForeground(new Color(180, 0, 0)); 
        if (muertes == null || muertes.length == 0) {
            areaMue.setText("\n   No se registraron bajas en este periodo.");
        } else {
            for (String registro : muertes) {
                if (registro != null) areaMue.append(" [✗] " + registro + "\n");
            }
        }
        pestañas.addTab("Muertes / Extinciones", new JScrollPane(areaMue));

        // Botón de cierre
        JButton btnCerrar = new JButton("Cerrar Reporte");
        btnCerrar.addActionListener(e -> this.dispose());
        
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(new JLabel("Resumen de Evento: " + nombreEvento));
        
        add(panelNorte, BorderLayout.NORTH);
        add(pestañas, BorderLayout.CENTER);
        add(btnCerrar, BorderLayout.SOUTH);
    }

    private JTextArea crearAreaTextoPersonalizada(Color fondo) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setBackground(fondo);
        area.setMargin(new Insets(15, 15, 15, 15));
        return area;
    }
}