package poo.simulacionecosistema;

public class NodoEspecie {

    Especie especie;
    NodoEspecie LIGA;

    public NodoEspecie(Especie especie) {
        this.especie = especie;
        this.LIGA = null;
    }
}

