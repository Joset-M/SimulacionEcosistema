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
 * ENUMERACIÓN: TipoEvento
 * Define los diferentes fenómenos que pueden ocurrir dentro del ecosistema.
 * Estos eventos se dividen lógicamente en impactos negativos (desastres) 
 * e impactos positivos (crecimiento y expansión).
 * * @author Usuario-Asus
 */
public enum TipoEvento {
    /** * EVENTOS NEGATIVOS:
     * Reducen la población de las especies según el factor de intensidad.
     */
    SEQUIA,
    INCENDIO,
    CONTAMINACION,

    /** * EVENTOS POSITIVOS:
     * Incrementan la población de las especies, favoreciendo el equilibrio.
     */
    LLUVIA,
    REPRODUCCION,
    MIGRACION;
}
