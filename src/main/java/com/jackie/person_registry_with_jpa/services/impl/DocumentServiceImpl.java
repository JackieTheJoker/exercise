package com.jackie.person_registry_with_jpa.services.impl;

import com.jackie.person_registry_with_jpa.controllers.PersonController;
import com.jackie.person_registry_with_jpa.entities.Document;
import com.jackie.person_registry_with_jpa.entities.Person;
import com.jackie.person_registry_with_jpa.enums.TypeOfDoc;
import com.jackie.person_registry_with_jpa.enums.TypeOfFile;
import com.jackie.person_registry_with_jpa.exceptions.MyCustomException;
import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import com.jackie.person_registry_with_jpa.repositories.DocumentRepository;
import com.jackie.person_registry_with_jpa.services.DocumentService;
import com.jackie.person_registry_with_jpa.services.PersonService;
import com.jackie.person_registry_with_jpa.utilities.DecodedWrapper;
import com.jackie.person_registry_with_jpa.utilities.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository repo;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    private PersonService personService;

    @Override
    public List<DocumentResponse> getAllDocumentsByFiscalCode(String fiscalCode) {
        Person person = personService.getPersonByFiscalCode(fiscalCode);
        List<DocumentResponse> presentedDocuments = new ArrayList<>();
        List<Document> persistenceDocuments = repo.findAllByPerson(person);

        for (Document persistent : persistenceDocuments){

            DecodedWrapper wrappedFile = new DecodedWrapper();
            wrappedFile.setDecoded(fileUtil.download(persistent.getFile()));

            DocumentResponse presentedDocument = new DocumentResponse();

            BeanUtils.copyProperties(persistent, presentedDocument);
            presentedDocument.setFile(wrappedFile);
            presentedDocuments.add(presentedDocument);
        }
        return presentedDocuments;
    }

    @Override
    @Transactional
    public void createDocumentByFiscalCode(MultipartFile file, TypeOfDoc doc, TypeOfFile extension, String fiscalCode) throws IOException {
        Person person = personService.getPersonByFiscalCode(fiscalCode);

        if (fiscalCode != null && fiscalCode.equals(person.getFiscalCode())) {

            Document newDocument = new Document();
            newDocument.setTypeOfFile(extension);
            newDocument.setNameFile(file.getOriginalFilename());
            newDocument.setTypeOfDoc(doc);
            newDocument.setDataOfInput(LocalDate.now());
            newDocument.setFile(fileUtil.upload(file));
            newDocument.setPerson(person);
            repo.save(newDocument);
        }
    }

    // TODO : implementare logica della service;

}
