package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.entities.Coach;
import spring.repositories.CoachRepository;

import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Override
    public Iterable<Coach> listAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public Optional<Coach> getCoachById(Integer id) {
        return coachRepository.findById(id);
    }

    @Override
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void deleteCoach(Integer id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (coachRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Coach> listAllCoachesPaging(Integer pageNr, Integer howManyOnPage) {
        return coachRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }
}
