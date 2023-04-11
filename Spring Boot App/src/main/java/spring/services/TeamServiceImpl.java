package spring.services;

import spring.entities.Team;
import spring.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Product service implement.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Iterable<Team> listAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> getTeamById(Integer id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (teamRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Team> listAllTeamsPaging(Integer pageNr, Integer howManyOnPage) {
        return teamRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

}
