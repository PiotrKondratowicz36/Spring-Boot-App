package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.entities.Owner;
import spring.repositories.OwnerRepository;

import java.util.Optional;


@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Iterable<Owner> listAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> getOwnerById(Integer id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Integer id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (ownerRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Owner> listAllOwnersPaging(Integer pageNr, Integer howManyOnPage) {
        return ownerRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

}
