package spring.controllers;

import spring.entities.Position;
import spring.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "/positions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Position> list(Model model) {
        return positionService.listAllPositions();
    }

    @ApiIgnore
    @RequestMapping(value = "/positions", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Position> redirect(Model model) {
        return positionService.listAllPositions();
    }

    @GetMapping(value = "/position/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Position getByPublicId(@PathVariable("id") Integer publicId) {
        return positionService.getPositionById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/position", produces = MediaType.APPLICATION_JSON_VALUE)
    public Position getByParamPublicId(@RequestParam("id") Integer publicId) {
        return positionService.getPositionById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/position")
    public ResponseEntity<Position> create(@RequestBody @NonNull @Valid
                                               Position position) {
        position.setPositionId(UUID.randomUUID().toString());
        positionService.savePosition(position);
        return ResponseEntity.ok().body(position);
    }

    @PutMapping(value = "/position")
    public ResponseEntity<Void> edit(@RequestBody Position position) {
        if(!positionService.checkIfExist(position.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            positionService.savePosition(position);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/position/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/positions/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/positions/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Position> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return positionService.listAllPositionsPaging(pageNr, howManyOnPage.orElse(2));
    }
}

