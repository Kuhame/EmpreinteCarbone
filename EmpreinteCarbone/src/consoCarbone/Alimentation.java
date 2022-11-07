package consoCarbone;

public class Alimentation extends ConsoCarbone {
    private double txBoeuf; // entre 0 et 1
    private double txVege;
    //TODO impact en attribut ?
    private static final double RATIO_BOEUF = 8.0;
    private static final double RATIO_VOLAILLE = 1.6;
    private static final double RATIO_VEGE = 0.9;

    public Alimentation(double txBoeuf, double txVege) {
        assert (txBoeuf >= 0.0 && txBoeuf <= 1.0);
        assert (txVege >= 0.0 && txVege <= 1.0);
        this.txBoeuf = txBoeuf;
        this.txVege = txVege;
    }

    public double getTxBoeuf() {
        return txBoeuf;
    }

    public double getTxVege() {
        return txVege;
    }

    @Override
    public double impact() {
        return txBoeuf * RATIO_BOEUF + (1 - txBoeuf - txVege) * RATIO_VOLAILLE + txVege * RATIO_VEGE;
    }

    public void setTxBoeuf(double txBoeuf) {
        this.txBoeuf = txBoeuf;
    }

    public void setTxVege(double txVege) {
        this.txVege = txVege;
    }


    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, les Français émettent :");
        System.out.println("- Viandes et Poissons : 1 144 kg eq CO2,");
        System.out.println("- Produits laitiers et Oeufs : 408 kg eq CO2,");
        System.out.println("- Boissons : 263 kg eq CO2,");
        System.out.println("- Autres : 538 kg eq CO2.");
    }

}
