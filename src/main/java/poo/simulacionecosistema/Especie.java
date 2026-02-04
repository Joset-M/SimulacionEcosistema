package poo.simulacionecosistema;

/**
 * CLASE ENTIDAD: Especie
 * Representa la unidad biológica fundamental del simulador.
 * Contiene los atributos de estado (población, riesgo) y los métodos 
 * necesarios para gestionar su ciclo de vida y supervivencia.
 */
public class Especie {

    // --- ATRIBUTOS DE ESTADO (Encapsulamiento protegido para herencia) ---
    protected String nombre;      // Identificador único de la especie
    protected int poblacion;      // Cantidad actual de individuos vivos
    protected int nivelRiesgo;    // Valor numérico basado en el riesgo biológico
    protected boolean extinta;    // Estado lógico que indica si la especie ha desaparecido
    protected TipoEspecie tipo;   // Categoría taxonómica (Productor, Depredador, etc.)

    /**
     * CONSTRUCTOR PRINCIPAL
     * Inicializa una especie con sus valores base y verifica su estado inicial.
     * @param nombre Nombre común de la especie.
     * @param poblacion Cantidad inicial de individuos.
     * @param tipo Categoría del enum TipoEspecie.
     */
    public Especie(String nombre, int poblacion, TipoEspecie tipo) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.tipo = tipo;
        // El riesgo base se obtiene directamente de la configuración del tipo
        this.nivelRiesgo = tipo.getRiesgoBase(); 
        this.extinta = false;
        verificarExtincion();
    }

    /**
     * CONSTRUCTOR SOBRECARGADO
     * Utilizado para instanciamientos rápidos o cálculos donde solo importa la población.
     * @param poblacion Cantidad de individuos.
     */
    public Especie(int poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * CÁLCULO DE IMPACTO DE RECURSOS
     * Calcula cuántas unidades de recursos consume la especie en total
     * multiplicando la población por el gasto individual de su tipo.
     * @return El consumo energético/hídrico total.
     */
    public int consumoTotal() {
        return poblacion * tipo.getConsumoRecursos();
    }

    /**
     * LÓGICA DE CRECIMIENTO
     * Incrementa la población actual. Suele llamarse tras eventos positivos 
     * o ciclos de reproducción exitosos.
     * @param cantidad Número de individuos a sumar.
     */
    public void aumentarPoblacion(int cantidad){
        poblacion += cantidad;
    }
    
    /**
     * LÓGICA DE DECREMENTO
     * Reduce la población y dispara automáticamente la verificación de extinción.
     * @param cantidad Número de individuos a restar.
     */
    public void disminuirPoblacion(int cantidad) {
        poblacion -= cantidad;
        verificarExtincion();
    }

    /**
     * CONTROL DE ESTADO CRÍTICO
     * Evalúa si la población ha llegado a cero. 
     * Asegura que el valor de población no sea negativo por errores de cálculo.
     * @return true si la especie está extinta, false en caso contrario.
     */
    public boolean verificarExtincion() {
        if (poblacion <= 0) {
            poblacion = 0;
            extinta = true;
        }
        return extinta;
    }
    
    // --- MÉTODOS DE ACCESO (GETTERS Y SETTERS) ---

    public String getNombre() {
        return nombre;
    }
    
    /**
     * REPRESENTACIÓN EN TEXTO
     * Facilita la visualización de la especie en consola o componentes de la interfaz.
     */
    @Override
    public String toString() {
        return "Especie: " + nombre + " | Población: " + poblacion + " | Extinta: " + (extinta ? "Sí" : "No");
    }
    
    /**
     * Obtiene la poblacion actual.
     * @return 
     */
    public int getPoblacion() {
        return poblacion;
    }

    /**
     * Obtiene el tipo de especie.
     * @return 
     */
    public TipoEspecie getTipo() {
        return tipo;
    }

    /**
     * Obtiene el nivel de riesgo de la especie 
     * @return 
     */
    public int getNivelRiesgo() {
        return nivelRiesgo;
    }

    /**
     * Nos facilita el saber si una especie aun tiene seres vivos.
     * @return 
     */
    public boolean isExtinta() {
        return extinta;
    }

    /**
     * Nos permite modificar el valor.
     * @param extinta 
     */
    public void setExtinta(boolean extinta) {
        this.extinta = extinta;
    }
}