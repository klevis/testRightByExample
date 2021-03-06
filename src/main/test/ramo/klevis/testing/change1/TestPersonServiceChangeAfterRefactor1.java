package ramo.klevis.testing.change1;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonDao;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static ramo.klevis.testing.TestData.*;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonServiceChangeAfterRefactor1 {

    @Test
    public void shouldSaveAddressedListPerEachPerson() {
        Person modelPerson = createModelPerson();
        ArrayList<Address> addressesModelList = new ArrayList<>();
        modelPerson.setAddresses(addressesModelList);
        Address addressModel = createAddressModel();
        addressesModelList.add(addressModel);

        assertPersonWasSavedWhenServiceCalled(modelPerson);
    }

    @Test
    public void shouldSavePersonForFirstTime() {
        Person modelPerson = createModelPerson();
        assertPersonWasSavedWhenServiceCalled(modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenNameIsNull() {
        Person modelPerson = createModelPerson();
        modelPerson.setName(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);

    }


    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSurnameIsNull() {
        Person modelPerson = createModelPerson();
        modelPerson.setSurname(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenBirthdateIsNull() {
        Person modelPerson = createModelPerson();
        modelPerson.setBirthDate(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);

    }

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void shouldNotSavePersonWhenSocialNumberIsNull() {
        Person modelPerson = createModelPerson();
        modelPerson.setSocialSecurityNumber(null);
        assertPersonWasSavedWhenServiceCalled(modelPerson);

    }

    @Test(expected = PersonNotExistException.class)
    public void shouldFailToUpdatePersonWhenNotExisting() {

        Person modelPerson = createModelPerson();
        callPersonUpdateService(modelPerson, false);

    }

    @Test
    public void shouldUpdatePersonWhenExisting() {

        Person modelPerson = createModelPerson();
        callPersonUpdateService(modelPerson, true);

    }

    private void assertPersonWasSavedWhenServiceCalled(Person modelPerson) {
        IPersonDao mockPersonRepository = mockPersonRepository();
        PersonServiceRefactoredChange1 personService = new PersonServiceRefactoredChange1(mockPersonRepository, new PersonConverter());

        Assert.assertThat(personService.savePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

    private IPersonDao mockPersonRepository() {
        IPersonDao mockPersonRepository = Mockito.mock(IPersonDao.class);
        when(mockPersonRepository.save((PersonDbo) any())).thenAnswer(new Answer<PersonDbo>() {
            @Override
            public PersonDbo answer(InvocationOnMock invocationOnMock) throws Throwable {
                PersonDbo o = (PersonDbo) invocationOnMock.getArguments()[0];
                return o;
            }
        });
        return mockPersonRepository;
    }

    private void callPersonUpdateService(Person modelPerson, boolean existSocialSecurityNumber) {
        IPersonDao mockPersonRepository = mockPersonRepository();
        PersonServiceRefactoredChange1 personService = new PersonServiceRefactoredChange1(mockPersonRepository, new PersonConverter());
        when(mockPersonRepository.exists(modelPerson.getSocialSecurityNumber())).thenReturn(existSocialSecurityNumber);
        Assert.assertThat(personService.updatePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

}
