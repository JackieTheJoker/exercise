package com.jackie.person_registry_with_jpa.controllers;


import com.jackie.person_registry_with_jpa.enums.TypeOfDoc;
import com.jackie.person_registry_with_jpa.enums.TypeOfFile;
import com.jackie.person_registry_with_jpa.exceptions.MyCustomException;
import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import com.jackie.person_registry_with_jpa.message.ResponseMessage;
import com.jackie.person_registry_with_jpa.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @PostMapping(consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE}, value = "/{fiscalCode}")

    public ResponseEntity<ResponseMessage> createDocument(@RequestPart MultipartFile file,
                                                          @RequestPart TypeOfFile extension,
                                                          @RequestPart TypeOfDoc doc,
                                                          @PathVariable("fiscalCode") String fiscalCode) {
        String message = "";
        try {
            service.createDocumentByFiscalCode(file, doc, extension, fiscalCode);
            message = "File caricato con successo: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "Impossibile caricare il file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/{fiscalCode}")
    public ResponseEntity<List<DocumentResponse>> getAllByFiscalCode(@PathVariable("fiscalCode") String fiscalCode) throws IOException {

        try {
            return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllDocumentsByFiscalCode(fiscalCode));
        } catch (IOException ioEx){
           throw new IOException("Documenti non trovati");
        } catch (Exception ex){
            throw new MyCustomException("Errore");
        }
    }
}
