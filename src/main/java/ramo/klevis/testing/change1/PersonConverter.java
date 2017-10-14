package ramo.klevis.testing.change1;

import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class PersonConverter implements Converter<Person, PersonDbo> {

    private Converter<Address, AddressDbo> personPersonDboConverter = new AddressConverter();

    @Override
    public Person convertToModel(PersonDbo personDbo) {
        Person person = new Person();
        person.setSocialSecurityNumber(personDbo.getSocialSecurityNumber());
        person.setSurname(personDbo.getSurname());
        person.setName(personDbo.getName());
        person.setBirthDate(personDbo.getBirthDate());
        if (personDbo.getAddresses() != null) {
            List<Address> addresses = personDbo.getAddresses().stream().map(e -> personPersonDboConverter.convertToModel(e)).collect(Collectors.toList());
            person.setAddresses(addresses);
        }
        return person;
    }

    @Override
    public PersonDbo convertToDbo(Person person) {
        PersonDbo personDbo = new PersonDbo();
        personDbo.setSocialSecurityNumber(person.getSocialSecurityNumber());
        personDbo.setSurname(person.getSurname());
        personDbo.setName(person.getName());
        personDbo.setBirthDate(person.getBirthDate());

        if (person.getAddresses() != null) {
            List<AddressDbo> addressDbos = person.getAddresses().stream().map(e -> personPersonDboConverter.convertToDbo(e)).collect(Collectors.toList());
            personDbo.setAddresses(addressDbos);
        }
        return personDbo;
    }

}
