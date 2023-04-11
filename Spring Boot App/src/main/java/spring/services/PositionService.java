package spring.services;

import spring.entities.Position;

import java.util.Optional;

public interface PositionService {

    Iterable<Position> listAllPositions();

    Optional<Position> getPositionById(Integer id);

    Position savePosition(Position position);

    void deletePosition(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Position> listAllPositionsPaging(Integer pageNr, Integer howManyOnPage);
}

