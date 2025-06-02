package com.lba.docker.service;


import com.lba.docker.entity.*;
import com.lba.docker.repository.ImportSessionRepository;
import com.lba.docker.repository.OddsRepository;
import com.lba.docker.repository.SportsEventRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImportSessionServiceImpl implements ImportSessionService {

    @Autowired
    private final SportsEventRepository sportsEventRepository;

    @Autowired
    private final OddsRepository oddsRepository;

    @Autowired
    private ImportSessionRepository importSessionRepository;

    public ImportSessionServiceImpl(SportsEventRepository sportsEventRepository, OddsRepository oddsRepository) {
        this.sportsEventRepository = sportsEventRepository;
        this.oddsRepository = oddsRepository;
    }

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

    @Override
    @CacheEvict(value = {"oddsByEvent", "oddsByValue", "oddsById", "AllOdds", "sportEvents", "sportEventsByType",
            "sportEventById", "sportEventByName", "sportEventInRange"}, allEntries = true)
    public void importFromExcel(MultipartFile file) {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            oddsRepository.deleteAll();
            sportsEventRepository.deleteAll();

            Map<String, SportsEvent> eventMap = new HashMap<>();
            for (Row row : workbook.getSheet("Sports Events")) {
                if (row.getRowNum() == 0) continue;
                try {
                    var event = new SportsEvent();
                    event.setName(getCellStringValue(row.getCell(1)));
                    event.setEventDate(getCellDateTimeValue(row.getCell(2)));
                    event.setEventType(EventType.valueOf(getCellStringValue(row.getCell(3))));
                    eventMap.put(event.getName(), sportsEventRepository.save(event));
                } catch (Exception ignored) {}
            }

            for (Row row : workbook.getSheet("Odds")) {
                if (row.getRowNum() == 0) continue;
                try {
                    String name = getCellStringValue(row.getCell(3));
                    if (!eventMap.containsKey(name)) continue;
                    var odds = new Odds();
                    odds.setEvent(eventMap.get(name));
                    odds.setValue(getCellNumericValue(row.getCell(1)));
                    odds.setOutcomeType(OutcomeType.valueOf(getCellStringValue(row.getCell(4))));
                    oddsRepository.save(odds);
                } catch (Exception ignored) {}
            }

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения Excel", e);
        }
    }
    private String getCellStringValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue()); // если число — преобразуем в строку
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }

    private double getCellNumericValue(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }

    private LocalDateTime getCellDateTimeValue(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue();
            } else {
                throw new IllegalStateException("Expected date-formatted numeric cell");
            }
        } else if (cell.getCellType() == CellType.STRING) {
            String str = cell.getStringCellValue();
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException e) {
                throw new IllegalStateException("Invalid date string: " + str, e);
            }
        } else if (cell.getCellType() == CellType.BLANK) {
            return null;
        } else {
            throw new IllegalStateException("Unsupported cell type for date value: " + cell.getCellType());
        }
    }
}
