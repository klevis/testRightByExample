package ramo.klevis.testing.change1;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import ramo.klevis.testing.TestData;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.model.Person;

import static org.junit.Assert.assertThat;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class TestPersonConverter {


    @Test
    public void convertToModel() throws Exception {
        PersonDbo dboPerson = TestData.createDboPerson();
        Person person = new PersonConverter().convertToModel(dboPerson);
        assertThat(dboPerson.getAddresses(), IsEqual.equalTo(person.getAddresses()));
        assertThat(dboPerson.getBirthDate(), IsEqual.equalTo(person.getBirthDate()));
        assertThat(dboPerson.getName(), IsEqual.equalTo(person.getName()));
        assertThat(dboPerson.getSocialSecurityNumber(), IsEqual.equalTo(person.getSocialSecurityNumber()));
        assertThat(dboPerson.getSurname(), IsEqual.equalTo(person.getSurname()));
    }

    @Test
    public void convertToDbo() throws Exception {
        Person modelPerson = TestData.createModelPerson();
        PersonDbo dboPerson = new PersonConverter().convertToDbo(modelPerson);
        assertThat(dboPerson.getAddresses(), IsEqual.equalTo(modelPerson.getAddresses()));
        assertThat(dboPerson.getBirthDate(), IsEqual.equalTo(modelPerson.getBirthDate()));
        assertThat(dboPerson.getName(), IsEqual.equalTo(modelPerson.getName()));
        assertThat(dboPerson.getSocialSecurityNumber(), IsEqual.equalTo(modelPerson.getSocialSecurityNumber()));
        assertThat(dboPerson.getSurname(), IsEqual.equalTo(modelPerson.getSurname()));

    }

}