package poo.simulacionecosistema;

/**
 * CLASE LISTA SIMPLEMENTE ENLZADA: ListaEspecies
 * Esta estructura de datos manual actúa como el contenedor principal del ecosistema.
 * Utiliza nodos enlazados (NodoEspecie) para permitir un crecimiento dinámico de la
 * población de especies sin las limitaciones de tamaño de un arreglo estático.
 */
public class ListaEspecies {
    // P: Puntero al Nodo Cabeza (primer elemento de la lista)
    NodoEspecie P;
    /**
     * CONSTRUCTOR CON POBLACIÓN INICIAL
     * Crea la lista e inicializa el primer nodo con una especie base.
     * @param poblacionInicial Cantidad de individuos para la primera especie.
     */
    public ListaEspecies(int poblacionInicial) {
        Especie e = new Especie(poblacionInicial);
        P = new NodoEspecie(e);
    }
    /**
     * CONSTRUCTOR VACÍO
     * Inicializa una lista sin elementos (P = null).
     */
    public ListaEspecies() {
    }

    /**
     * MÉTODO INSERTAR AL FINAL
     * Crea un nuevo nodo Q y lo enlaza al final de la estructura.
     * @param e El objeto Especie que se desea registrar.
     */
    public void insertarEspecie(Especie e) {
        // Q: Nodo nuevo que contiene la especie
        NodoEspecie Q = new NodoEspecie(e);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        if (P == null) {
            P = Q;
            return;
        }

        // T: Nodo auxiliar para recorrer la lista hasta el último elemento
        NodoEspecie T = P;
        Q.LIGA = null; // Nos aseguramos que el nuevo último nodo apunte a null
       
        // Recorrido hasta encontrar el nodo cuya LIGA sea null
        while (T.LIGA != null) {
            T = T.LIGA;
        }
        // Se realiza el enlace del último nodo actual con el nuevo nodo Q
        T.LIGA = Q;
    }

    /**
     * MÉTODO BUSCAR POR NOMBRE (Búsqueda Lineal)
     * Recorre la lista comparando nombres ignorando mayúsculas/minúsculas.
     * @param nombre El nombre de la especie a buscar.
     * @return El objeto Especie si se encuentra, de lo contrario null.
     */
    public Especie buscarEspecie(String nombre) {
        NodoEspecie T = P;

        while (T != null) {
            if (T.especie.getNombre().equalsIgnoreCase(nombre)) {
                return T.especie;
            }
            T = T.LIGA; // Avanza al siguiente nodo
        }
        return null;
    }

    /**
     * MÉTODO ELIMINAR CUALQUIER ESPECIE
     * Busca una especie por nombre y reestructura los enlaces para saltarse su nodo.
     * @param nombre Nombre de la especie a dar de baja del ecosistema.
     */
    public void eliminarEspecie(String nombre) {
        if (P == null) return;

        // CASO 1: La especie a eliminar es la cabeza de la lista (P)
        if (P.especie.getNombre().equalsIgnoreCase(nombre)) {
            P = P.LIGA;
            return;
        }

        // CASO 2: La especie está en medio o al final
        NodoEspecie T = P;
        while (T.LIGA != null) {
            // Revisamos el nombre del siguiente nodo
            if (T.LIGA.especie.getNombre().equalsIgnoreCase(nombre)) {
                // "Saltamos" el nodo objetivo enlazando T con el nodo subsiguiente
                T.LIGA = T.LIGA.LIGA;
                return;
            }
            T = T.LIGA;
        }
    }

    /**
     * MÉTODO MOSTRAR (Recorrido Completo)
     * Imprime en consola todas las especies registradas utilizando el método toString().
     */
    public void mostrarEspecies() {
        NodoEspecie T = P;

        if (T == null) {
            System.out.println("No hay especies en el ecosistema.");
            return;
        }

        while (T != null) {
            System.out.println(T.especie);
            T = T.LIGA;
        }
    }
    
    /**
     * Permite  obtener la cabeza de la lista.
     * @return El puntero P al inicio de la lista.
     */
    public NodoEspecie getP() {
        return this.P;
    }
    

}
