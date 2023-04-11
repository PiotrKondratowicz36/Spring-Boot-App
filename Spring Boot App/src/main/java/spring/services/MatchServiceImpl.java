package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.entities.Match;
import spring.repositories.MatchRepository;

import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Iterable<Match> listAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public void deleteMatch(Integer id) {
        matchRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (matchRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Match> listAllMatchesPaging(Integer pageNr, Integer howManyOnPage) {
        return matchRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

}
