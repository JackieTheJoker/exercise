package com.jackie.person_registry_with_jpa.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.jackie.person_registry_with_jpa.exceptions.MyCustomException;

public enum TypeOfDoc {
    ID_CARD("ID_CARD"),
    DRIVER_LICENSE("DRIVER_LICENSE"),
    CURRICULUM("CURRICULUM");

    private String value;

    TypeOfDoc(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TypeOfDoc fromValue(String value) {
        for (TypeOfDoc e : values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new MyCustomException();
    }

}
