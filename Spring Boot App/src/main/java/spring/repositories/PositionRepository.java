package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.Position;

public interface PositionRepository extends CrudRepository<Position, Integer>, PagingAndSortingRepository<Position, Integer>  {

    @Query("select count(*) from Position p where p.id = ?1")
    Integer checkIfExist(Integer id);
}