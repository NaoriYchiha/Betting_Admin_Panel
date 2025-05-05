package com.lba.docker.service;

import com.lba.docker.entity.ChangeLog;
import java.util.List;

public interface ChangeLogService {
    ChangeLog save(ChangeLog changeLog);
    List<ChangeLog> findByEventId(Long eventId);
    List<ChangeLog> findByUserId(Long userId);
    List<ChangeLog> findAll();
}
