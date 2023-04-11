package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.Match;
import spring.entities.Position;

public interface MatchRepository extends CrudRepository<Match, Integer>, PagingAndSortingRepository<Match, Integer>  {

    @Query("select count(*) from Match p where p.id = ?1")
    Integer checkIfExist(Integer id);
}