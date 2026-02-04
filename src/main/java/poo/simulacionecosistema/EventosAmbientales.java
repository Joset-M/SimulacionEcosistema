package poo.simulacionecosistema;

/**
 * CLASE DE LÓGICA DE NEGOCIO: EventosAmbientales
 * Funciona como el "Motor de Simulación" del sistema.
 * Su objetivo es aplicar cambios masivos en las poblaciones de la ListaEspecies
 * basándose en desastres naturales o beneficios ambientales.
 */
public class EventosAmbientales {

    /**
     * MÉTODO APLICAR
     * Es el núcleo de la simulación. Recorre toda la lista de especies y modifica 
     * sus poblaciones según el evento y la intensidad seleccionada.
     * * @param t El TipoEvento a ejecutar (Enum que define si es positivo o negativo).
     * @param i La intensidad del evento (del 1 al 10).
     * @param l La ListaEspecies (Lista Simple) que contiene a todos los seres vivos.
     * @param h La PilaHistorial donde se registrará la actividad.
     */
    public void aplicar(TipoEvento t, int i, ListaEspecies l, PilaHistorial h) {
        // f: Factor de impacto. Se calcula como un porcentaje (intensidad * 10%).
        // Ejemplo: Intensidad 5 = 0.50 (50% de afectación).
        double f = i * 0.10; 

        // Validación: Solo operamos si la lista tiene elementos (cabeza P no nula).
        if (l.getP() != null) {
            // n: Nodo auxiliar para recorrer la lista enlazada desde la cabeza P.
            NodoEspecie n = l.getP();
            
            while (n != null) {
                // c: Cantidad de individuos afectados.
                // Se calcula multiplicando la población actual por el factor de impacto.
                int c = (int) (n.getEspecie().getPoblacion() * f);
                
                // Regla de Protección: Si el cálculo da 0 pero la especie aún vive, 
                // se afecta al menos a 1 individuo para que el evento tenga efecto.
                if (c == 0 && n.getEspecie().getPoblacion() > 0) c = 1;

                // Lógica de Selección: Se decide si se aumenta o disminuye la población.
                switch (t) {
                    // Eventos de impacto negativo (Catástrofes)
                    case SEQUIA:
                    case INCENDIO:
                    case CONTAMINACION:
                        n.getEspecie().disminuirPoblacion(c);
                        break;
                    
                    // Eventos de impacto positivo (Crecimiento)
                    case LLUVIA:
                    case REPRODUCCION:
                    case MIGRACION:
                        n.getEspecie().aumentarPoblacion(c);
                        break;
                }
                
                // Avance del puntero a la siguiente posición de la lista (LIGA).
                n = n.getLIGA();
            }
        }
        
        // Registro en la Pila: Se guarda el nombre del evento y su nivel para la bitácora.
        h.setEvento(t.name() + " Nivel " + i);
    }
}