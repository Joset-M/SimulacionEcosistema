package poo.simulacionecosistema;

public class Especie {

    protected String nombre;
    protected int poblacion;
    protected int nivelRiesgo;
    protected boolean extinta;
    protected TipoEspecie tipo;

    public Especie(String nombre, int poblacion, TipoEspecie tipo) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.tipo = tipo;
        this.nivelRiesgo = tipo.getRiesgoBase();
        this.extinta = false;
        verificarExtincion();
    }

    public int consumoTotal() {
        return poblacion * tipo.getConsumoRecursos();
    }

    public void aumentarPoblacion(int cantidad){
        poblacion += cantidad;
    }
    
    public void disminuirPoblacion(int cantidad) {
        poblacion -= cantidad;
        verificarExtincion();
    }

    public boolean verificarExtincion() {
        if (poblacion <= 0) {
            poblacion = 0;
            extinta = true;
        }
        return extinta;
    }
}
