package consoCarbone;

public enum Taille {
    P(4.2), // Petite voiture
    G(19.); // Grosse voiture

    private final double consoProd;

    Taille(double consoProd) {
        this.consoProd = consoProd;
    }

    public double getConsoProd() {
        return consoProd;
    }
}