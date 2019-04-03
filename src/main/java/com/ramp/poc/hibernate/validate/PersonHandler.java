package com.ramp.poc.hibernate.validate;

import javax.validation.Valid;


public interface PersonHandler {

    @Valid
    public Person getPerson(String uuid);


    public String setPerson(@Valid Person person);

    public void updatePerson(Person person, String uuid);

}
