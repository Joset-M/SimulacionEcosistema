public class Humano extends Especie {

    public Humano(int poblacion) {
        super("Humano", poblacion, 1); // riesgo bajo por defecto
    }

    // Ejemplo de comportamiento específico (opcional)
    public void intervenirEcosistema() {
        System.out.println("El humano ha intervenido el ecosistema.");
    }
}
