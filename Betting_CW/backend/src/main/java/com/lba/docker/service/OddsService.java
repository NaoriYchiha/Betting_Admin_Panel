package com.lba.docker.service;

import com.lba.docker.entity.Odds;

import java.util.List;
import java.util.Optional;

public interface OddsService {
    List<Odds> getOddsByEvent(Long eventId);
    Optional<Odds> getOddById(Long OddId);
    Optional<Odds> getOddsByValue(double value);
    Odds createOdds(Odds odds);
    Odds updateOdds(Odds odds);
    void deleteOdds(Long oddsId);
}
