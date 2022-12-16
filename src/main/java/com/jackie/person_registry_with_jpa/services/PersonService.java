package com.jackie.person_registry_with_jpa.services;
import com.jackie.person_registry_with_jpa.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPerson();

    Person getPersonByFiscalCode(String fiscalCode);

    void createPerson(Person Person);

    void removePersonByFiscalCode(String fiscalCode);

    void updatePersonByFiscalCode(String fiscalCode, Person newPerson);
}

