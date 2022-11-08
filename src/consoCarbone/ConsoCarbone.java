package consoCarbone;

public abstract class ConsoCarbone {
    private static int compteurId = 0;
    private final int id;

    private double impact;

    public ConsoCarbone() {
        this.id = compteurId++;
    }

    public abstract void calculerImpact();

    public double getImpact() {
        return impact;
    }

    public int getId() {
        return id;
    }

    // Package-private pour garantir l'encapsulation
    void setImpact(double impact) {
        this.impact = impact;
    }
}
