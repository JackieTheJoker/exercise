package com.jackie.person_registry_with_jpa.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Person {

    @Id
    private String fiscalCode;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private Date birthDate;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Document> documents;

    public Person(String name, String surname, Date birthDate, String fiscalCode, Address address, List<Document> documents) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.fiscalCode = fiscalCode;
        this.address = address;
        this.documents= documents;
    }

    public Person() {
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
