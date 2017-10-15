package ramo.klevis.testing.change2;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ramo.klevis.testing.change1.PersonConverter;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonRepository;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static ramo.klevis.testing.TestData.createAddressModel;
import static ramo.klevis.testing.TestData.createModelPerson;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonServiceRefactorChange2 {

    @Test(expected = PersonRequiredFieldsMissingException.class)
    public void testBug1345NullPointerWhenNullPerson() {
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonServiceChange2 personService = new PersonServiceChange2(mockPersonRepository, new PersonConverter());
        personService.savePerson(null);
    }

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
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonServiceRefactorChange2 personService = new PersonServiceRefactorChange2(mockPersonRepository, new PersonConverterChangeRefactor2());

        Assert.assertThat(personService.savePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

    private IPersonRepository mockPersonRepository() {
        IPersonRepository mockPersonRepository = Mockito.mock(IPersonRepository.class);
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
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonServiceRefactorChange2 personService = new PersonServiceRefactorChange2(mockPersonRepository, new PersonConverterChangeRefactor2());
        when(mockPersonRepository.exists(modelPerson.getSocialSecurityNumber())).thenReturn(existSocialSecurityNumber);
        Assert.assertThat(personService.updatePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

}
