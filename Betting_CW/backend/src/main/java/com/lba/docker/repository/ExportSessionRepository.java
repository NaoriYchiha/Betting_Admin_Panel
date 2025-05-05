package com.lba.docker.repository;

import com.lba.docker.entity.ExportSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportSessionRepository extends JpaRepository<ExportSession, Long> {
    List<ExportSession> findByExportedBy_Id(Long userId);
}