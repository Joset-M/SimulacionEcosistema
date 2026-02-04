/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.simulacionecosistema;

/**
 *
 * @author Usuario-Asus
 */
/**
 * CLASE COLA DE PRIORIDAD (Basada en Lista Enlazada)
 * Esta estructura organiza las especies automáticamente según su nivel de urgencia.
 * En este sistema, la prioridad se define por la población: a menor cantidad de individuos, 
 * mayor es la prioridad de atención para evitar la extinción.
 */
public class ColaDePrioridad {
    // P: Representa el nodo cabeza de la cola (el de mayor prioridad/menor población)
    private NodoEspecie P;
    /**
     * MÉTODO AGREGAR CON ORDENAMIENTO POR INSERCIÓN
     * A diferencia de una cola simple, este método busca la posición correcta
     * para el nuevo nodo basándose en la población de la especie.
     * * @param e Objeto Especie a ser evaluado e insertado.
     */
    public void agregar(Especie e){
        // Q: Nodo nuevo que se intenta insertar
        NodoEspecie Q = new NodoEspecie(e);
        // CASO 1: La cola está vacía o la nueva especie tiene menos población que la actual cabeza (P)
        // Se inserta al inicio (prioridad máxima)
        if(P == null || e.poblacion < P.especie.poblacion){
            Q.LIGA = P;
            P = Q;
        }else{
            // CASO 2: Se recorre la cola para encontrar el lugar adecuado
            // T: Nodo auxiliar para el recorrido
            NodoEspecie T = P;
            // Lógica del Algoritmo de Inserción:
            // Se avanza mientras el siguiente nodo no sea nulo y su población sea menor o igual a la nueva
            while(T.LIGA != null && T.LIGA.especie.poblacion <= e.poblacion){
                T = T.LIGA;
            }
            
            // Se realizan los enlaces (puentes) para insertar Q después de T
            Q.LIGA = T.LIGA;
            T.LIGA = Q;
        }
    } 
    
    /**
     * Obtiene el primer elemento de la cola.
     * @return El nodo con la especie más vulnerable actualmente.
     */
    public NodoEspecie getP() {
        return P;
    }
}
