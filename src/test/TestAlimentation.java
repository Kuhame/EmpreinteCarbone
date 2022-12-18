package test;


import consoCarbone.Alimentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAlimentation {

    private Alimentation alimentation;

    @BeforeEach
    public void initAlimentation(){
        alimentation = new Alimentation(13,12);
    }

    @Test
    public void testCalculerImpact(){
        assertEqual()
    }
}
