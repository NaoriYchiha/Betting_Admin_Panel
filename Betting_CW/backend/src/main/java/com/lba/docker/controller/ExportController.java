package com.lba.docker.controller;

import com.lba.docker.entity.ExportSession;
import com.lba.docker.entity.Odds;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.entity.User;
import com.lba.docker.security.CustomUserDetails;
import com.lba.docker.service.ExportSessionService;
import com.lba.docker.service.OddsService;
import com.lba.docker.service.SportsEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private  SportsEventService sportsEventService;

    @Autowired
    private  OddsService oddsService;

    @Autowired
    private  ExportSessionService excelExportService;

    @GetMapping("/events-and-odds")
    public ResponseEntity<byte[]> exportEventsAndOdds() throws IOException {

        List<SportsEvent> events = sportsEventService.getAllEvents();
        List<Odds> oddsList = oddsService.getAllOdds();
        byte[] excelData = excelExportService.exportEventsAndOddsToExcel(events, oddsList);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=events_and_odds.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelData);
    }
}
