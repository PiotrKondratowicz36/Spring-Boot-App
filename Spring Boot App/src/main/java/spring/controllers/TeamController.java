package spring.controllers;

import spring.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.services.TeamService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Team> list(Model model) {
        return teamService.listAllTeams();
    }

    @ApiIgnore
    @RequestMapping(value = "/teams", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Team> redirect(Model model) {
        return teamService.listAllTeams();
    }

    @GetMapping(value = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team getByPublicId(@PathVariable("id") Integer publicId) {
        return teamService.getTeamById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team getByParamPublicId(@RequestParam("id") Integer publicId) {
        return teamService.getTeamById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/team")
    public ResponseEntity<Team> create(@RequestBody @NonNull @Valid
                                                  Team team) {
        team.setTeamId(UUID.randomUUID().toString());
        teamService.saveTeam(team);
        return ResponseEntity.ok().body(team);
    }

    @PutMapping(value = "/team")
    public ResponseEntity<Void> edit(@RequestBody Team team) {
        if(!teamService.checkIfExist(team.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            teamService.saveTeam(team);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/team/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        teamService.deleteTeam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/teams/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/teams/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Team> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return teamService.listAllTeamsPaging(pageNr, howManyOnPage.orElse(2));
    }
}
