package com.lba.docker.service;

import com.lba.docker.entity.ExportSession;
import com.lba.docker.entity.User;
import com.lba.docker.repository.ExportSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExportSessionServiceTest {

    @InjectMocks
    private ExportSessionServiceImpl exportSessionService;

    @Mock
    private ExportSessionRepository exportSessionRepository;

    private ExportSession testSession;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("admin");

        testSession = new ExportSession();
        testSession.setId(1L);
        testSession.setExportedAt(LocalDateTime.now());
        testSession.setExportedBy(mockUser);
    }

    @Test
    public void testSaveExportSession() {
        when(exportSessionRepository.save(testSession)).thenReturn(testSession);

        ExportSession saved = exportSessionService.save(testSession);
        assertNotNull(saved);
        assertEquals("admin", saved.getExportedBy().getUsername());
    }

    @Test
    public void testFindAll() {
        when(exportSessionRepository.findAll()).thenReturn(List.of(testSession));

        List<ExportSession> sessions = exportSessionService.findAll();
        assertEquals(1, sessions.size());
        assertEquals(1L, sessions.get(0).getExportedBy().getId());
    }
}
