package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Person;
import htw.berlin.webtech.demo.persistence.PersonEntity;
import htw.berlin.webtech.demo.persistence.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        List<PersonEntity> persons = personRepository.findAll();
        return persons.stream()
                .map(personEntity -> new Person(
                        personEntity.getId(),
                        personEntity.getFirstName(),
                        personEntity.getLastName()
                ))
                .collect(Collectors.toList());
    }

}
