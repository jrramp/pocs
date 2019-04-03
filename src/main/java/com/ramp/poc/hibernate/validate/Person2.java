package com.ramp.poc.hibernate.validate;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Person2 {

    @NotNull
    @Valid
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}