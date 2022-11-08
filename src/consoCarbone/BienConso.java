package consoCarbone;

public class BienConso extends ConsoCarbone {
    private final double montant;
    private static final int DEPENSES_T_CO2_EQ = 1750; // 1 TCO2eq = 1750€

    public BienConso(double montant) {
        super();

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
    public String toString() {
        return "Impact du bien de consommation : " + super.getImpact();
    }
}
