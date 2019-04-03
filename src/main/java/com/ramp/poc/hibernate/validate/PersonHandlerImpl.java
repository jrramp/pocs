package com.ramp.poc.hibernate.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.UUID;


@Component
@Validated
public class PersonHandlerImpl implements PersonHandler {

    HashMap<String, Person> persons = new HashMap<>();

    public Person getPerson(String uuid) {
        Person p = persons.get(uuid);
        return p;

    }


    public String setPerson(Person person) {
        String uuid = UUID.randomUUID().toString();
        persons.put(uuid, person);
        return uuid;
    }

    public void updatePerson(Person person, String uuid) {

        persons.put(uuid, person);

    }

}
