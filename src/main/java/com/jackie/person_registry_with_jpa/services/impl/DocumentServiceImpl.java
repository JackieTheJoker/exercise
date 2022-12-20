package com.jackie.person_registry_with_jpa.services.impl;

import com.jackie.person_registry_with_jpa.controllers.PersonController;
import com.jackie.person_registry_with_jpa.entities.Document;
import com.jackie.person_registry_with_jpa.entities.Person;
import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import com.jackie.person_registry_with_jpa.repositories.DocumentRepository;
import com.jackie.person_registry_with_jpa.services.DocumentService;
import com.jackie.person_registry_with_jpa.services.PersonService;
import com.jackie.person_registry_with_jpa.utilities.DecodedWrapper;
import com.jackie.person_registry_with_jpa.utilities.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        List<Document> documents = repo.findDocumentsByPerson(person);
        List<DocumentResponse> decodedDocuments = new ArrayList<>();
        for (Document d : documents){
            DocumentResponse decodedDocument = new DocumentResponse();
            DecodedWrapper file = new DecodedWrapper();
            BeanUtils.copyProperties(d, file);
            file.setDecoded(fileUtil.download(d.getFile()));
            decodedDocument.setFile(file);
            decodedDocuments.add(decodedDocument);
        }
        return decodedDocuments;
    }

    @Override
    @Transactional
    public void createDocumentByFiscalCode(MultipartFile file, String fiscalCode) throws IOException {
        Person  person = personService.getPersonByFiscalCode(fiscalCode);

        if (fiscalCode != null && fiscalCode.equals(person.getFiscalCode())) {

            Document newDocument = new Document();
            BeanUtils.copyProperties(file, newDocument);
            newDocument.setDataOfInput(LocalDate.now());
            newDocument.setFile(fileUtil.upload(file));
            newDocument.setPerson(person);
            repo.save(newDocument);
        }
    }

    // TODO : implementare logica della service;

}
