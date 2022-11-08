package consoCarbone;

public class Alimentation extends ConsoCarbone {
    private final double txBoeuf; // entre 0 et 1
    private final double txVege;

    private static final double RATIO_BOEUF = 8.0;
    private static final double RATIO_VOLAILLE = 1.6;
    private static final double RATIO_VEGE = 0.9;

    public Alimentation(double txBoeuf, double txVege) {
        super();

        assert (txBoeuf >= 0.0 && txBoeuf <= 1.0);
        assert (txVege >= 0.0 && txVege <= 1.0);

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
    public String toString() {
        return "Impact de l'alimentation : " + super.getImpact();
    }
}
