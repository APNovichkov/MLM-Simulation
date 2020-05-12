package com.apn.mlm.service;

import java.util.List;
import com.apn.mlm.dao.PersonDao;
import com.apn.mlm.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void addPerson(Person person){
        personDao.add(person);
    }

    public List<Person> listPersons(){
        return personDao.list();
    }

    public Person findOne(int id){
        return personDao.findById(id);
    }

}
