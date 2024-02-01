package com.practice.aop.aop.rest;

import com.practice.aop.aop.persistence.entity.Person;
import com.practice.aop.aop.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/person")
public class PersonRest {

    private final PersonService service;

    public PersonRest(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(service.save(person), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Stream<Person>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
