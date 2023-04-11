package spring.services;

import spring.entities.Coach;

import java.util.Optional;

public interface CoachService {

    Iterable<Coach> listAllCoaches();

    Optional<Coach> getCoachById(Integer id);

    Coach saveCoach(Coach coach);

    void deleteCoach(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Coach> listAllCoachesPaging(Integer pageNr, Integer howManyOnPage);
}

