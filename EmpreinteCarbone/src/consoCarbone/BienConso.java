package consoCarbone;

public class BienConso extends ConsoCarbone {
    private double montant;
    private static final int DEPENSES_T_CO2_EQ = 1750; // 1 TCO2eq = 1750€

    public BienConso(double montant) {
        this.montant = montant;
    }

    @Override
    public double impact() {
        return montant / DEPENSES_T_CO2_EQ;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, les Français émettent :");
        System.out.println("- Habillement : 763 kg eq CO2,");
        System.out.println("- Autres Biens et Services : 682 kg eq CO2,");
        System.out.println("- Achat et usages Internet et technologies : 1 180 kg eq CO2.");
    }

}
