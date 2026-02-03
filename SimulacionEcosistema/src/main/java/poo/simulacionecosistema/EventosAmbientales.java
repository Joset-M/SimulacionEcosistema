package poo.simulacionecosistema;

public class EventosAmbientales {

    public void aplicar(TipoEvento t, int i, ListaEspecies l, PilaHistorial h) {
        double f = i * 0.10; 

        if (l.P != null) {
            NodoEspecie n = l.P;
            while (n != null) {
                int c = (int) (n.especie.poblacion * f);
                if (c == 0 && n.especie.poblacion > 0) c = 1;

                switch (t) {
                    case SEQUIA:
                    case INCENDIO:
                    case CONTAMINACION:
                        n.especie.disminuirPoblacion(c);
                        break;
                    case LLUVIA:
                    case REPRODUCCION:
                    case MIGRACION:
                        n.especie.aumentarPoblacion(c);
                        break;
                }
                n = n.LIGA;
            }
        }
        h.setEvento(t.name() + " Nivel " + i);
    }
}