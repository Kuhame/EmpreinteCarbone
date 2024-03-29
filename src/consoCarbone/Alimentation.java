package consoCarbone;

public class Alimentation extends ConsoCarbone {
    private final double txBoeuf; // entre 0 et 1
    private final double txVege;

    private static final double RATIO_BOEUF = 8.0;
    private static final double RATIO_VOLAILLE = 1.6;
    private static final double RATIO_VEGE = 0.9;

    /**
     * Initialise une alimentation
     *
     * @param txBoeuf La proportion de consommation de repas à base de boeuf [0.0, 1.0]
     * @param txVege  La proportion de consommation de repas végétariens [0.0, 1.0]
     */
    public Alimentation(double txBoeuf, double txVege) {
        super();

        if (!(txBoeuf >= 0.0 && txBoeuf <= 1.0)) {
            throw new IllegalArgumentException("Le taux de repas à base de boeuf doit être compris entre 0.0 et 1.0");
        }
        if (!(txVege >= 0.0 && txVege <= 1.0)) {
            throw new IllegalArgumentException("Le taux de repas végétariens doit être compris entre 0.0 et 1.0");
        }

        this.txBoeuf = txBoeuf;
        this.txVege = txVege;
        calculerImpact();
    }

    public double getTxBoeuf() {
        return txBoeuf;
    }

    public double getTxVege() {
        return txVege;
    }

    @Override
    public void calculerImpact() {
        double impact = RATIO_BOEUF * txBoeuf + RATIO_VOLAILLE * (1 - txVege - txBoeuf) + RATIO_VEGE * txVege;
        super.setImpact(impact);
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, un Français émet :");
        System.out.println("- Viandes et Poissons : 1 144 kg eq CO2,");
        System.out.println("- Produits laitiers et Oeufs : 408 kg eq CO2,");
        System.out.println("- Boissons : 263 kg eq CO2,");
        System.out.println("- Autres : 538 kg eq CO2.");
    }

    @Override
    public String recommandation() {
        if (txBoeuf >= 0.6) {
            return "Vous devriez songer à manger moins de viande.";
        }
        return "Tout en minimisant votre consommation de viande, vous pourriez vous orienter vers des produits locaux.";
    }

    @Override
    public String toString() {
        return "Impact de l'alimentation : " + super.toString();
    }
}
