package com.jackie.person_registry_with_jpa.controllers;

import com.jackie.person_registry_with_jpa.message.DocumentResponse;
import com.jackie.person_registry_with_jpa.message.ResponseMessage;
import com.jackie.person_registry_with_jpa.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @PostMapping(consumes = "multipart/form-data",value = "/{fiscalCode}")

    public ResponseEntity<ResponseMessage> createDocument(@ModelAttribute("file") MultipartFile file,
                                                          @PathVariable("fiscalCode") String fiscalCode) {
        String message = "";
        try {
            service.createDocumentByFiscalCode(file, fiscalCode);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (IOException e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
