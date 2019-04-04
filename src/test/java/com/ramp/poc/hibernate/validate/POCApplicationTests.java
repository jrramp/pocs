package com.ramp.poc.hibernate.validate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.*;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class POCApplicationTests {

    @Autowired
    PersonHandler personHandler;

    @Test
    public void contextLoads() {
    }

    @Test(expected = ValidationException.class)
    public void testValidation() {
        Person p = new Person();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(p);
        if (constraintViolations.size() > 0) {
            throw new ValidationException("Not valid object" + constraintViolations);
        }

    }

    @Test(expected = ConstraintViolationException.class)
    public void testToVerifyValidatorInvokedForParameter() {
        Person p = new Person();

        personHandler.setPerson(p);

    }

    public void testToVerifyValidatorInvokedForParameterNoException() {

        Person p = new Person();
        p.setFirstName("test");
        personHandler.setPerson(p);

    }

    @Test(expected = ConstraintViolationException.class)
    public void testToVerifyValidatorInvokedForReturn() {
        try {
            Person p = new Person();
            String uuid = UUID.randomUUID().toString();
            personHandler.updatePerson(p, uuid);

            personHandler.getPerson(uuid);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Test
    public void testToVerifyValidatorInvokedForParameterAndNoException() {
        Person p = new Person();
        Address address = new Address();
        address.setZipCode(12345);
        p.setAddress(address);
        p.setFirstName("test");
        String uuid = personHandler.setPerson(p);
        personHandler.getPerson(uuid);

    }


    @Test(expected = ConstraintViolationException.class)
    public void testValidationFailOnAddressElement() {
        try {
            Person p = new Person();
            Address address = new Address();
            // zipcode is required not null field. Not setting
            p.setAddress(address);
            p.setFirstName("test");
            String uuid = personHandler.setPerson(p);
            personHandler.getPerson(uuid);

        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw e;
        }

    }

}
