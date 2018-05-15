package ee.ttu.studyhub.controllers;

import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST,
            consumes = "application/json")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") long personId) {
        return personService.getPersonById(personId);
    }

    @PostMapping("/login")
    public Person loginPerson(@RequestBody Map<String, Object> params) {
        return personService.loginPerson((String) params.get("email"), (String) params.get("password"));
    }
}


