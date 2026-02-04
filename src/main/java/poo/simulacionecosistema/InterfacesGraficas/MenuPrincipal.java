package poo.simulacionecosistema.InterfacesGraficas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import poo.simulacionecosistema.*;

/**
 * CLASE PRINCIPAL DE INTERFAZ (GUI)
 * Esta clase actúa como el "Mediador" entre el usuario y las estructuras de datos.
 * Coordina la visualización en tablas, el historial en la pila y los eventos ambientales.
 */
public class MenuPrincipal extends JFrame {

    // --- ATRIBUTOS DE LÓGICA (Estructuras Manuales) ---
    private ListaEspecies listaEspecies; // Repositorio dinámico de seres vivos (Lista Simple)
    private PilaHistorial historial;    // Registro cronológico LIFO de sucesos (Pila)
    private ArbolEscenarios arbol;      // Jerarquía de impactos ambientales (BST)
    private EventosAmbientales motorEventos; // Motor de lógica para cambios poblacionales

    // --- COMPONENTES VISUALES ---
    private JTable tablaEspecies;
    private DefaultTableModel modeloTabla;
    private JTextArea areaHistorial;
    private JTextField txtNombreEspecie, txtPoblacionInicial;
    private JComboBox<TipoEspecie> comboTipo;
    private JComboBox<TipoEvento> comboEvento;
    private JSlider sliderIntensidad;

    public MenuPrincipal() {
        // Inicialización de componentes de lógica
        listaEspecies = new ListaEspecies(); 
        historial = new PilaHistorial();
        arbol = new ArbolEscenarios();
        motorEventos = new EventosAmbientales();

        // Configuración básica de la ventana (JFrame)
        setTitle("ECOSYSTEM - Simulador de Equilibrio Ambiental");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // CONSTRUCCIÓN DE LA INTERFAZ: Dividida en paneles Norte, Centro y Sur
        inicializarPanelRegistro();
        inicializarPanelCentral();
        inicializarPanelInferior();

        actualizarInterfaz();
    }

    /**
     * PANEL NORTE: Registro de datos.
     * Permite la entrada de nuevas entidades al sistema.
     */
    private void inicializarPanelRegistro() {
        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRegistro.setBorder(BorderFactory.createTitledBorder("Registro de Nueva Especie"));
        
        txtNombreEspecie = new JTextField(10);
        txtPoblacionInicial = new JTextField(5);
        comboTipo = new JComboBox<>(TipoEspecie.values());
        JButton btnAgregar = new JButton("Registrar en Lista");

        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(txtNombreEspecie);
        panelRegistro.add(new JLabel("Población:"));
        panelRegistro.add(txtPoblacionInicial);
        panelRegistro.add(new JLabel("Tipo:"));
        panelRegistro.add(comboTipo);
        panelRegistro.add(btnAgregar);

        btnAgregar.addActionListener(this::registrarEspecie);
        add(panelRegistro, BorderLayout.NORTH);
    }

