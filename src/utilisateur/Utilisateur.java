package utilisateur;

import consoCarbone.*;

import java.io.*;
import java.util.*;

public class Utilisateur {
    private final Alimentation alimentation;
    private final BienConso bienConso;
    private final List<Logement> logements;
    private final List<Transport> transports;
    private final ServicesPublics servicesPublics;

    private double impactLogements;
    private double impactTransports;

    /**
     * Constructeur pour logement et voiture uniques
     * @param alimentation Alimentation de l'utilisateur
     * @param bienConso Biens de consommation de l'utilisateur
     * @param logement Logement unique de l'utilisateur
     * @param transport Voiture unique de l'utilisateur
     * @param servicesPublics Services publics
     */
    public Utilisateur(Alimentation alimentation, BienConso bienConso, Logement logement, Transport transport,
                       ServicesPublics servicesPublics) {
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logements = new ArrayList<>();
        this.logements.add(logement);
        this.transports = new ArrayList<>();
        this.transports.add(transport);
        this.servicesPublics = servicesPublics;

        calculerImpactLogements();
        calculerImpactTransports();
    }

    public Utilisateur(Alimentation alimentation, BienConso bienConso, List<Logement> logements, List<Transport> transports,
                       ServicesPublics servicesPublics) {
        this.alimentation = alimentation;
        this.bienConso = bienConso;
        this.logements = logements;
        this.transports = transports;
        this.servicesPublics = servicesPublics;

        calculerImpactLogements();
        calculerImpactTransports();
    }

    /**
     * Initialisation par fichier
     * @param fichier Le fichier texte
     */
    public Utilisateur(String fichier) {
        // Lecture de toutes les lignes du fichier pour parsing 1 à 1
        LinkedList<String> entrees = new LinkedList<>();
        try {
            FileReader file = new FileReader(fichier);
            BufferedReader br = new BufferedReader(file);

            String line = br.readLine();
            while (line != null) {
                entrees.add(line);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Problème de lecture du fichier.");
            System.exit(1);
        }

        // Alimentation
        double txBoeuf = Double.parseDouble(entrees.pop());
        double txVege = Double.parseDouble(entrees.pop());
        alimentation = new Alimentation(txBoeuf, txVege);

        // Biens de consommation
        double montant = Double.parseDouble(entrees.pop());
        bienConso = new BienConso(montant);

        // Logements

        int nbLogements = Integer.parseInt(entrees.pop());

        int nbLogementsInit = 0;
        logements = new ArrayList<>();

        // Si pas de logement, initialiser un logement de superficie nulle (qui aura un impact nul)
        if (nbLogements == 0) {
            logements.add(new Logement(0, CE.E));
        }

        while (nbLogementsInit < nbLogements) {
            int superficie = Integer.parseInt(entrees.pop());
            CE classeEnergetique = CE.valueOf(entrees.pop());
            logements.add(new Logement(superficie, classeEnergetique));
            ++nbLogementsInit;
        }

        // Transports

        int nbTransports = Integer.parseInt(entrees.pop());

        int nbTransportsInit = 0;
        transports = new ArrayList<>();

        // Si pas de transport, appeler le constructeur correspondant
        if (nbTransports == 0) {
            transports.add(new Transport());
        }

        while (nbTransportsInit < nbTransports) {
            Taille taille = Taille.valueOf(entrees.pop());
            int kilomAnnee = Integer.parseInt(entrees.pop());
            int amortissement = Integer.parseInt(entrees.pop());
            transports.add(new Transport(true, taille, kilomAnnee, amortissement));
            ++nbTransportsInit;
        }

        servicesPublics = ServicesPublics.getInstance();
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
                servicesPublics.getImpact();
    }

    /**
     * Affiche le détail de chacune des consommations de l'utilisateur
     */
    public void detaillerEmpreinte() {
        System.out.println(alimentation);
        System.out.println(bienConso);
        System.out.println("Impact du logement : " + impactLogements);
        System.out.println("Impact du logement : " + impactTransports);
        System.out.println(servicesPublics);
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

        consoCarbones.add(servicesPublics);

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
        new Utilisateur("utilisateur_txt/utilisateur2.txt").afficherRecommandations();
    }
}
