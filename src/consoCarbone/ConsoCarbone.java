package consoCarbone;

public abstract class ConsoCarbone implements Comparable<ConsoCarbone> {
    private static int compteurId = 0;
    private final int id;

    private double impact;

    public ConsoCarbone() {
        this.id = compteurId++;
    }

    /**
     * Calcule l'impact d'une consommation carbonne en fonction de ses caractéristiques
     */
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

    @Override
    public int compareTo(ConsoCarbone o) {
        return (int) (impact - o.impact);
    }
}
