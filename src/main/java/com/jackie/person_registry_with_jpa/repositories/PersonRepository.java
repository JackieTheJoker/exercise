package com.jackie.person_registry_with_jpa.repositories;

import com.jackie.person_registry_with_jpa.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Optional<Person> findPersonByFiscalCode(String fiscalCode);
    void removePersonByFiscalCode(String fiscalCode);

}
