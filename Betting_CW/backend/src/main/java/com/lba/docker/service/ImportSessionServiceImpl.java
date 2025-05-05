package com.lba.docker.service;


import com.lba.docker.entity.ImportSession;
import com.lba.docker.repository.ImportSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportSessionServiceImpl implements ImportSessionService {

    @Autowired
    private ImportSessionRepository importSessionRepository;

    @Override
    public ImportSession save(ImportSession importSession) {
        return importSessionRepository.save(importSession);
    }

    @Override
    public List<ImportSession> findByUserId(Long userId) {
        return importSessionRepository.findByStartedBy_Id(userId);
    }

    @Override
    public List<ImportSession> findAll() {
        return importSessionRepository.findAll();
    }
}