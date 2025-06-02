package com.lba.docker.service;

import com.lba.docker.entity.ImportSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportSessionService {
    ImportSession save(ImportSession importSession);
    List<ImportSession> findByUserId(Long userId);
    List<ImportSession> findAll();
    void importFromExcel(MultipartFile file);
}