package com.jackie.person_registry_with_jpa.entities;

import com.jackie.person_registry_with_jpa.enums.TypeOfDoc;
import com.jackie.person_registry_with_jpa.enums.TypeOfFile;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoc;
    private String nameFile;
    private LocalDate dateOfInput;
    private TypeOfFile typeOfFile;
    private TypeOfDoc typeOfDoc;
    @Column(columnDefinition = "longtext")
    private String file;
    @ManyToOne
    @JoinColumn(name = "person_fiscalCode")
    private Person person;

    public Document() {
        super();
    }

    public Document(Integer idDoc, String nameFile, LocalDate dataOfInput, TypeOfFile typeOfFile, TypeOfDoc typeOfDoc, String file, Person person) {
        this.idDoc = idDoc;
        this.nameFile = nameFile;
        this.dateOfInput = dataOfInput;
        this.typeOfFile = typeOfFile;
        this.typeOfDoc = typeOfDoc;
        this.file = file;
        this.person = person;
    }

    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public LocalDate getDataOfInput() {
        return dateOfInput;
    }

    public void setDataOfInput(LocalDate dataOfInput) {
        this.dateOfInput = dataOfInput;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getDateOfInput() {
        return dateOfInput;
    }

    public void setDateOfInput(LocalDate dateOfInput) {
        this.dateOfInput = dateOfInput;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

