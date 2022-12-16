package com.jackie.person_registry_with_jpa.repositories;

import com.jackie.person_registry_with_jpa.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
