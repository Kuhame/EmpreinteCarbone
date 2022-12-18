package utilisateur;

import consoCarbone.*;

public class Utilisateur {
    private Alimentation alimentation;
    private BienConso bienConso;
    private Logement logement;
    private Transport transport;
    private ServicesPublics services;

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

    public void detaillerEmpreinte() {
        System.out.println(alimentation);
        System.out.println(bienConso);
        System.out.println(logement);
        System.out.println(transport);
        System.out.println(services);
    }
}
