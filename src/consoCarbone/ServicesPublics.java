package consoCarbone;

public class ServicesPublics extends ConsoCarbone {
    private static final double impact = 1.5;
    private static ServicesPublics instance = null;

    private ServicesPublics() {
        super();
        calculerImpact();
    }

    public static ServicesPublics getInstance() {
        if (instance == null) {
            instance = new ServicesPublics();
        }
        return instance;
    }

    @Override
    public void calculerImpact() {
        super.setImpact(impact);
    }
}
