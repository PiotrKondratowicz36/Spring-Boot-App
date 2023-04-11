package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.entities.Position;
import spring.entities.Team;
import spring.repositories.PositionRepository;
import spring.repositories.TeamRepository;

import java.util.Optional;

/**
 * Product service implement.
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public Iterable<Position> listAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> getPositionById(Integer id) {
        return positionRepository.findById(id);
    }

    @Override
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public void deletePosition(Integer id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (positionRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Position> listAllPositionsPaging(Integer pageNr, Integer howManyOnPage) {
        return positionRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

}
