package spring.services;

import spring.entities.Player;
import java.util.Optional;

public interface PlayerService {

    Iterable<Player> listAllPlayers();

    Optional<Player> getPlayerById(Integer id);

    Player savePlayer(Player player);

    void deletePlayer(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Player> listAllPlayersPaging(Integer pageNr, Integer howManyOnPage);
}

