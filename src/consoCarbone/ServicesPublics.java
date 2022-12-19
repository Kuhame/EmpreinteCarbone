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
     *
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
    public String recommandation() {
        return "Vous pourriez demander à votre collectivité de réduire, notamment, le temps durant lequel sont allumés " +
                "les éclairages des rues ou des commerces, particulièrement le jour et la nuit après fermeture.\n" +
                "Attention : la nécessaire réduction des émissions du service public ne signifie pas moins de service public, au contraire !";
    }

    @Override
    public String toString() {
        return "Impact des services publics : " + super.toString();
    }
}
