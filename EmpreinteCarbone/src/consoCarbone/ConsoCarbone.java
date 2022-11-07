package consoCarbone;

public abstract class ConsoCarbone {
    private static int compteurId = 0;
    private int id;

    public ConsoCarbone() {
        this.id = compteurId++;
    }

    public abstract double impact();

    @Override
    public String toString() {
        // TODO meilleur affichage que juste la valeur de l'impact
        return Double.toString(impact());
    }
}
