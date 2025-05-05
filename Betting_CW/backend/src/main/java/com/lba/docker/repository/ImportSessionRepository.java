package com.lba.docker.repository;

import com.lba.docker.entity.ImportSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportSessionRepository extends JpaRepository<ImportSession, Long> {
    List<ImportSession> findByStartedBy_Id(Long userId);
}