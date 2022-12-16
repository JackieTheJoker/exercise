package com.jackie.person_registry_with_jpa.controllers;

import com.jackie.person_registry_with_jpa.entities.Person;
import com.jackie.person_registry_with_jpa.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = service.getAllPerson();
        return ResponseEntity.ok(persons);
    }

    // get Person by id dati in input
    @GetMapping("/{fiscalCode}")
    public ResponseEntity<Person> getPersonByFiscalCode(@PathVariable String fiscalCode) {
        Person person = service.getPersonByFiscalCode(fiscalCode);
        return ResponseEntity.ok(person);
    }

    // creazione nuovo Person
    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody Person person) {
        service.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{fiscalCode}")
    public ResponseEntity<Void> deletePerson(@PathVariable String fiscalCode) {
        service.removePersonByFiscalCode(fiscalCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{fiscalCode}")
    public ResponseEntity<Void> updatePerson(@RequestBody Person person, @PathVariable String fiscalCode) {
        service.updatePersonByFiscalCode(fiscalCode, person);
        return ResponseEntity.noContent().build();
    }
}
