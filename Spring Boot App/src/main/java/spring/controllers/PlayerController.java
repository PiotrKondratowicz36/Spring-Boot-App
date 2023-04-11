package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entities.Player;
import spring.services.PlayerService;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Player> list(Model model) {
        return playerService.listAllPlayers();
    }

    @ApiIgnore
    @RequestMapping(value = "/players", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Player> redirect(Model model) {
        return playerService.listAllPlayers();
    }

    @GetMapping(value = "/player/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Player getByPublicId(@PathVariable("id") Integer publicId) {
        return playerService.getPlayerById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public Player getByParamPublicId(@RequestParam("id") Integer publicId) {
        return playerService.getPlayerById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/player")
    public ResponseEntity<Player> create(@RequestBody @NonNull @Valid
                                                   Player player) {
        player.setPlayerId(UUID.randomUUID().toString());
        playerService.savePlayer(player);
        return ResponseEntity.ok().body(player);
    }

    @PutMapping(value = "/player")
    public ResponseEntity<Void> edit(@RequestBody Player player) {
        if(!playerService.checkIfExist(player.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            playerService.savePlayer(player);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/player/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/players/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Player> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return playerService.listAllPlayersPaging(pageNr, howManyOnPage.orElse(2));
    }
}


