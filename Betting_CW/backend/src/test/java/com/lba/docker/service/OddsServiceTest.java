package com.lba.docker.service;

import com.lba.docker.entity.Odds;
import com.lba.docker.repository.OddsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class OddsServiceTest {

    @InjectMocks
    private OddsServiceImpl oddsService;

    @Mock
    private OddsRepository oddsRepository;

    private Odds testOdds;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testOdds = new Odds();
        testOdds.setId(1L);
        testOdds.setValue(2.5);
    }

    @Test
    public void testCreateOdds() {
        when(oddsRepository.save(testOdds)).thenReturn(testOdds);

        Odds createdOdds = oddsService.createOdds(testOdds);
        assertEquals(testOdds.getValue(), createdOdds.getValue());
    }

    @Test
    public void testGetOddsByEvent() {
        when(oddsRepository.findByEventId(1L)).thenReturn(List.of(testOdds));

        List<Odds> odds = oddsService.getOddsByEvent(1L);
        assertFalse(odds.isEmpty());
    }

    @Test
    public void testGetOddsByValue() {
        when(oddsRepository.findByValue(2.5)).thenReturn(testOdds);

        Optional<Odds> foundOdds = oddsService.getOddsByValue(2.5);
        assertTrue(foundOdds.isPresent());
        assertEquals(2.5, foundOdds.get().getValue());
    }
}
