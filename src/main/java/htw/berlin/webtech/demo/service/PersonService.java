package htw.berlin.webtech.demo.service;

import htw.berlin.webtech.demo.api.Person;
import htw.berlin.webtech.demo.api.PersonCreateRequest;
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
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Person create(PersonCreateRequest request){
        var personEntity = new PersonEntity(request.getFirstName(), request.getLastName());
        personEntity = personRepository.save(personEntity);
        return transformEntity(personEntity);
    }

    private Person transformEntity(PersonEntity personEntity){
        return new Person(
                personEntity.getId(),
                personEntity.getFirstName(),
                personEntity.getLastName()
                );
    }

}
