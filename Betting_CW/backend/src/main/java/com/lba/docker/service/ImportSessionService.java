package com.lba.docker.service;

import com.lba.docker.entity.ImportSession;

import java.util.List;

public interface ImportSessionService {
    ImportSession save(ImportSession importSession);
    List<ImportSession> findByUserId(Long userId);
    List<ImportSession> findAll();
}