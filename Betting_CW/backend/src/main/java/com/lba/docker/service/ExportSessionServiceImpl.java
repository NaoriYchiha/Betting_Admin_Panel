package com.lba.docker.service;


import com.lba.docker.entity.ExportSession;
import com.lba.docker.repository.ExportSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportSessionServiceImpl implements ExportSessionService {
    @Autowired
    private ExportSessionRepository exportSessionRepository;

    @Override
    public ExportSession save(ExportSession exportSession) {
        return exportSessionRepository.save(exportSession);
    }

    @Override
    public List<ExportSession> findByUserId(Long userId) {
        return exportSessionRepository.findByExportedBy_Id(userId);
    }

    @Override
    public List<ExportSession> findAll() {
        return exportSessionRepository.findAll();
    }
}

