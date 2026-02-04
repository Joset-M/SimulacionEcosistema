/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.simulacionecosistema;

/**
 *
 * @author HP
 */

/**
 * CLASE NODO DEL ÁRBOL
 * Representa un punto de decisión o evento en el historial del ecosistema.
 * Funciona como la unidad básica del Árbol de Búsqueda Binaria (ABB).
 */
class NodoEscenario {
    String decision;      // Nombre del evento ambiental (ej: Sequía, Inundación)
    int impacto;         // Valor numérico de la gravedad del evento (clave de ordenamiento)
    NodoEscenario nodoIzq; // Referencia al hijo izquierdo (impactos menores)
    NodoEscenario nodoDer; // Referencia al hijo derecho (impactos mayores)

    /**
     * Constructor del nodo.
     * @param decision Descripción del evento.
     * @param impacto Valor de intensidad para clasificar el nodo.
     */
    public NodoEscenario(String decision, int impacto) {
        this.decision = decision;
        this.impacto = impacto;
        this.nodoIzq = null;
        this.nodoDer = null;
    }
}

/**
 * CLASE ÁRBOL DE ESCENARIOS (Árbol de Búsqueda Binaria - BST)
 * Esta estructura organiza los eventos ambientales de forma jerárquica.
 * Permite clasificar la severidad de los impactos para análisis predictivos.
 */
public class ArbolEscenarios {
    private NodoEscenario raiz; // El primer evento registrado se convierte en el origen del árbol
    
    /**
     * MÉTODO DE INSERCIÓN PÚBLICO
     * Punto de entrada para agregar una nueva decisión al historial jerárquico.
     */
    public void insertaDecision(String decision, int impacto) {
        raiz = insertarRecursivo(raiz, decision, impacto);
    }

    /**
     * LÓGICA DE INSERCIÓN RECURSIVA (Core del BST)
     * Clasifica el nuevo nodo comparando su impacto con los nodos existentes.
     * Menor impacto -> va a la izquierda.
     * Mayor impacto -> va a la derecha.
     */
    private NodoEscenario insertarRecursivo(NodoEscenario actual, String desc, int val) {
        // Si llegamos a una posición vacía, creamos el nodo aquí
        if (actual == null) return new NodoEscenario(desc, val);
        
        // Comparación para determinar la rama de inserción
        if (val < actual.impacto) {
            // Si el impacto es menor, bajamos por la izquierda
            actual.nodoIzq = insertarRecursivo(actual.nodoIzq, desc, val);
        } else if (val > actual.impacto) {
            // Si el impacto es mayor, bajamos por la derecha
            actual.nodoDer = insertarRecursivo(actual.nodoDer, desc, val);
        }
        // Retornamos el nodo actual para mantener la estructura de enlaces
        return actual;
    }

    /**
     * RECORRIDO DEL ÁRBOL (In-Order)
     * Muestra los escenarios ordenados de menor impacto a mayor impacto.
     * Es fundamental para visualizar la tendencia de degradación del ecosistema.
     */
    public void recorrerArbol() {
        System.out.println("\n --- ANÁLISIS DE ESCENARIOS (ABB) ---");
        ayudanteInOrder(raiz);
    }

    /**
     * MÉTODO AUXILIAR RECURSIVO (In-Order)
     * Sigue la secuencia: Izquierda -> Raíz -> Derecha.
     * Gracias a la propiedad del BST, esto imprime los datos en orden ascendente.
     */
    private void ayudanteInOrder(NodoEscenario nodo) {
        if (nodo != null) {
            ayudanteInOrder(nodo.nodoIzq); // Visita rama menor
            System.out.println("[" + nodo.impacto + " pts] Escenario: " + nodo.decision); // Procesa raíz
            ayudanteInOrder(nodo.nodoDer); // Visita rama mayor
        }
    }

    /**
     * Muestra el escenario base o raíz.
     * Útil para identificar cuál fue el primer evento que desencadenó la cadena de cambios.
     */
    public void mostrarEscenario() {
        if (raiz != null) {
            System.out.println("Escenario Actual de Referencia (Raíz): " + raiz.decision);
        } else {
            System.out.println("No hay escenarios analizados aún.");
        }
    }
}