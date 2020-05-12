package com.apn.mlm.dao;

import com.apn.mlm.model.Person;

import java.util.List;

public interface PersonDao {

    public void add(Person person);
    public List<Person> list();
    public Person findById(int id);

}

