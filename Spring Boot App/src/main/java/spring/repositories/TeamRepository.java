package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.Team;

public interface TeamRepository extends CrudRepository<Team, Integer>, PagingAndSortingRepository<Team, Integer>  {

    @Query("select count(*) from Team p where p.id = ?1")
    Integer checkIfExist(Integer id);
}