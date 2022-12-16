package com.jackie.person_registry_with_jpa.services.impl;

import com.jackie.person_registry_with_jpa.entities.Person;
import com.jackie.person_registry_with_jpa.exceptions.MyCustomException;
import com.jackie.person_registry_with_jpa.repositories.PersonRepository;
import com.jackie.person_registry_with_jpa.services.DocumentService;
import com.jackie.person_registry_with_jpa.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repo;

    @Override
    public List<Person> getAllPerson() {
        return repo.findAll();
    }

    @Override
    public Person getPersonByFiscalCode(String fiscalCode) {
        Optional<Person> personOpt = repo.findPersonByFiscalCode(fiscalCode);
        if (personOpt.isEmpty()) {
            throw new MyCustomException("Utente con codice fiscale " + fiscalCode + " non trovato", HttpStatus.NOT_FOUND.value());
        }
        return personOpt.get();
    }

    @Override
    @Transactional
    public void createPerson(Person person) {

        repo.save(person);
    }

    @Override
    @Transactional
    public void updatePersonByFiscalCode(String fiscalCode, Person newPerson) {

        Person oldPerson = getPersonByFiscalCode(fiscalCode);

        newPerson.setFiscalCode(oldPerson.getFiscalCode());
        newPerson.getAddress().setId(oldPerson.getAddress().getId());
        repo.save(newPerson);
    }

    @Override
    @Transactional
    public void removePersonByFiscalCode(String fiscalCode) {

        Person personToDelete = getPersonByFiscalCode(fiscalCode);

        repo.delete(personToDelete);
    }
}
