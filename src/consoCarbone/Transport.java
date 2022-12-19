package consoCarbone;

public class Transport extends ConsoCarbone {
    private final boolean possede; // si utilisateur possède une voiture
    private final Taille taille;
    private final int kilomAnnee; // nb de km parcourus en 1 an
    private final int amortissement; // durée de conservation du véhicule


    private static final double RATIO_IMPACT = 1.93 * Math.pow(10, -4);

    /**
     * Initialise un nouveau moyen de transport
     *
     * @param possede       Si l'utilisateur possède une voiture
     * @param taille        La taille du véhicule le cas échéant
     * @param kilomAnnee    Le nombre de km parcourus par an le cas échéant
     * @param amortissement La durée de conservation le cas échéant
     */
    public Transport(boolean possede, Taille taille, int kilomAnnee, int amortissement) {
        super();

        if (kilomAnnee < 0) {
            throw new IllegalArgumentException("Le nombre de km parcourus par an avec la voiture doit être supérieur ou égal à 0.");
        }

        if (amortissement < 0) {
            throw new IllegalArgumentException("Le nombre d'années d'amortissement de la voiture doit être supérieur ou égal à 0.");
        }

        this.possede = possede;
        this.taille = taille;
        this.kilomAnnee = kilomAnnee;
        this.amortissement = amortissement;
        calculerImpact();
    }

    /**
     * Constructeur pour Utilisateur n'ayant pas de voiture
     */
    public Transport() {
        this.possede = false;
        this.taille = null;
        this.kilomAnnee = 0;
        this.amortissement = 0;
        calculerImpact();
    }

    @Override
    public void calculerImpact() {
        if (!possede) {
            super.setImpact(0);
        } else {
            double impact = kilomAnnee * RATIO_IMPACT + taille.getConsoProd() / amortissement;
            super.setImpact(impact);
        }
    }

    public boolean isPossede() {
        return possede;
    }

    public Taille getTaille() {
        return taille;
    }

    public int getKilomAnnee() {
        return kilomAnnee;
    }

    public int getAmortissement() {
        return amortissement;
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, un Français émet :");
        System.out.println("- Fret et messagerie : 383 kg eq CO2,");
        System.out.println("- Avion : 580 kg eq CO2,");
        System.out.println("- Voiture : 1 972 kg eq CO2.");
    }

    @Override
    public String recommandation() {
        if (taille == Taille.G) {
            return "Essayez de changer de voiture pour un plus petit modèle.";
        }
        return "Avez-vous envisagé de passer à la voiture électrique ?";
    }

    @Override
    public String toString() {
        return "Impact du transport : " + super.getImpact();
    }
}
