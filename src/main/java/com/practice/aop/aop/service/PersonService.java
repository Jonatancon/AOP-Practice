package com.practice.aop.aop.service;

import com.practice.aop.aop.persistence.dao.PersonDao;
import com.practice.aop.aop.persistence.entity.Person;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PersonService {
    private final PersonDao dao;

    public PersonService(PersonDao dao) {
        this.dao = dao;
    }

    public Person save(Person person) {
        return dao.save(person);
    }

    public Stream<Person> getAll() {
        return dao.findAll().stream();
    }

    public Person getById(Integer id) {
        return dao.findById(id).orElseThrow(() -> new RuntimeException("Not found person"));
    }
}
