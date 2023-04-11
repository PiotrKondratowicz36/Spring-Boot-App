package spring.services;

import spring.entities.Owner;
import spring.entities.Position;

import java.util.Optional;

public interface OwnerService {

    Iterable<Owner> listAllOwners();

    Optional<Owner> getOwnerById(Integer id);

    Owner saveOwner(Owner owner);

    void deleteOwner(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Owner> listAllOwnersPaging(Integer pageNr, Integer howManyOnPage);
}

