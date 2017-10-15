package ramo.klevis.testing;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static ramo.klevis.testing.TestData.createDboPerson;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonService {

    @Test
    public void shouldSavePersonForFirstTime() {
        Person modelPerson = TestData.createModelPerson();
        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenNameIsNull() {
        Person modelPerson = TestData.createModelPerson();
        modelPerson.setName(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSurnameIsNull() {
        Person modelPerson = TestData.createModelPerson();
        modelPerson.setSurname(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenBirthdateIsNull() {
        Person modelPerson = TestData.createModelPerson();
        modelPerson.setBirthDate(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSocialNumberIsNull() {
        Person modelPerson = TestData.createModelPerson();
        modelPerson.setSocialSecurityNumber(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test(expected = PersonNotExistException.class)
    public void shouldFailToUpdatePersonWhenNotExisting() {
        Person modelPerson = TestData.createModelPerson();
        callPersonUpdateService(modelPerson, false);
    }

    @Test
    public void shouldUpdatePersonWhenExisting() {
        Person modelPerson = TestData.createModelPerson();
        callPersonUpdateService(modelPerson, true);
    }

    private void assertPersonWasSavedWhenServiceCalled(Person modelPerson) {
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonService personService = new PersonService(mockPersonRepository);

        Assert.assertThat(personService.savePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

    private IPersonRepository mockPersonRepository() {
        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
        when(mockPersonRepository.save((PersonDbo) any())).thenAnswer(invocationOnMock -> {
            PersonDbo o = (PersonDbo) invocationOnMock.getArguments()[0];
            return o;
        });
        return mockPersonRepository;
    }

    private void callPersonUpdateService(Person modelPerson, boolean existSocialSecurityNumber) {
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonService personService = new PersonService(mockPersonRepository);
        when(mockPersonRepository.exists(modelPerson.getSocialSecurityNumber())).thenReturn(existSocialSecurityNumber);
        Assert.assertThat(personService.updatePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }
}
