package com.lba.docker.repository;

import com.lba.docker.entity.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
    List<ChangeLog> findByEvent_Id(Long eventId);
    List<ChangeLog> findByChangedBy_Id(Long userId);
}