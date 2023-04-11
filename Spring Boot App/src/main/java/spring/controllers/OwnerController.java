package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entities.Owner;
import spring.services.OwnerService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping(value = "/owners", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Owner> list(Model model) {
        return ownerService.listAllOwners();
    }

    @ApiIgnore
    @RequestMapping(value = "/owners", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Owner> redirect(Model model) {
        return ownerService.listAllOwners();
    }

    @GetMapping(value = "/owner/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Owner getByPublicId(@PathVariable("id") Integer publicId) {
        return ownerService.getOwnerById(publicId).orElseGet(null);
    }

    @GetMapping(value = "/owner", produces = MediaType.APPLICATION_JSON_VALUE)
    public Owner getByParamPublicId(@RequestParam("id") Integer publicId) {
        return ownerService.getOwnerById(publicId).orElseGet(null);
    }

    @PostMapping(value = "/owner")
    public ResponseEntity<Owner> create(@RequestBody @NonNull @Valid
                                               Owner owner) {
        owner.setOwnerId(UUID.randomUUID().toString());
        ownerService.saveOwner(owner);
        return ResponseEntity.ok().body(owner);
    }

    @PutMapping(value = "/owner")
    public ResponseEntity<Void> edit(@RequestBody Owner owner) {
        if(!ownerService.checkIfExist(owner.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            ownerService.saveOwner(owner);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value = "/owner/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/owners/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/owners/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Owner> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return ownerService.listAllOwnersPaging(pageNr, howManyOnPage.orElse(2));
    }
}

