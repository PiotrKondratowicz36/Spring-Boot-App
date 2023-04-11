package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entities.Match;
import spring.services.MatchService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping(value = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Match> list(Model model) {
        return matchService.listAllMatches();
    }

    @ApiIgnore
    @RequestMapping(value = "/matches", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Match> redirect(Model model) {
        return matchService.listAllMatches();
    }

    @GetMapping(value = "/match/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Match getByPublicId(@PathVariable("id") Integer publicId) {
        return matchService.getMatchById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/match", produces = MediaType.APPLICATION_JSON_VALUE)
    public Match getByParamPublicId(@RequestParam("id") Integer publicId) {
        return matchService.getMatchById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/match")
    public ResponseEntity<Match> create(@RequestBody @NonNull @Valid
                                               Match match) {
        match.setMatchId(UUID.randomUUID().toString());
        matchService.saveMatch(match);
        return ResponseEntity.ok().body(match);
    }

    @PutMapping(value = "/match")
    public ResponseEntity<Void> edit(@RequestBody Match match) {
        if(!matchService.checkIfExist(match.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            matchService.saveMatch(match);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/match/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        matchService.deleteMatch(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/matches/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/matches/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Match> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return matchService.listAllMatchesPaging(pageNr, howManyOnPage.orElse(2));
    }
}

