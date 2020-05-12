package com.apn.mlm.dao;

import com.apn.mlm.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonInMemoryDao implements  PersonDao{
    private List<Person> persons;

    public PersonInMemoryDao() {
        this.persons = new ArrayList<>();
    }

    @Override
    public void add(Person person) {
        persons.add(person);
    }

    @Override
    public List<Person> list() {
        return persons;
    }

    @Override
    public Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
