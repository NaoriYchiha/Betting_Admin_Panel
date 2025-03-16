package com.lba.docker.service;

import com.lba.docker.entity.SportsEvent;
import com.lba.docker.repository.SportsEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class SportsEventServiceTest {

    @InjectMocks
    private SportsEventServiceImpl sportsEventService;

    @Mock
    private SportsEventRepository sportsEventRepository;

    private SportsEvent testEvent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testEvent = new SportsEvent();
        testEvent.setId(1L);
        testEvent.setName("Football Match");
    }

    @Test
    public void testCreateEvent() {
        when(sportsEventRepository.save(testEvent)).thenReturn(testEvent);

        SportsEvent createdEvent = sportsEventService.createEvent(testEvent);
        assertEquals(testEvent.getName(), createdEvent.getName());
    }

    @Test
    public void testGetEventById() {
        when(sportsEventRepository.findById(1L)).thenReturn(Optional.of(testEvent));

        Optional<SportsEvent> foundEvent = sportsEventService.getEventById(1L);
        assertTrue(foundEvent.isPresent());
        assertEquals("Football Match", foundEvent.get().getName());
    }
}

