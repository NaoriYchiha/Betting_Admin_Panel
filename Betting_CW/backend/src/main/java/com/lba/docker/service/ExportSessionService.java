package com.lba.docker.service;

import com.lba.docker.entity.ExportSession;
import com.lba.docker.entity.Odds;
import com.lba.docker.entity.SportsEvent;

import java.io.IOException;
import java.util.List;

public interface ExportSessionService {
    ExportSession save(ExportSession exportSession);
    List<ExportSession> findByUserId(Long userId);
    List<ExportSession> findAll();

    byte[] exportEventsAndOddsToExcel(List<SportsEvent> events, List<Odds> oddsList) throws IOException;
}
