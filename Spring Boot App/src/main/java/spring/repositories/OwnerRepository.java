package spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.entities.Owner;
import spring.entities.Position;

public interface OwnerRepository extends CrudRepository<Owner, Integer>, PagingAndSortingRepository<Owner, Integer>  {

    @Query("select count(*) from Owner p where p.id = ?1")
    Integer checkIfExist(Integer id);
}