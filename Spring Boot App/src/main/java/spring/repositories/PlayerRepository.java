package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.Player;
import spring.entities.Position;

public interface PlayerRepository extends CrudRepository<Player, Integer>, PagingAndSortingRepository<Player, Integer>  {

    @Query("select count(*) from Player p where p.id = ?1")
    Integer checkIfExist(Integer id);
}