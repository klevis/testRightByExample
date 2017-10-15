package ramo.klevis.testing;

import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;

import java.util.Date;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class TestData {
    public static Address createAddressModel() {
        Address address = new Address();
        address.setCity("Muenchen");
        address.setCountry("Deutschland");
        address.setStreet("SomeStreet");
        address.setHouseNumber("12");
        address.setPostalCode("81888");
        return address;
    }

    public static AddressDbo createAddressDbo() {
        AddressDbo address = new AddressDbo();
        address.setCity("Muenchen");
        address.setCountry("Deutschland");
        address.setStreet("SomeStreet");
        address.setHouseNumber("12");
        address.setPostalCode("81888");
        return address;
    }

    public static PersonDbo createDboPerson() {
        PersonDbo person = new PersonDbo();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");
        return person;
    }

    public static Person createModelPerson() {
        Person person = new Person();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");
        return person;
    }
}
