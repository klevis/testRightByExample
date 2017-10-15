package ramo.klevis.testing.change2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ramo.klevis.testing.IPersonService;
import ramo.klevis.testing.change1.Converter;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonDao;

import java.util.Optional;

import static java.util.Optional.*;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
@SuppressWarnings("ALL")
public class PersonServiceRefactorChange2 implements IPersonService {

    private IPersonDao personDao;

    private Converter<Optional<Person>, Optional<PersonDbo>> personPersonDboConverter;

    @Autowired
    public PersonServiceRefactorChange2(IPersonDao personDao, Converter<Optional<Person>, Optional<PersonDbo>> personPersonDboConverter) {
        this.personDao = personDao;
        this.personPersonDboConverter = personPersonDboConverter;
    }

    @Override
    @Transactional
    public Person savePerson(Person person) {

        if (areRequiredFieldFilled(person)) {
            Person personResponse = convertAndSave(person);
            return personResponse;
        } else {
            throw new PersonRequiredFieldsMissingException("Required Fields for Person are missing!" + person);
        }
    }


    @Override
    @Transactional
    public Person updatePerson(Person person) {

        if (personDao.exists(person.getSocialSecurityNumber())) {
            Person personResponse = convertAndSave(person);
            return personResponse;
        } else {
            throw new PersonNotExistException("Cannot Update Person is not existing");
        }

    }

    private Person convertAndSave(Person person) {
        PersonDbo savedPerson = personDao.save(personPersonDboConverter.convertToDbo(ofNullable(person))
                .orElseThrow(() -> new PersonRequiredFieldsMissingException("Required Fields for Person are missing!" + person)));
        return personPersonDboConverter.convertToModel(ofNullable(savedPerson)).orElse(null);
    }

    private boolean areRequiredFieldFilled(Person person) {
        return person != null && person.getBirthDate() != null &&
                !StringUtils.isEmpty(person.getName()) &&
                !StringUtils.isEmpty(person.getSurname()) &&
                !StringUtils.isEmpty(person.getSocialSecurityNumber());
    }
}
