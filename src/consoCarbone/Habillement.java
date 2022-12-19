package consoCarbone;

public class Habillement extends BienConso {
    private static final double PRIX_CHEMISE = 30.0;
    private static final double PRIX_JEAN = 40.0;
    private static final double PRIX_T_SHIRT = 20.0;
    private static final double PRIX_PULL = 40.0;
    private static final double PRIX_MANTEAU = 80.0;
    private static final double PRIX_ROBE = 50.0;
    private static final double PRIX_CHAUSSURES = 100.0;

    private final int nbChemises;
    private final int nbJeans;
    private final int nbTShirts;
    private final int nbPulls;
    private final int nbManteaux;
    private final int nbRobes;
    private final int nbChaussures;;

    public Habillement(int nbChemises, int nbJeans, int nbTShirts, int nbPulls, int nbManteaux, int nbRobes,
                       int nbChaussures) {
        super(0);

        this.nbChemises = nbChemises;
        this.nbJeans = nbJeans;
        this.nbTShirts = nbTShirts;
        this.nbPulls = nbPulls;
        this.nbManteaux = nbManteaux;
        this.nbRobes = nbRobes;
        this.nbChaussures = nbChaussures;
        calculerMontant();
    }

    private void calculerMontant() {
        double total = 0.0;
        total += nbChemises * PRIX_CHEMISE;
        total += nbJeans * PRIX_JEAN;
        total += nbTShirts * PRIX_T_SHIRT;
        total += nbPulls * PRIX_PULL;
        total += nbManteaux * PRIX_MANTEAU;
        total += nbRobes * PRIX_ROBE;
        total += nbChaussures * PRIX_CHAUSSURES;

        super.setMontant(total);
    }

    @Override
    public String recommandation() {
        return "Pour protéger la planète, restreignez votre garde-robe au strict nécessaire.";
    }

    @Override
    public String toString() {
        return "Impact de l'habillement : " + super.getImpact();
    }
}
