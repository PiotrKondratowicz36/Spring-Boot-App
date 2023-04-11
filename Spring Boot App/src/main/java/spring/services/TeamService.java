package spring.services;

import spring.entities.Team;

import java.util.Optional;

public interface TeamService {

    Iterable<Team> listAllTeams();

    Optional<Team> getTeamById(Integer id);

    Team saveTeam(Team team);

    void deleteTeam(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Team> listAllTeamsPaging(Integer pageNr, Integer howManyOnPage);
}

