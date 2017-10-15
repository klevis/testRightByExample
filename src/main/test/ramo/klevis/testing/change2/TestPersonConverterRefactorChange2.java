package ramo.klevis.testing.change2;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import ramo.klevis.testing.TestData;
import ramo.klevis.testing.change1.PersonConverter;
import ramo.klevis.testing.entity.PersonDbo;
import ramo.klevis.testing.model.Person;

import static org.junit.Assert.assertThat;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class TestPersonConverterRefactorChange2 {


    @Test
    public void convertToModel() throws Exception {
        PersonDbo dboPerson = TestData.createDboPerson();
        Person person = new PersonConverterChangeRefactor2().convertToModel(dboPerson);
        assertThat(dboPerson.getAddresses(), IsEqual.equalTo(person.getAddresses()));
        assertThat(dboPerson.getBirthDate(), IsEqual.equalTo(person.getBirthDate()));
        assertThat(dboPerson.getName(), IsEqual.equalTo(person.getName()));
        assertThat(dboPerson.getSocialSecurityNumber(), IsEqual.equalTo(person.getSocialSecurityNumber()));
        assertThat(dboPerson.getSurname(), IsEqual.equalTo(person.getSurname()));
    }


    @Test
    public void shouldReturnNUllWhenDboNull() {
        Person person = new PersonConverter().convertToModel(null);
        assertThat(person, IsNull.nullValue());
    }

    @Test
    public void shouldReturnNUllWhenModelNull() {
        PersonDbo dboPerson = new PersonConverter().convertToDbo(null);
        assertThat(dboPerson, IsNull.nullValue());
    }

    @Test
    public void convertToDbo() throws Exception {
        Person modelPerson = TestData.createModelPerson();
        PersonDbo dboPerson = new PersonConverterChangeRefactor2().convertToDbo(modelPerson);
        assertThat(dboPerson.getAddresses(), IsEqual.equalTo(modelPerson.getAddresses()));
        assertThat(dboPerson.getBirthDate(), IsEqual.equalTo(modelPerson.getBirthDate()));
        assertThat(dboPerson.getName(), IsEqual.equalTo(modelPerson.getName()));
        assertThat(dboPerson.getSocialSecurityNumber(), IsEqual.equalTo(modelPerson.getSocialSecurityNumber()));
        assertThat(dboPerson.getSurname(), IsEqual.equalTo(modelPerson.getSurname()));

    }

}