package be.vdab.web;

import be.vdab.entities.Filiaal;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.Adres;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
public class FiliaalControllerTest {
    private FiliaalController filiaalController;
    private List<Filiaal> filialen;
    private FiliaalService filiaalService;
    private Filiaal filiaal;

    @Before
    public void before() {
        filialen = Collections.emptyList();
        filiaalService = mock(FiliaalService.class);
        when(filiaalService.findAll()).thenReturn(filialen);
        filiaalController = new FiliaalController(filiaalService);
        filiaal = new Filiaal("naam1", true, BigDecimal.ONE, LocalDate.now(),
                new Adres("straat1", "huisnr1", 1, "gemeente1"));
        when(filiaalService.read(1)).thenReturn(Optional.of(filiaal));
    }

    @Test
    public void findAllActiveertJuisteView() {
        assertEquals("filialen/filialen",
                filiaalController.findAll().getViewName());
    }

    @Test
    public void findAllMaaktRequestAttribuutFilialen() {
        assertSame(filialen,
                filiaalController.findAll().getModelMap().get("filialen"));
    }

    @Test
    public void readActiveertJuisteView() {
        assertEquals("filialen/filiaal", filiaalController.read(1L).getViewName());
    }

    @Test
    public void readMetBestaandeIDGeeftFiliaalTerug() {
        assertSame(filiaal,
                filiaalController.read(1L).getModelMap().get("filiaal"));
    }

    @Test(expected = NullPointerException.class)
    public void readMetOnbestaandeIDGeeftNullTerug() {
        assertNull(filiaalController.read(666L).getModelMap().get("filiaal"));
    }
}