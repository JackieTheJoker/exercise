package com.jackie.person_registry_with_jpa.services;

import com.jackie.person_registry_with_jpa.entities.Document;
import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    List<Document> getAllDocumentsByFiscalCode();

    void createDocumentByFiscalCode (MultipartFile file, String fiscalCode) throws IOException;

}
