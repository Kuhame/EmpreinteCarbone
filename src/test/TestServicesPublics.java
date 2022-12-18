package test;

import consoCarbone.ServicesPublics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TestServicesPublics {
    private ServicesPublics servicesPublics;

    @BeforeEach
    public void initServicesPublics() {
        servicesPublics = ServicesPublics.getInstance();
    }

    @Test
    public void testGetInstance() {
        ServicesPublics newServicesPublics = ServicesPublics.getInstance();
        assertSame(servicesPublics, newServicesPublics);
    }

    @Test
    public void testGetImpact() {
        assertEquals(servicesPublics.getImpact(), 1.5);
    }
}
