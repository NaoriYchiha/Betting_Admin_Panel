package com.lba.docker.service;

import com.lba.docker.entity.ChangeLog;
import com.lba.docker.entity.User;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.repository.ChangeLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ChangeLogServiceTest {

    @InjectMocks
    private ChangeLogServiceImpl changeLogService;

    @Mock
    private ChangeLogRepository changeLogRepository;

    private ChangeLog testChangeLog;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");

        SportsEvent event = new SportsEvent();
        event.setId(10L);
        event.setName("Test Event");

        testChangeLog = new ChangeLog();
        testChangeLog.setId(100L);
        testChangeLog.setChangedBy(user);
        testChangeLog.setEvent(event);
        testChangeLog.setFieldName("odds");
        testChangeLog.setOldValue("1.5");
        testChangeLog.setNewValue("2.0");
        testChangeLog.setChangedAt(LocalDateTime.now());
    }

    @Test
    public void testFindByEventId() {
        when(changeLogRepository.findByEvent_Id(10L)).thenReturn(List.of(testChangeLog));

        List<ChangeLog> logs = changeLogService.findByEventId(10L);
        assertEquals(1, logs.size());
        assertEquals("odds", logs.get(0).getFieldName());
    }

    @Test
    public void testFindByUserId() {
        when(changeLogRepository.findByChangedBy_Id(1L)).thenReturn(List.of(testChangeLog));

        List<ChangeLog> logs = changeLogService.findByUserId(1L);
        assertFalse(logs.isEmpty());
        assertEquals("admin", logs.get(0).getChangedBy().getUsername());
    }

    @Test
    public void testSaveChangeLog() {
        when(changeLogRepository.save(testChangeLog)).thenReturn(testChangeLog);

        ChangeLog saved = changeLogService.save(testChangeLog);
        assertNotNull(saved);
        assertEquals("odds", saved.getFieldName());
    }
}
