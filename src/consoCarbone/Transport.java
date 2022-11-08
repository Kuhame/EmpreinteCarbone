package consoCarbone;

public class Transport extends ConsoCarbone {
    private boolean possede; // utilisateur possède une voiture ?
    private Taille taille;
    private int kilomAnnee; // nb de km parcourus en 1 an
    private int amortissement; // durée de conservation du véhicule

    public Transport(boolean possede, Taille taille, int kilomAnnee, int amortissement) {
        this.possede = possede;
        this.taille = taille;
        this.kilomAnnee = kilomAnnee;
        this.amortissement = amortissement;
    }

    @Override
    public double impact() {
        if (!possede) return 0.0;

        // TODO nb magique ?
        return kilomAnnee * 1.93 * Math.pow(10, -4) + taille.getCoef() / amortissement;
    }

    public boolean isPossede() {
        return possede;
    }

    public void setPossede(boolean possede) {
        this.possede = possede;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public int getKilomAnnee() {
        return kilomAnnee;
    }

    public void setKilomAnnee(int kilomAnnee) {
        this.kilomAnnee = kilomAnnee;
    }

    public int getAmortissement() {
        return amortissement;
    }

    public void setAmortissement(int amortissement) {
        this.amortissement = amortissement;
    }

    public static void afficherEmpreinteMoyenne() {
        System.out.println("En moyenne, chaque année, les Français émettent :");
        System.out.println("- Fret et messagerie : 383 kg eq CO2,");
        System.out.println("- Avion : 580 kg eq CO2,");
        System.out.println("- Voiture : 1 972 kg eq CO2.");
    }
}
