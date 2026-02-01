package poo.simulacionecosistema;

public enum TipoEspecie {
    HUMANO(5, 1),
    AVE(1, 2),
    PEZ(2, 2),
    FELINO(3, 3),
    MAMIFERO(4, 2);

    private final int consumoRecursos;
    private final int riesgoBase;

    TipoEspecie(int consumoRecursos, int riesgoBase) {
        this.consumoRecursos = consumoRecursos;
        this.riesgoBase = riesgoBase;
    }

    public int getConsumoRecursos() {
        return consumoRecursos;
    }

    public int getRiesgoBase() {
        return riesgoBase;
    }
}
