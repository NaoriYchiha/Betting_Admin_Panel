package com.lba.docker.service;

import com.lba.docker.entity.Odds;
import com.lba.docker.repository.OddsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OddsServiceImpl implements OddsService {

    @Autowired
    private OddsRepository oddsRepository;

    @Override
    @Cacheable(value = "oddsByEvent", key = "#eventId")
    public List<Odds> getOddsByEvent(Long eventId) {
        return oddsRepository.findByEventId(eventId);
    }

    @Override
    @Cacheable(value = "oddsById", key = "#id")
    public Optional<Odds> getOddById(Long id) {
        return oddsRepository.findById(id);
    }
    @Override
    @Cacheable(value = "oddsByValue", key = "#value")
    public Optional<Odds> getOddsByValue(double value) {
        return oddsRepository.findByValue(value);  // если уже возвращается Optional
    }


    @Override
    @CacheEvict(value = {"oddsByEvent", "oddsByValue", "oddsById"}, allEntries = true)
    public Odds createOdds(Odds odds) {
        return oddsRepository.save(odds);
    }

    @Override
    @CacheEvict(value = {"oddsByEvent", "oddsByValue", "oddsById"}, allEntries = true)
    public Odds updateOdds(Odds odds) {
        return oddsRepository.save(odds);
    }

    @Override
    @CacheEvict(value = {"oddsByEvent", "oddsByValue", "oddsById"}, allEntries = true)
    public void deleteOdds(Long oddsId) {
        oddsRepository.deleteById(oddsId);
    }
}