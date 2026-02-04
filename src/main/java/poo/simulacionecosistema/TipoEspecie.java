package poo.simulacionecosistema;

/**
 * ENUMERACIÓN: TipoEspecie
 * Define las categorías taxonómicas disponibles en el simulador.
 * Cada tipo posee valores predefinidos que afectan la lógica de consumo
 * de recursos y la vulnerabilidad inicial (riesgo base) en el ecosistema.
 */
public enum TipoEspecie {
    // Definición de categorías con sus valores de (consumoRecursos, riesgoBase)
    AVE(1, 2),
    PEZ(2, 2),
    REPTILES(3, 4),
    ANFIBIOS(3, 3),
    MAMIFERO(4, 2);

    // Atributos finales para la configuración biológica del tipo
    private final int consumoRecursos;
    private final int riesgoBase;

    /**
     * CONSTRUCTOR DEL ENUM
     * Asigna las constantes de impacto ambiental a cada categoría.
     * @param consumoRecursos Valor de gasto de energía/agua por individuo.
     * @param riesgoBase Nivel de peligro inicial frente a eventos externos.
     */
    TipoEspecie(int consumoRecursos, int riesgoBase) {
        this.consumoRecursos = consumoRecursos;
        this.riesgoBase = riesgoBase;
    }

    /**
     * Obtiene el factor de consumo de recursos.
     * @return Valor entero de consumo.
     */
    public int getConsumoRecursos() {
        return consumoRecursos;
    }

    /**
     * Obtiene el nivel de riesgo inherente a la especie.
     * @return Valor entero de riesgo base.
     */
    public int getRiesgoBase() {
        return riesgoBase;
    }

    /**
     * REPRESENTACIÓN VISUAL
     * Formatea el nombre de la constante para su uso en la interfaz gráfica.
     */
    @Override
    public String toString() {
        String nombre = name().toLowerCase();
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1);
    }
}