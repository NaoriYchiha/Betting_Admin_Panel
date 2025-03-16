package com.lba.docker.service;

import com.lba.docker.entity.Odds;
import com.lba.docker.repository.OddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OddsServiceImpl implements OddsService {

    @Autowired
    private OddsRepository oddsRepository;

    @Override
    public List<Odds> getOddsByEvent(Long eventId) {
        return oddsRepository.findByEventId(eventId);
    }

    @Override
    public Optional<Odds> getOddsByValue(double value) {
        return Optional.ofNullable(oddsRepository.findByValue(value));
    }

    @Override
    public Odds createOdds(Odds odds) {
        return oddsRepository.save(odds);
    }

    @Override
    public Odds updateOdds(Odds odds) {
        return oddsRepository.save(odds);
    }

    @Override
    public void deleteOdds(Long oddsId) {
        oddsRepository.deleteById(oddsId);
    }
}
