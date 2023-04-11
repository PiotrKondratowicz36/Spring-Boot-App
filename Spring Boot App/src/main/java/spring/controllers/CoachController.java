package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entities.Coach;
import spring.services.CoachService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Coach> list(Model model) {
        return coachService.listAllCoaches();
    }

    @ApiIgnore
    @RequestMapping(value = "/coaches", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Coach> redirect(Model model) {
        return coachService.listAllCoaches();
    }

    @GetMapping(value = "/coach/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coach getByPublicId(@PathVariable("id") Integer publicId) {
        return coachService.getCoachById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/coach", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coach getByParamPublicId(@RequestParam("id") Integer publicId) {
        return coachService.getCoachById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/coach")
    public ResponseEntity<Coach> create(@RequestBody @NonNull @Valid
                                                Coach coach) {
        coach.setCoachId(UUID.randomUUID().toString());
        coachService.saveCoach(coach);
        return ResponseEntity.ok().body(coach);
    }

    @PutMapping(value = "/coach")
    public ResponseEntity<Void> edit(@RequestBody Coach coach) {
        if(!coachService.checkIfExist(coach.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            coachService.saveCoach(coach);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/coach/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        coachService.deleteCoach(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/coaches/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/coaches/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Coach> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return coachService.listAllCoachesPaging(pageNr, howManyOnPage.orElse(2));
    }
}

