package ramo.klevis.testing.change2;

import ramo.klevis.testing.change1.Converter;
import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class PersonConverterChangeRefactor2 implements Converter<Optional<Person>, Optional<PersonDbo>> {

    private Converter<Optional<Address>, Optional<AddressDbo>> personPersonDboConverter = new AddressConverterChangeRefactor2();

    @Override
    public Optional<Person> convertToModel(Optional<PersonDbo> personDboOptional) {
        if (!personDboOptional.isPresent()) {
            return Optional.empty();
        }
        PersonDbo personDbo = personDboOptional.get();
        Person person = new Person();
        person.setSocialSecurityNumber(personDbo.getSocialSecurityNumber());
        person.setSurname(personDbo.getSurname());
        person.setName(personDbo.getName());
        person.setBirthDate(personDbo.getBirthDate());
        if (personDbo.getAddresses() != null) {
            List<Address> addresses = personDbo.getAddresses().stream().map(e -> personPersonDboConverter.convertToModel(Optional.ofNullable(e)).orElse(null)).collect(Collectors.toList());
            person.setAddresses(addresses);
        }
        return Optional.ofNullable(person);
    }

    @Override
    public Optional<PersonDbo> convertToDbo(Optional<Person> personOptional) {
        if (!personOptional.isPresent()) {
            return Optional.empty();
        }
        Person person = personOptional.get();
        PersonDbo personDbo = new PersonDbo();
        personDbo.setSocialSecurityNumber(person.getSocialSecurityNumber());
        personDbo.setSurname(person.getSurname());
        personDbo.setName(person.getName());
        personDbo.setBirthDate(person.getBirthDate());

        if (person.getAddresses() != null) {
            List<AddressDbo> addressDbos = person.getAddresses().stream().map(e -> personPersonDboConverter.convertToDbo(Optional.ofNullable(e)).orElse(null)).collect(Collectors.toList());
            personDbo.setAddresses(addressDbos);
        }
        return Optional.ofNullable(personDbo);
    }

}
