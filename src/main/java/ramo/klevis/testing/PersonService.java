package ramo.klevis.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonRepository;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class PersonService implements IPersonService {

    private IPersonRepository personDao;

    @Autowired
    public PersonService(IPersonRepository personDao) {
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public Person savePerson(Person person) {

        if (areRequiredFieldFilled(person)) {
            Person personResponse = convertAndSave(person);
            return personResponse;
        } else {
            throw new PersonRequiredFieldsMissingException("Required Fields for Person are missing!" + person.toString());
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
        PersonDbo savedPerson = personDao.save(convertToDbo(person));
        return convertToModel(savedPerson);
    }

    /**
     * Convert to Database object
     *
     * @param person
     * @return
     */
    private PersonDbo convertToDbo(Person person) {

        PersonDbo personDbo = new PersonDbo();
        personDbo.setSocialSecurityNumber(person.getSocialSecurityNumber());
        personDbo.setSurname(person.getSurname());
        personDbo.setName(person.getName());
        personDbo.setBirthDate(person.getBirthDate());
        return personDbo;
    }

    /**
     * Convert to Model object
     *
     * @param personDbo
     * @return
     */
    private Person convertToModel(PersonDbo personDbo) {
        Person person = new Person();
        person.setSocialSecurityNumber(personDbo.getSocialSecurityNumber());
        person.setSurname(personDbo.getSurname());
        person.setName(personDbo.getName());
        person.setBirthDate(personDbo.getBirthDate());
        return person;
    }

    private boolean areRequiredFieldFilled(Person person) {
        return person.getBirthDate() != null &&
                !StringUtils.isEmpty(person.getName()) &&
                !StringUtils.isEmpty(person.getSurname()) &&
                !StringUtils.isEmpty(person.getSocialSecurityNumber());
    }
}
