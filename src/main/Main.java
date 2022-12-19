package main;

import consoCarbone.*;
import utilisateur.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Alimentation

        System.out.println("Saisir votre taux de repas à base de boeuf entre 0.0 et 1.0.");
        double txBoeuf = Double.parseDouble(sc.next());
        System.out.println("Saisir votre taux de repas végétariens entre 0.0 et 1.0.");
        double txVege = Double.parseDouble(sc.next());

        Alimentation alimentation = new Alimentation(txBoeuf, txVege);

        // Habillement (Biens de consommation)

        System.out.println("Saisir le nombre de chemises que vous achetez par an.");
        int nbChemises = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de jeans que vous achetez par an.");
        int nbJeans = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de T-shirts que vous achetez par an.");
        int nbTShirts = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de pulls que vous achetez par an.");
        int nbPulls = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de manteaux que vous achetez par an.");
        int nbManteaux = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de robes que vous achetez par an.");
        int nbRobes = Integer.parseInt(sc.next());
        System.out.println("Saisir le nombre de paires de chaussres que vous achetez par an.");
        int nbChaussures = Integer.parseInt(sc.next());

        BienConso habillement = new Habillement(nbChemises, nbJeans, nbTShirts, nbPulls, nbManteaux, nbRobes, nbChaussures);

        // Logements

        System.out.println("Combien possédez-vous de logements ?");
        int nbLogements = Integer.parseInt(sc.next());

        int nbLogementsInit = 0;
        List<Logement> logements = new ArrayList<>();

        // Si pas de logement, initialiser un logement de superficie nulle (qui aura un impact nul)
        if (nbLogements == 0) {
            logements.add(new Logement(0, CE.E));
        }

        while (nbLogementsInit < nbLogements) {
            System.out.println("Saisir la superficie de votre logement n°" + (nbLogementsInit + 1) + ".");
            int superficie = Integer.parseInt(sc.next());
            System.out.println("Saisir la classe énergétique de votre logement n°" + (nbLogementsInit + 1) + ".");
            CE classeEnergetique = CE.valueOf(sc.next());

            logements.add(new Logement(superficie, classeEnergetique));
            ++nbLogementsInit;
        }

        // Transports

        System.out.println("Combien possédez-vous de voitures ?");
        int nbTransports = Integer.parseInt(sc.next());

        int nbTransportsInit = 0;
        List<Transport> transports = new ArrayList<>();

        // Si pas de transport, appeler le constructeur correspondant
        if (nbTransports == 0) {
            transports.add(new Transport());
        }

        while (nbTransportsInit < nbTransports) {
            System.out.println("Saisir la taille (P ou G) de votre voiture n°" + (nbTransportsInit + 1) + ".");
            Taille taille = Taille.valueOf(sc.next());
            System.out.println("Saisir le nombre de km parcours par an avec votre voiture n°" + (nbTransportsInit + 1) + ".");
            int kilomAnnee = Integer.parseInt(sc.next());
            System.out.println("Saisir la durée d'amortissement de votre voiture n°" + (nbTransportsInit + 1) + ".");
            int amortissement = Integer.parseInt(sc.next());

            transports.add(new Transport(true, taille, kilomAnnee, amortissement));
            ++nbTransportsInit;
        }

        // Services publics
        ServicesPublics servicesPublics = ServicesPublics.getInstance();

        // Initialisation de l'utilisateur
        Utilisateur utilisateur = new Utilisateur(alimentation, habillement, logements, transports, servicesPublics);

        // Affichage de l'empreinte carbone de l'utilisateur
        System.out.println(utilisateur.recommandations());
    }
}
