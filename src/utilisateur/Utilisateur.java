package utilisateur;

import consoCarbone.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utilisateur {
    private final Alimentation alimentation;
    private final BienConso bienConso;
    private final List<Logement> logements;
    private final List<Transport> transports;
    private final ServicesPublics services;

    private double impactLogements;
    private double impactTransports;

    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Transport transport,
                       ServicesPublics services) {
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logements = new ArrayList<>();
        this.logements.add(logement);
        this.transports = new ArrayList<>();
        this.transports.add(transport);
        this.services = services;

        calculerImpactLogements();
        calculerImpactTransports();
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, List<Logement> logements, List<Transport> transports,
                       ServicesPublics services) {
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logements = logements;
        this.transports = transports;
        this.services = services;

        calculerImpactLogements();
        calculerImpactTransports();
    }

    private void calculerImpactLogements() {
        double impactLogements = 0.0;
        for (Logement l : logements) {
            impactLogements += l.getImpact();
        }
        this.impactLogements = impactLogements;
    }

    private void calculerImpactTransports() {
        double impactTransports = 0.0;
        for (Transport t : transports) {
            impactTransports += t.getImpact();
        }
        this.impactTransports = impactTransports;
    }

    public double calculerEmpreinte() {
        return alimentation.getImpact() + bienConso.getImpact() + impactLogements + impactTransports +
                services.getImpact();
    }

    /**
     * Affiche le détail de chacune des consommations de l'utilisateur
     */
    public void detaillerEmpreinte() {
        System.out.println(alimentation);
        System.out.println(bienConso);
        System.out.println("Impact du logement : " + impactLogements);
        System.out.println("Impact du logement : " + impactTransports);
        System.out.println(services);
    }

    /**
     * Affiche chaque consommation de l'utilisateur par ordre décroissant et donne des recommandations selon les
     * résultats
     */
    public void afficherRecommandations() {
        // Affichage des impacts carbone par ordre décroissant
        List<ConsoCarbone> consoCarbones  = new ArrayList<>();
        consoCarbones.add(alimentation);
        consoCarbones.add(bienConso);

        // Ne prendre que le logement d'impact le plus élevé
        Logement logement = logements.stream().max(Comparator.comparing(ConsoCarbone::getImpact)).orElse(null);
        consoCarbones.add(logement);

        // Ne prendre que le transport d'impact le plus élevé
        Transport transport = transports.stream().max(Comparator.comparing(ConsoCarbone::getImpact)).orElse(null);
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
        Logement logement2 = new Logement(200, CE.D);
        List<Logement> logements = new ArrayList<>();
        logements.add(logement);
        logements.add(logement2);

        double montant = 12345.67;
        BienConso bienConso = new BienConso(montant);

        boolean possede = true;
        Taille taille = Taille.P;
        int kilomAnnee = 1234;
        int amortissement = 10;
        Transport transport = new Transport(possede, taille, kilomAnnee, amortissement);
        Transport transport2 = new Transport(possede, Taille.G, 11037, 10);
        List<Transport> transports = new ArrayList<>();
        transports.add(transport);
        transports.add(transport2);

//        Utilisateur u = new Utilisateur(alimentation, bienConso, logement, transport, ServicesPublics.getInstance());
//        u.afficherRecommandations();
//
//        System.out.println();

        Utilisateur u2 = new Utilisateur(alimentation, bienConso, logements, transports, ServicesPublics.getInstance());
        u2.afficherRecommandations();
    }
}
