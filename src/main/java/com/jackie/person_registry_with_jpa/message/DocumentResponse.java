package com.jackie.person_registry_with_jpa.message;

import com.jackie.person_registry_with_jpa.enums.TypeOfDoc;
import com.jackie.person_registry_with_jpa.enums.TypeOfFile;
import org.springframework.web.multipart.MultipartFile;

public class DocumentResponse {

    private String nameFile;
    private TypeOfFile typeOfFile;
    private TypeOfDoc typeOfDoc;
    private MultipartFile file;

    public DocumentResponse(String nameFile, TypeOfFile typeOfFile, TypeOfDoc typeOfDoc, String fiscalCode, MultipartFile file) {
        this.nameFile = nameFile;
        this.typeOfFile = typeOfFile;
        this.typeOfDoc = typeOfDoc;
        this.file = file;
    }
    public DocumentResponse() {
        super();
    }

    public String getNameFile() {
        return nameFile;
    }
    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public TypeOfFile getTypeOfFile() {
        return typeOfFile;
    }

    public void setTypeOfFile(TypeOfFile typeOfFile) {
        this.typeOfFile = typeOfFile;
    }

    public TypeOfDoc getTypeOfDoc() {
        return typeOfDoc;
    }

    public void setTypeOfDoc(TypeOfDoc typeOfDoc) {
        this.typeOfDoc = typeOfDoc;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
