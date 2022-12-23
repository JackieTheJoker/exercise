package com.jackie.person_registry_with_jpa.repositories;

import com.jackie.person_registry_with_jpa.entities.Document;
import com.jackie.person_registry_with_jpa.entities.Person;
import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findAllByPerson(Person person);
}
