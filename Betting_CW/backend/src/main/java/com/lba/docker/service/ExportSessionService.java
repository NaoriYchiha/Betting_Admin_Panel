package com.lba.docker.service;

import com.lba.docker.entity.ExportSession;

import java.util.List;

public interface ExportSessionService {
    ExportSession save(ExportSession exportSession);
    List<ExportSession> findByUserId(Long userId);
    List<ExportSession> findAll();
}
