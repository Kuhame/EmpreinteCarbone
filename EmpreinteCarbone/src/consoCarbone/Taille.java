package consoCarbone;

public enum Taille {
    P(4.2),
    G(19);

    private final double conso;

    Taille(double conso) {
        this.conso = conso;
    }

    public double getCoef() {
        return conso;
    }
}