package poo.simulacionecosistema;

/**
 * CLASE NODO: NodoEspecie
 * Actúa como el contenedor individual de la Lista Enlazada.
 * Su función es doble: almacenar los datos de una 'Especie' y mantener 
 * la referencia (puntero) hacia el siguiente elemento de la estructura.
 */
public class NodoEspecie {
// Atributo que almacena el objeto con la información biológica
    Especie especie;
    
    /**
     * LIGA: Puntero de enlace.
     * Almacena la dirección de memoria del siguiente nodo en la lista.
     * Si es null, indica que este es el último nodo (final de la lista).
     */
    NodoEspecie LIGA;

    /**
     * CONSTRUCTOR
     * Inicializa un nuevo nodo con la especie proporcionada.
     * Por defecto, la LIGA se establece en null hasta que se enlace con otro nodo.
     * @param especie Objeto de la clase Especie a almacenar.
     */
    public NodoEspecie(Especie especie) {
        this.especie = especie;
        this.LIGA = null;
    }

    /**
     * Obtiene la especie contenida en este nodo.
     * @return Objeto Especie.
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * Obtiene el siguiente nodo en la secuencia.
     * @return Referencia al siguiente NodoEspecie o null si es el final.
     */
    public NodoEspecie getLIGA() {
        return LIGA;
    }
}

