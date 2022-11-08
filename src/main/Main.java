package main;

import consoCarbone.*;

public class Main {
    public static void main(String[] args) {
        // ALIMENTATION

        double txBoeuf = 0.8;
        double txVege = 0.2;

        Alimentation alimentation = new Alimentation(txBoeuf, txVege);

        assert(alimentation.getTxBoeuf() == txBoeuf);
        assert(alimentation.getTxVege() == txVege);

        System.out.println("===== ALIMENTATION =====\n");
        Alimentation.afficherEmpreinteMoyenne();
        System.out.println("Impact attendu : " + (8 * 0.8 + 1.6 * (1 - 0.2 - 0.8) + 0.9 * 0.2));
        System.out.println("Impact obtenu : " + alimentation.getImpact());
        System.out.println();

        // LOGEMENT

        int superficie = 17;
        CE classeEnergetique = CE.C;

        Logement logement = new Logement(superficie, classeEnergetique);

        assert(logement.getSuperficie() == superficie);
        assert(logement.getClasseEnergetique() == classeEnergetique);

        System.out.println("===== LOGEMENT =====\n");
        Logement.afficherEmpreinteMoyenne();
        System.out.println("Impact attendu : " + 17 * 0.02);
        System.out.println("Impact obtenu : " + logement.getImpact());
        System.out.println();

        // BIEN CONSO

        double montant = 12345.67;

        BienConso bienConso = new BienConso(montant);

        assert(bienConso.getMontant() == montant);

        System.out.println("===== BIEN CONSO =====\n");
        BienConso.afficherEmpreinteMoyenne();
        System.out.println("Impact attendu : " + 12345.67 / 1750);
        System.out.println("Impact obtenu : " + bienConso.getImpact());
        System.out.println();

        // TRANSPORT

        boolean possede = true;
        Taille taille = Taille.G;
        int kilomAnnee = 2341;
        int amortissement = 8;

        Transport transport = new Transport(possede, taille, kilomAnnee, amortissement);

        assert(transport.isPossede() == possede);
        assert(transport.getTaille() == taille);
        assert(transport.getKilomAnnee() == kilomAnnee);
        assert(transport.getAmortissement() == amortissement);

        System.out.println("===== TRANSPORT =====\n");
        Transport.afficherEmpreinteMoyenne();
        System.out.println("Impact attendu : " + (2341 * 1.93 * Math.pow(10, -4) + 19.0 / 8));
        System.out.println("Impact obtenu : " + transport.getImpact());
        System.out.println();

        // Polymorphisme

        System.out.println("===== POLYMORPHISME =====\n");

        ConsoCarbone al = new Alimentation(txBoeuf, txVege);
        System.out.println(al);
        ConsoCarbone lg = new Logement(superficie, classeEnergetique);
        System.out.println(lg);
        ConsoCarbone bc = new BienConso(montant);
        System.out.println(bc);
        ConsoCarbone tr = new Transport(possede, taille, kilomAnnee, amortissement);
        System.out.println(tr);

    }
}
