package ee.ttu.studyhub.controllers;


import ee.ttu.studyhub.entity.Person;
import ee.ttu.studyhub.service.PersonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value="/person/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
}
