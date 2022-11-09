package htw.berlin.webtech.demo;

import htw.berlin.webtech.demo.api.Person;
import htw.berlin.webtech.demo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/api/v1/persons")
    public ResponseEntity<List<Person>> fetchPersons(){
        return ResponseEntity.ok(personService.findAll());
    }

}
