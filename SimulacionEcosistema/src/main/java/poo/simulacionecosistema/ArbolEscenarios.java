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

class NodoEscenario {
    String decision;
    int impacto; 
    NodoEscenario nodoIzq;
    NodoEscenario nodoDer;

    public NodoEscenario(String decision, int impacto) {
        this.decision = decision;
        this.impacto = impacto;
        this.nodoIzq = null;
        this.nodoDer = null;
    }
}

public class ArbolEscenarios {
    private NodoEscenario raiz;
    
    public void insertaDecision(String decision, int impacto) {
        raiz = insertarRecursivo(raiz, decision, impacto);
    }

    private NodoEscenario insertarRecursivo(NodoEscenario actual, String desc, int val) {
        if (actual == null) return new NodoEscenario(desc, val);
        
        if (val < actual.impacto) {
            actual.nodoIzq = insertarRecursivo(actual.nodoIzq, desc, val);
        } else if (val > actual.impacto) {
            actual.nodoDer = insertarRecursivo(actual.nodoDer, desc, val);
        }
        return actual;
    }

    public void recorrerArbol() {
        System.out.println("\n ANALISIS DE ESCENARIOS (ABB)");
        ayudanteInOrder(raiz);
    }

    private void ayudanteInOrder(NodoEscenario nodo) {
        if (nodo != null) {
            ayudanteInOrder(nodo.nodoIzq);
            System.out.println("[" + nodo.impacto + " pts] Escenario: " + nodo.decision);
            ayudanteInOrder(nodo.nodoDer);
        }
    }

    public void mostrarEscenario() {
        if (raiz != null) {
            System.out.println("Escenario Actual de Referencia: " + raiz.decision);
        } else {
            System.out.println("No hay escenarios analizados.");
        }
    }
}