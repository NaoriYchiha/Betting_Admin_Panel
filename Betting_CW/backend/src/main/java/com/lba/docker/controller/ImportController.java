package com.lba.docker.controller;

import com.lba.docker.service.ImportSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/api/import")
public class ImportController {

    @Autowired
    private ImportSessionService importService;

    @PostMapping
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        importService.importFromExcel(file);
        return ResponseEntity.ok("Импорт завершён успешно");
    }
}