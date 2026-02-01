/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.simulacionecosistema;

/**
 *
 * @author Usuario-Asus
 */
public class Humano extends Especie {

    public Humano(int poblacion) {
        super("Humano", poblacion, TipoEspecie.HUMANO); // riesgo bajo por defecto
    }



    // Ejemplo de comportamiento específico (opcional)
    public void intervenirEcosistema() {
        System.out.println("El humano ha intervenido el ecosistema.");
    }
}