    /**
     * PANEL CENTRAL: Visualización y Herramientas.
     * Contiene la tabla de datos y botones para acceder a las estructuras de análisis (Cola y Árbol).
     */
    private void inicializarPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));
        
        String[] columnas = {"Especie", "Población", "Tipo", "Riesgo Base", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaEspecies = new JTable(modeloTabla);
        panelCentral.add(new JScrollPane(tablaEspecies), BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new GridLayout(5, 1, 10, 10));
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnColaPrioridad = new JButton("Ver Prioridades (Cola)");
        JButton btnAnalizarBST = new JButton("Analizar Escenarios (Arbol)");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");

        btnColaPrioridad.addActionListener(this::mostrarPrioridades);
        btnAnalizarBST.addActionListener(this::mostrarEscenarios);
        btnEliminar.addActionListener(this::eliminarEspecie);

        panelAcciones.add(btnColaPrioridad);
        panelAcciones.add(btnAnalizarBST);
        panelAcciones.add(new JLabel("---", SwingConstants.CENTER));
        panelAcciones.add(btnEliminar);
        
        panelCentral.add(panelAcciones, BorderLayout.EAST);
        add(panelCentral, BorderLayout.CENTER);
    }

    /**
     * PANEL INFERIOR: Eventos y Bitácora.
     * Muestra el generador de desastres y el historial gestionado por la Pila.
     */
    private void inicializarPanelInferior() {
        JPanel panelInferior = new JPanel(new GridLayout(1, 2, 10, 10));
        panelInferior.setPreferredSize(new Dimension(1000, 220)); 
        
        // Simulación de Eventos (Entrada para el motor de lógica)
        JPanel panelSimulacion = new JPanel(new GridBagLayout());
        panelSimulacion.setBorder(BorderFactory.createTitledBorder("Generador de Eventos Ambientales"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5); gbc.fill = GridBagConstraints.HORIZONTAL;

        comboEvento = new JComboBox<>(TipoEvento.values());
        sliderIntensidad = new JSlider(1, 10, 5);
        sliderIntensidad.setMajorTickSpacing(1);
        sliderIntensidad.setPaintTicks(true); sliderIntensidad.setPaintLabels(true);
        JButton btnAplicarEvento = new JButton("Aplicar Evento al Ecosistema");
        btnAplicarEvento.setBackground(new Color(231, 76, 60));
        btnAplicarEvento.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; panelSimulacion.add(new JLabel("Tipo de Evento:"), gbc);
        gbc.gridx = 1; panelSimulacion.add(comboEvento, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelSimulacion.add(new JLabel("Intensidad (1-10):"), gbc);
        gbc.gridx = 1; panelSimulacion.add(sliderIntensidad, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; panelSimulacion.add(btnAplicarEvento, gbc);

        btnAplicarEvento.addActionListener(this::aplicarEventoAmbiental);

        // Historial (Visualización de la Pila LIFO)
        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setBackground(new Color(245, 245, 245));
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollHistorial = new JScrollPane(areaHistorial);
        scrollHistorial.setBorder(BorderFactory.createTitledBorder("Historial Reciente (PILA LIFO)"));
        scrollHistorial.setPreferredSize(new Dimension(450, 180)); 

        panelInferior.add(panelSimulacion);
        panelInferior.add(scrollHistorial);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // --- LÓGICA DE EVENTOS (MÉTODOS DE ACCIÓN) ---

    /**
     * Registra una nueva especie en la listaEspecies(lista simple).
     * Valida datos e inserta el registro dentro de la pila.
     * @param e 
     */
    private void registrarEspecie(ActionEvent e) {
        try {
            String nombre = txtNombreEspecie.getText().trim();
            int poblacion = Integer.parseInt(txtPoblacionInicial.getText());
            TipoEspecie tipo = (TipoEspecie) comboTipo.getSelectedItem();

            if (nombre.isEmpty()) throw new Exception();

            Especie nueva = new Especie(nombre, poblacion, tipo);
            listaEspecies.insertarEspecie(nueva); // Inserción en Lista Manual
            
            historial.setEvento("Registro: " + nombre + " [" + poblacion + "]"); // Push a la Pila
            actualizarInterfaz();
            
            txtNombreEspecie.setText(""); txtPoblacionInicial.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos. Verifique nombre y población.");
        }
    }

    /**
     * Aplica un desastre o beneficio al ecosistema.
     * 1. Captura poblaciones previas.
     * 2. Aplica cambios mediante EventosAmbientales.
     * 3. Registra impacto en el Árbol de Búsqueda Binaria.
     * 4. Abre la ventana de reporte con las diferencias detectadas(nacimientos y muertes).
     * @param e 
     */
    private void aplicarEventoAmbiental(ActionEvent e) {
        TipoEvento evento = (TipoEvento) comboEvento.getSelectedItem();
        int intensidad = sliderIntensidad.getValue();

        // Obtener estado actual para comparar después
        int total = 0;
        NodoEspecie tempC = obtenerNodoCabeza();
        while(tempC != null) { total++; tempC = tempC.getLIGA(); }

        int[] pobsAntes = new int[total];
        String[] nombres = new String[total];
        NodoEspecie temp = obtenerNodoCabeza();
        for(int i=0; i<total; i++) {
            pobsAntes[i] = temp.getEspecie().getPoblacion();
            nombres[i] = temp.getEspecie().getNombre();
            temp = temp.getLIGA();
        }

        // Lógica de afectación y registro en Árbol BST
        motorEventos.aplicar(evento, intensidad, listaEspecies, historial);
        arbol.insertaDecision(evento.name(), intensidad * 10); // Nodo del árbol basado en magnitud
        
        // Generar listas para el reporte de muertes y nacimientos
        String[] nacimientos = new String[total];
        String[] muertes = new String[total];
        int in = 0, im = 0;

        temp = obtenerNodoCabeza();
        for(int i=0; i<total; i++) {
            int pobActual = temp.getEspecie().getPoblacion();
            int diferencia = pobActual - pobsAntes[i];
            
            if (diferencia > 0) nacimientos[in++] = nombres[i] + ": +" + diferencia;
            else if (diferencia < 0) muertes[im++] = nombres[i] + ": " + diferencia + (pobActual <= 0 ? " [EXTINTA]" : "");
            
            temp = temp.getLIGA();
        }

        actualizarInterfaz();
        new VentanaReporteDetallado(evento.name(), nacimientos, muertes).setVisible(true);
    }

    /**
     * Usa la COLA DE PRIORIDAD para detectar qué especie está más cerca de la extinción.
     * Extrae datos de la Lista Simple y los inserta ordenadamente en la Cola.
      * @param e 
      */
    private void mostrarPrioridades(ActionEvent e) {
        ColaDePrioridad cola = new ColaDePrioridad();
        NodoEspecie temp = obtenerNodoCabeza();
        
        while (temp != null) {
            if (temp.getEspecie().getPoblacion() > 0) {
                cola.agregar(temp.getEspecie()); // Inserción con ordenamiento manual
            }
            temp = temp.getLIGA(); 
        }

        if (cola.getP() != null) {
            JOptionPane.showMessageDialog(this, "Especie con mayor riesgo:\n" + cola.getP().getEspecie());
        } else {
            JOptionPane.showMessageDialog(this, "No hay especies vivas.");
        }
    }

    /**
     * Dispara el recorrido IN-ORDER del Árbol de Escenarios.
      * @param e 
      */
    private void mostrarEscenarios(ActionEvent e) {
        arbol.recorrerArbol(); 
        arbol.mostrarEscenario(); 
        JOptionPane.showMessageDialog(this, "Análisis de jerarquía generado en consola.");
    }

    /**
     * Elimina una especie seleccionada directamente desde la tabla de especies.
     * @param e 
     */
    private void eliminarEspecie(ActionEvent e) {
        int fila = tablaEspecies.getSelectedRow();
        if (fila != -1) {
            String nombre = (String) modeloTabla.getValueAt(fila, 0);
            listaEspecies.eliminarEspecie(nombre);
            historial.setEvento("Eliminado: " + nombre);
            actualizarInterfaz();
        }
    }

    private void actualizarInterfaz() {
        actualizarTabla();
        actualizarHistorial();
    }

    /**
     * Recorre la Lista Simple para refrescar los datos en el JTable.
     */
     
    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        NodoEspecie temp = obtenerNodoCabeza();
        while (temp != null) {
            Especie esp = temp.getEspecie();
            esp.verificarExtincion();
            Object[] fila = { esp.getNombre(), esp.getPoblacion(), esp.getTipo(), esp.getNivelRiesgo(), esp.isExtinta() ? "EXTINTA" : "ACTIVA" };
            modeloTabla.addRow(fila);
            temp = temp.getLIGA();
        }
    }

    /**
     * Actualiza el JTextArea insertando el último evento de la Pila en la parte superior.
     */
    private void actualizarHistorial() {
        String ultimo = historial.getEvento();
        if (ultimo != null && !ultimo.equals("Historial vacío")) {
            areaHistorial.insert("- " + ultimo + "\n", 0);
            areaHistorial.setCaretPosition(0);
        }
    }
    
    private NodoEspecie obtenerNodoCabeza() { return listaEspecies.getP(); }
}