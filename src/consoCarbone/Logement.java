package consoCarbone;

public class Logement extends ConsoCarbone {
    private final int superficie; // m2
    private final CE classeEnergetique;

    /**
     * Initialise un logement
     * @param superficie La superficie du logement en m²
     * @param classeEnergetique La classe énergétique du logement
     */
    public Logement(int superficie, CE classeEnergetique) {
        super();

        this.superficie = superficie;
        this.classeEnergetique = classeEnergetique;
        calculerImpact();
    }

    public int getSuperficie() {
        return superficie;
    }

    public CE getClasseEnergetique() {
        return classeEnergetique;
    }

    @Override
    public void calculerImpact() {
        double impact = superficie * classeEnergetique.getCoef();
        super.setImpact(impact); // en TCO2eq
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, un Français émet :");
        System.out.println("- Equipement des logements : 335 kg eq CO2,");
        System.out.println("- Construction & gros entretien : 675 kg eq CO2,");
        System.out.println("- Energie et utilités : 1 696 kg eq CO2.");
    }

    @Override
    public String toString() {
        return "Impact du logement : " + super.getImpact();
    }
}
