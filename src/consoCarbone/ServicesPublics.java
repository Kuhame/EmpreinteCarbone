package consoCarbone;

/**
 * Classe Singleton représentant la consommation fixe des services publics en France
 */
public class ServicesPublics extends ConsoCarbone {
    private static final double impact = 1.5;
    private static ServicesPublics instance = null;

    private ServicesPublics() {
        super();
        calculerImpact();
    }

    /**
     * Crée une instance unique de la classe si elle n'existe pas et la renvoie
     * @return Le singleton de la classe
     */
    public static ServicesPublics getInstance() {
        if (instance == null) {
            instance = new ServicesPublics();
        }
        return instance;
    }

    @Override
    public void calculerImpact() {
        super.setImpact(impact);
    }

    @Override
    public String toString() {
        return "Impact des services publics : " + super.getImpact();
    }
}
