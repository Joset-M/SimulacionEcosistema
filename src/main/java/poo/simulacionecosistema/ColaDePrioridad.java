/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.simulacionecosistema;

/**
 *
 * @author Usuario-Asus
 */

 //P: Nodo cabeza
 //T: Nodo aux
 //Q: Nodo nuevo
public class ColaDePrioridad {
    private NodoEspecie P;
    
    public void agregar(Especie e){
        NodoEspecie Q = new NodoEspecie(e);
        //Prioridad: menor población a mayor población
        if(P == null || e.poblacion < P.especie.poblacion){
            Q.LIGA = P;
            P = Q;
        }else{
            NodoEspecie T = P;
            //Ordenamiento método Inserccion
            while(T.LIGA != null && T.LIGA.especie.poblacion <= e.poblacion){
                T = T.LIGA;
            }
            Q.LIGA = T.LIGA;
            T.LIGA = Q;
        }
    } 
    
    public NodoEspecie getP() {
        return P;
    }
}
