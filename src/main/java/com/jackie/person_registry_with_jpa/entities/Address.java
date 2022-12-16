package com.jackie.person_registry_with_jpa.entities;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String civic;
    @Column(nullable = false)
    private Integer postalCode;

    public Address() {
    }

    public Address(String street, String civic, Integer postalCode) {
        this.street = street;
        this.civic = civic;
        this.postalCode = postalCode;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public long getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCivic() {
        return civic;
    }

    public void setCivic(String civic) {
        this.civic = civic;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public long getId() {return addressId;}

    public void setId(long id) {
    }
}
