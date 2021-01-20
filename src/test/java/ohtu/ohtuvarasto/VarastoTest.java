package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp()
    {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktori2LuoHuononVaraston() {
        Varasto varasto2 = new Varasto(0,-1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2YlijaamaMeneehukkaan() {
        Varasto varasto2 = new Varasto(10, 15);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2SaldoMeneeOikeinJosVahemman() {
        Varasto varasto2 = new Varasto(20,5);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusNollataan() {
        Varasto varasto2 = new Varasto(-5);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);

    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonEiLisaaNegatiivisilla() {
        double alku = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(alku, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonEiMeneYli() {
        varasto.lisaaVarastoon(Double.POSITIVE_INFINITY);
        assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaEiOtaNegatiivisilla() {
        double alku = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(varasto.getSaldo(), alku, vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaOttaaKaikenMitaVoi() {
        varasto.lisaaVarastoon(5);
        double loput = varasto.otaVarastosta(6);

        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
        assertEquals(loput, 5, vertailuTarkkuus);
    }

    @Test
    public void toStringPalauttaaOikeanMerkkijonon() {
        varasto.lisaaVarastoon(1);
        assertEquals("saldo = 1., vielä tilaa 9.0", varasto.toString());
    }
}