package com.lba.docker.service;

import com.lba.docker.entity.ImportSession;
import com.lba.docker.entity.ImportStatus;
import com.lba.docker.repository.ImportSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportSessionServiceTest {

    @InjectMocks
    private ImportSessionServiceImpl importSessionService;

    @Mock
    private ImportSessionRepository importSessionRepository;

    private ImportSession testSession;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testSession = new ImportSession();
        testSession.setId(1L);
        testSession.setFileName("import_file.csv");
        testSession.setStartedAt(LocalDateTime.now().minusMinutes(1));
        testSession.setFinishedAt(LocalDateTime.now());
        testSession.setStatus(ImportStatus.SUCCESS);
    }

    @Test
    public void testSaveImportSession() {
        when(importSessionRepository.save(testSession)).thenReturn(testSession);

        ImportSession saved = importSessionService.save(testSession);
        assertNotNull(saved);
        assertEquals("import_file.csv", saved.getFileName());
        assertTrue(saved.getStatus().equals(ImportStatus.SUCCESS));
    }

    @Test
    public void testFindAll() {
        when(importSessionRepository.findAll()).thenReturn(List.of(testSession));

        List<ImportSession> sessions = importSessionService.findAll();
        assertEquals(1, sessions.size());
        assertEquals("import_file.csv", sessions.get(0).getFileName());
    }
}
