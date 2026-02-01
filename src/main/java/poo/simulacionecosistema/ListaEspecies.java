package poo.simulacionecosistema;

 //P: Nodo cabeza
 //T: Nodo aux
 //Q: Nodo nuevo

public class ListaEspecies {

    private NodoEspecie P;

    public ListaEspecies(int poblacionHumanaInicial) {
        Humano humano = new Humano(poblacionHumanaInicial);
        P = new NodoEspecie(humano);
    }

    // Insertar especie al final
    public void insertarEspecie(Especie e) {
        NodoEspecie Q = new NodoEspecie(e);

        if (P == null) {
            P = Q;
            return;
        }

        NodoEspecie T = P;
        Q.LIGA = null;

        while (T.LIGA != null) {
            T = T.LIGA;
        }
        T.LIGA = Q;
    }

    // Buscar especie por nombre
    public Especie buscarEspecie(String nombre) {
        NodoEspecie T = P;

        while (T != null) {
            if (T.especie.getNombre().equalsIgnoreCase(nombre)) {
                return T.especie;
            }
            T = T.LIGA;
        }
        return null;
    }

    // Eliminar cualquier especie (incluido Humano)
    public void eliminarEspecie(String nombre) {
        if (P == null) return;

        // Si es la cabeza
        if (P.especie.getNombre().equalsIgnoreCase(nombre)) {
            P = P.LIGA;
            return;
        }

        NodoEspecie T = P;
        while (T.LIGA != null) {
            if (T.LIGA.especie.getNombre().equalsIgnoreCase(nombre)) {
                T.LIGA = T.LIGA.LIGA;
                return;
            }
            T = T.LIGA;
        }
    }

    // Mostrar especies
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
}
