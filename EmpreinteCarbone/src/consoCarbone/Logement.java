package consoCarbone;

public class Logement extends ConsoCarbone {
    private int superficie; // m2
    private CE classeEnergetique;

    public Logement(int superficie, CE classeEnergetique) {
        this.superficie = superficie;
        this.classeEnergetique = classeEnergetique;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public CE getClasseEnergetique() {
        return classeEnergetique;
    }

    public void setClasseEnergetique(CE classeEnergetique) {
        this.classeEnergetique = classeEnergetique;
    }

    @Override
    public double impact() {
        return superficie * classeEnergetique.getCoef(); // en TCO2eq
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, les Français émettent :");
        System.out.println("- Equipement des logements : 335 kg eq CO2,");
        System.out.println("- Construction & gros entretien : 675 kg eq CO2,");
        System.out.println("- Energie et utilités : 1 696 kg eq CO2.");
    }

    public static void main(String[] args) {
        ConsoCarbone cl = new Logement(17, CE.C);
        ConsoCarbone ca = new Alimentation(1.0, 0.0);

        Logement.afficherEmpreinteMoyenne();
        System.out.println(cl.impact());

        Alimentation.afficherEmpreinteMoyenne();
        System.out.println(ca.impact());
    }
}
