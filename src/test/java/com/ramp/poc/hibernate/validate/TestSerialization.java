package com.ramp.poc.hibernate.validate;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class TestSerialization {

    @Test
    public void testJackSerializeWithEmptyJsonContent() throws Exception {
        ObjectMapper om = new ObjectMapper();


        Person person = om.reader().withType(Person.class).readValue("{}");
        assertThat("Didnt read firstName  as null", person.getFirstName(), IsNull.nullValue());
        assertThat("Didnt read firstName  as null", person.getLastName(), IsNull.nullValue());

    }

    // Jackson parser does not do any data validation while parsing to POJO.
    @Test(expected = UnrecognizedPropertyException.class)
    public void testJackSerializeWithAdditional() throws Exception {
        ObjectMapper om = new ObjectMapper();
        // Tries to parse Person2 that dont have ignoreUnknown properties enabled.
        om.reader().withType(Person2.class).readValue("{\"newField\":{}}");

    }

    @Test
    public void testSerializeWithEmptyJsonContent() throws Exception {
        ObjectMapper om = new ObjectMapper();
        // Tries to parse Person2 that dont have ignoreUnknown properties enabled.

        Person2 person = om.reader().withType(Person2.class).readValue("{}");
        assertThat("Didnt read firstName  as null", person.getFirstName(), IsNull.nullValue());
        assertThat("Didnt read firstName  as null", person.getLastName(), IsNull.nullValue());

    }
}
