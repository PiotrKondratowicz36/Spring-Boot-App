package spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import spring.entities.Player;
import spring.repositories.PlayerRepository;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Iterable<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getPlayerById(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deletePlayer(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (playerRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterable<Player> listAllPlayersPaging(Integer pageNr, Integer howManyOnPage) {
        return playerRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

}
