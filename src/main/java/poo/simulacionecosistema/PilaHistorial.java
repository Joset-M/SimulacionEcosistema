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
 * CLASE PILA: PilaHistorial
 * Implementa una estructura de datos lineal de tipo LIFO (Last In, First Out).
 * Su función es almacenar la bitácora de eventos del ecosistema, asegurando que
 * el último suceso ocurrido sea el primero en visualizarse.
 */
public class PilaHistorial {
    
    /**
     * tope: Puntero que referencia al nodo en la cima de la pila.
     * Representa el evento más reciente registrado.
     */
    private NodoEvento tope; 

    /**
     * CLASE INTERNA PRIVADA: NodoEvento
     * Define la estructura de los eslabones de la pila. 
     * Se mantiene privada para encapsular la gestión de memoria de la bitácora.
     */
    private class NodoEvento {
        String evento;    // Descripción del suceso (ej: "SEQUIA Nivel 5")
        NodoEvento liga;  // Enlace al nodo que está debajo en la pila

        public NodoEvento(String evento) {
            this.evento = evento;
            this.liga = null;
        }
    }

    /**
     * MÉTODO PUSH (setEvento)
     * Inserta un nuevo evento en la cima de la pila.
     * @param evento Texto descriptivo del suceso ambiental o acción.
     */
    public void setEvento(String evento) {
        NodoEvento nuevo = new NodoEvento(evento);
        
        // El nuevo nodo se coloca arriba y apunta al antiguo tope
        nuevo.liga = tope;
        
        // El puntero tope se actualiza a la nueva posición más alta
        tope = nuevo;
    }

    /**
     * MÉTODO POP (getEvento)
     * Recupera y elimina el evento que está en la cima de la pila.
     * @return El texto del evento recuperado o un mensaje si la pila está vacía.
     */
    public String getEvento() {
        if (tope == null) return "Historial vacío";
        
        // Se captura la información del nodo superior
        String eventoRecuperado = tope.evento;
        
        // Se "desapila" moviendo el puntero tope al nodo de abajo
        tope = tope.liga;
        
        return eventoRecuperado;
    }

    /**
     * MÉTODO RECORRIDO (mostrarHistorial)
     * Realiza una lectura completa de la pila desde el tope hasta la base
     * sin destruir los datos, utilizando un nodo auxiliar.
     */
    public void mostrarHistorial() {
        System.out.println("\n --- HISTORIAL DEL ECOSISTEMA (PILA LIFO) ---");
        
        // temp: Nodo auxiliar para no perder la referencia original del tope
        NodoEvento temp = tope;
        
        if (temp == null) {
            System.out.println("No hay eventos registrados.");
        }
        
        // Se itera mientras no lleguemos al fondo de la pila (null)
        while (temp != null) {
            System.out.println("- " + temp.evento);
            temp = temp.liga; // Se desciende al siguiente nivel de la pila
        }
    }
}