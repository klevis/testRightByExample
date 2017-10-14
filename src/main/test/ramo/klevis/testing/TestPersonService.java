package ramo.klevis.testing;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonRepository;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonService {


    @Test
    public void shouldSavePersonForFirstTime() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();

        callPersonSaveService(dboPerson, modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenNameIsNull() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();
        dboPerson.setName(null);
        modelPerson.setName(null);

        callPersonSaveService(dboPerson, modelPerson);

    }


    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSurnameIsNull() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();
        dboPerson.setSurname(null);
        modelPerson.setSurname(null);

        callPersonSaveService(dboPerson, modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenBirthdateIsNull() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();
        dboPerson.setBirthDate(null);
        modelPerson.setBirthDate(null);

        callPersonSaveService(dboPerson, modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSocialNumberIsNull() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();
        dboPerson.setSocialSecurityNumber(null);
        modelPerson.setSocialSecurityNumber(null);

        callPersonSaveService(dboPerson, modelPerson);

    }

    @Test(expected = PersonNotExistException.class)
    public void shouldFailToUpdatePersonWhenNotExisting() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();

        callPersonUpdateService(dboPerson, modelPerson,false);

    }

    @Test
    public void shouldUpdatePersonWhenExisting() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();

        callPersonUpdateService(dboPerson, modelPerson,true);

    }

    private void callPersonSaveService(PersonDbo dboPerson, Person modelPerson) {
        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((PersonDbo) any())).thenReturn(dboPerson);
        PersonService personService = new PersonService(mockPersonRepository);

        Assert.assertThat(personService.savePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

    private void callPersonUpdateService(PersonDbo dboPerson, Person modelPerson,boolean existSocialSecurityNumber) {
        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((PersonDbo) any())).thenReturn(dboPerson);
        PersonService personService = new PersonService(mockPersonRepository);
        when(mockPersonRepository.exists(dboPerson.getSocialSecurityNumber())).thenReturn(existSocialSecurityNumber);
        Assert.assertThat(personService.updatePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }
    private PersonDbo createDboPerson() {
        PersonDbo person = new PersonDbo();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");
        return person;
    }

    private Person createModelPerson() {
        Person person = new Person();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");
        return person;
    }
}
