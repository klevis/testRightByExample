package ramo.klevis.testing;

import ramo.klevis.testing.model.Person;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public interface IPersonService {
    Person savePerson(Person person);

    Person updatePerson(Person person);
}
