package consoCarbone;

public class BienConso extends ConsoCarbone {
    private final double montant;
    private static final int DEPENSES_T_CO2_EQ = 1750; // 1 TCO2eq = 1750€

    /**
     * Initialise un bien de consommation
     * @param montant Le montant du bien en euros
     */
    public BienConso(double montant) {
        super();

        if (montant < 0.0) {
            throw new IllegalArgumentException("Le montant des biens de consommation doit être supérieur ou égal à 0.0.");
        }

        this.montant = montant;
        calculerImpact();
    }

    public double getMontant() {
        return montant;
    }

    @Override
    public void calculerImpact() {
        double impact = montant / DEPENSES_T_CO2_EQ;
        super.setImpact(impact);
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, un Français émet :");
        System.out.println("- Habillement : 763 kg eq CO2,");
        System.out.println("- Autres Biens et Services : 682 kg eq CO2,");
        System.out.println("- Achat et usages Internet et technologies : 1 180 kg eq CO2.");
    }

    @Override
    public String recommandation() {
        return "Vous devriez dépenser moins.";
    }

    @Override
    public String toString() {
        return "Impact du bien de consommation : " + super.getImpact();
    }
}
