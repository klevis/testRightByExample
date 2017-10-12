package ramo.klevis.testing;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonService {

    @Test
    public void shouldSavePersonForFirstTime() {

        Person person = new Person();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");

        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((Person) any())).thenReturn(person);
        PersonService personService = new PersonService(mockPersonRepository);

        Assert.assertThat(personService.savePerson(person), IsEqual.equalTo(person));

    }

    @Test(expected = PersonExistException.class)
    public void shouldFailToUpdatePersonWhenNotExisting() {

        Person person = new Person();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");

        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((Person) any())).thenReturn(person);
        PersonService personService = new PersonService(mockPersonRepository);

        Assert.assertThat(personService.updatePerson(person), IsEqual.equalTo(person));

    }

    @Test
    public void shouldUpdatePersonWhenExisting() {

        Person person = new Person();
        person.setBirthDate(new Date());
        person.setName("Klevis");
        person.setSurname("Ramo");
        person.setSocialSecurityNumber("12345");

        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((Person) any())).thenReturn(person);
        when(mockPersonRepository.exists(person.getSocialSecurityNumber())).thenReturn(true);
        PersonService personService = new PersonService(mockPersonRepository);

        Assert.assertThat(personService.updatePerson(person), IsEqual.equalTo(person));

    }
}
