package utilisateur;

import consoCarbone.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Utilisateur {
    private final Alimentation alimentation;
    private final BienConso bienConso;
    private final Logement logement;
    private final Transport transport;
    private final ServicesPublics services;

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Transport transport,
                       ServicesPublics services) {
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logement = logement;
        this.transport = transport;
        this.services = services;
    }

    public double calculerEmpreinte() {
        return alimentation.getImpact() + bienConso.getImpact() + logement.getImpact() + transport.getImpact() +
                services.getImpact();
    }

    /**
     * Affiche le détail de chacune des consommations de l'utilisateur
     */
    public void detaillerEmpreinte() {
        System.out.println(alimentation);
        System.out.println(bienConso);
        System.out.println(logement);
        System.out.println(transport);
        System.out.println(services);
    }

    /**
     * Affiche chaque consommation de l'utilisateur par ordre décroissant et donne des recommandations selon les
     * résultats
     */
    public void recommandations() {
        // Affichage des impacts carbone par ordre décroissant
        List<ConsoCarbone> consoCarbones  = new ArrayList<>();
        consoCarbones.add(alimentation);
        consoCarbones.add(bienConso);
        consoCarbones.add(logement);
        consoCarbones.add(transport);
        consoCarbones.add(services);

        Collections.sort(consoCarbones);
        Collections.reverse(consoCarbones);

        StringBuilder sb = new StringBuilder();
        for (ConsoCarbone consommation : consoCarbones) {
            sb.append(consommation);
            sb.append(System.lineSeparator());
        }
        System.out.println(sb);

        // Recommandations
        for (int i = 0; i < 3; ++i) {
            System.out.println(consoCarbones.get(i).recommandation());
        }
    }

    public static void main(String[] args) {
        double txBoeuf = 0.8;
        double txVege = 0.2;
        Alimentation alimentation = new Alimentation(txBoeuf, txVege);

        int superficie = 17;
        CE classeEnergetique = CE.C;
        Logement logement = new Logement(superficie, classeEnergetique);

        double montant = 12345.67;
        BienConso bienConso = new BienConso(montant);

        boolean possede = true;
        Taille taille = Taille.G;
        int kilomAnnee = 2341;
        int amortissement = 8;
        Transport transport = new Transport(possede, taille, kilomAnnee, amortissement);

        Utilisateur u = new Utilisateur(alimentation, bienConso, logement, transport, ServicesPublics.getInstance());

        u.recommandations();
    }
}
