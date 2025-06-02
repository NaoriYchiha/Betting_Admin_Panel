package com.lba.docker.controller;

import com.lba.docker.entity.Odds;
import com.lba.docker.service.OddsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/api/odds")
public class OddsController {

    @Autowired
    private OddsService oddsService;

    // Получить все коэффициенты для события
    @GetMapping("/{id}")
    public ResponseEntity<Odds> getOddsById(@PathVariable Long id) {
        Optional<Odds> odds = oddsService.getOddById(id);
        return odds.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Odds>> getAllOdds(){
        List<Odds> oddsList = oddsService.getAllOdds();
        if (oddsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oddsList, HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Odds>> getOddsByEvent(@PathVariable Long eventId) {
        List<Odds> oddsList = oddsService.getOddsByEvent(eventId);
        if (oddsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(oddsList, HttpStatus.OK);
    }

    // Получить коэффициент по значению
    @GetMapping("/value/{value}")
    public ResponseEntity<Odds> getOddsByValue(@PathVariable double value) {
        Optional<Odds> odds = oddsService.getOddsByValue(value);
        return odds.map(o -> new ResponseEntity<>(o, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Создать новый коэффициент
    @PostMapping
    public ResponseEntity<Odds> createOdds(@RequestBody Odds odds) {
        Odds createdOdds = oddsService.createOdds(odds);
        return new ResponseEntity<>(createdOdds, HttpStatus.CREATED);
    }

    // Обновить коэффициент
    @PutMapping("/{id}")
    public ResponseEntity<Odds> updateOdds(@PathVariable Long id, @RequestBody Odds odds) {
        Odds existingOdds = getExistingOddsById(id);

        if (existingOdds == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (odds.getValue() != 0) {
            existingOdds.setValue(odds.getValue());
        }
        if (odds.getOutcomeType() != null) {
            existingOdds.setOutcomeType(odds.getOutcomeType());
        }

        if (odds.getEvent() != null) {
            existingOdds.setEvent(odds.getEvent());
        }

        // Save and return updated odds
        Odds updatedOdds = oddsService.updateOdds(existingOdds);
        return new ResponseEntity<>(updatedOdds, HttpStatus.OK);
    }

    private Odds getExistingOddsById(Long id) {
        Optional<Odds> existingOddsOpt = oddsService.getOddById(id);
        return existingOddsOpt.orElse(null);
    }



    // Удалить коэффициент
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOdds(@PathVariable Long id) {
        oddsService.deleteOdds(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
