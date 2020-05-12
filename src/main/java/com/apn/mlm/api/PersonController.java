package com.apn.mlm.api;

import java.util.List;
import com.apn.mlm.model.Person;
import com.apn.mlm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    @Autowired
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody  Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> listPersons(){
        return personService.listPersons();
    }

    @GetMapping(path = "{id}")
    public Person getPerson(@PathVariable("id") int id){
        return personService.findOne(id);
    }

    

}
