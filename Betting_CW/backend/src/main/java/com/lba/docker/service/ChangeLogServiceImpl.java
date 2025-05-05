package com.lba.docker.service;

import com.lba.docker.entity.ChangeLog;
import com.lba.docker.repository.ChangeLogRepository;
import com.lba.docker.service.ChangeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangeLogServiceImpl implements ChangeLogService {

    private final ChangeLogRepository changeLogRepository;

    @Override
    public ChangeLog save(ChangeLog changeLog) {
        return changeLogRepository.save(changeLog);
    }

    @Override
    public List<ChangeLog> findByEventId(Long eventId) {
        return changeLogRepository.findByEvent_Id(eventId);
    }

    @Override
    public List<ChangeLog> findByUserId(Long userId) {
        return changeLogRepository.findByChangedBy_Id(userId);
    }

    @Override
    public List<ChangeLog> findAll() {
        return changeLogRepository.findAll();
    }
}

