package com.lba.docker.service;


import com.lba.docker.entity.ExportSession;
import com.lba.docker.entity.Odds;
import com.lba.docker.entity.SportsEvent;
import com.lba.docker.repository.ExportSessionRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExportSessionServiceImpl implements ExportSessionService {
    @Autowired
    private ExportSessionRepository exportSessionRepository;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
    @Override
    public byte[] exportEventsAndOddsToExcel(List<SportsEvent> events, List<Odds> oddsList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            // Лист 1: Sports Events
            Sheet eventSheet = workbook.createSheet("Sports Events");
            createEventsSheet(eventSheet, workbook, events);

            // Лист 2: Odds
            Sheet oddsSheet = workbook.createSheet("Odds");
            createOddsSheet(oddsSheet, workbook, oddsList);

            // Запись в байт-массив
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private void createEventsSheet(Sheet sheet, Workbook workbook, List<SportsEvent> events) {
        String[] columns = {"ID", "Name", "Event Date", "Event Type"};

        Row headerRow = sheet.createRow(0);
        createHeaderRow(headerRow, workbook, columns);

        int rowIdx = 1;
        for (SportsEvent event : events) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(event.getId());
            row.createCell(1).setCellValue(event.getName());
            row.createCell(2).setCellValue(event.getEventDate().format(DATE_FORMATTER));
            row.createCell(3).setCellValue(event.getEventType().name());
        }

        autoSizeColumns(sheet, columns.length);
    }

    private void createOddsSheet(Sheet sheet, Workbook workbook, List<Odds> oddsList) {
        String[] columns = {"ID", "Value", "Event ID", "Event Name", "Outcome Type"};

        Row headerRow = sheet.createRow(0);
        createHeaderRow(headerRow, workbook, columns);

        int rowIdx = 1;
        for (Odds odds : oddsList) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(odds.getId());
            row.createCell(1).setCellValue(odds.getValue());
            row.createCell(2).setCellValue(odds.getEvent().getId());
            row.createCell(3).setCellValue(odds.getEvent().getName());
            row.createCell(4).setCellValue(odds.getOutcomeType().name());
        }

        autoSizeColumns(sheet, columns.length);
    }

    private void createHeaderRow(Row headerRow, Workbook workbook, String[] columns) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(style);
        }
    }

    private void autoSizeColumns(Sheet sheet, int numberOfColumns) {
        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}

