package spring.services;

import spring.entities.Match;

import java.util.Optional;

public interface MatchService {

    Iterable<Match> listAllMatches();

    Optional<Match> getMatchById(Integer id);

    Match saveMatch(Match match);

    void deleteMatch(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Match> listAllMatchesPaging(Integer pageNr, Integer howManyOnPage);
}

