package ramo.klevis.testing;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ramo.klevis.testing.change1.PersonServiceChange1;
import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.exception.PersonNotExistException;
import ramo.klevis.testing.exception.PersonRequiredFieldsMissingException;
import ramo.klevis.testing.model.Address;
import ramo.klevis.testing.model.Person;
import ramo.klevis.testing.repository.IPersonRepository;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class TestPersonService {


    @Test
    public void shouldSaveAddressedListPerEachPerson() {
        Person modelPerson = createModelPerson();
        ArrayList<Address> addressesModelList = new ArrayList<>();
        modelPerson.setAddresses(addressesModelList);
        Address addressModel = createAddressModel();
        addressesModelList.add(addressModel);

        assertPersonWasSavedWhenServiceWithChangeOneCalled(modelPerson);

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

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();

        callPersonUpdateService(dboPerson, modelPerson, false);

    }

    @Test
    public void shouldUpdatePersonWhenExisting() {

        PersonDbo dboPerson = createDboPerson();
        Person modelPerson = createModelPerson();

        callPersonUpdateService(dboPerson, modelPerson, true);

    }

    private void assertPersonWasSavedWhenServiceWithChangeOneCalled(Person modelPerson) {
        PersonServiceChange1 personServiceChangeOne = new PersonServiceChange1(mockPersonRepository());
        Assert.assertThat(personServiceChangeOne.savePerson(modelPerson), IsEqual.equalTo(modelPerson));
    }

    private void assertPersonWasSavedWhenServiceCalled(Person modelPerson) {
        IPersonRepository mockPersonRepository = mockPersonRepository();
        PersonService personService = new PersonService(mockPersonRepository);

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

    private void callPersonUpdateService(PersonDbo dboPerson, Person modelPerson, boolean existSocialSecurityNumber) {
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

    private AddressDbo createAddressDbo() {
        AddressDbo addressDbo = new AddressDbo();
        addressDbo.setCity("Muenchen");
        addressDbo.setCountry("Deutschland");
        addressDbo.setStreet("SomeStreet");
        addressDbo.setHouseNumber("12");
        addressDbo.setPostalCode("81888");
        return addressDbo;
    }

    private Address createAddressModel() {
        Address address = new Address();
        address.setCity("Muenchen");
        address.setCountry("Deutschland");
        address.setStreet("SomeStreet");
        address.setHouseNumber("12");
        address.setPostalCode("81888");
        return address;
    }
}
