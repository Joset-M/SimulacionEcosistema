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
public class PilaHistorial {
    private NodoEvento tope; 

    // Nodo interno para la pila
    private class NodoEvento {
        String evento;
        NodoEvento liga;

        public NodoEvento(String evento) {
            this.evento = evento;
            this.liga = null;
        }
    }

    public void setEvento(String evento) {
        NodoEvento nuevo = new NodoEvento(evento);
        nuevo.liga = tope;
        tope = nuevo;
    }

    public String getEvento() {
        if (tope == null) return "Historial vacío";
        String eventoRecuperado = tope.evento;
        tope = tope.liga;
        return eventoRecuperado;
    }

    public void mostrarHistorial() {
        System.out.println("\n HISTORIAL DEL ECOSISTEMA (PILA) ");
        NodoEvento temp = tope;
        if (temp == null) System.out.println("No hay eventos registrados.");
        while (temp != null) {
            System.out.println("- " + temp.evento);
            temp = temp.liga;
        }
    }
}
