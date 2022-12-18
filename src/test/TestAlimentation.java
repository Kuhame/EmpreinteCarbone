package test;

import consoCarbone.Alimentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlimentation {
    private double txBoeuf;
    private double txVege;
    private Alimentation alimentation;

    @BeforeEach
    public void initAlimentation() {
        txBoeuf = 0.3;
        txVege = 0.4;
        alimentation = new Alimentation(txBoeuf, txVege);
    }

    @Test
    public void testGetTxBoeuf() {
        assertEquals(alimentation.getTxBoeuf(), txBoeuf);
    }

    @Test
    public void testGetTxVege() {
        assertEquals(alimentation.getTxVege(), txVege);
    }

    @Test
    public void testCalculerImpact() {
        double RATIO_BOEUF = 8.0;
        double RATIO_VOLAILLE = 1.6;
        double RATIO_VEGE = 0.9;
        double impact = RATIO_BOEUF * txBoeuf + RATIO_VOLAILLE * (1 - txVege - txBoeuf) + RATIO_VEGE * txVege;

        alimentation.calculerImpact();
        assertEquals(alimentation.getImpact(), impact);
    }
}
