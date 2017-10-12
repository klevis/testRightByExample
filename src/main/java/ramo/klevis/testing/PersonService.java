package ramo.klevis.testing;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class PersonService implements IPersonService{

    private IPersonRepository personDao;

    @Autowired
    public PersonService(IPersonRepository personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person savePerson(Person person) {

        return personDao.save(person);
    }
    @Override
    public Person updatePerson(Person person) {

        if(personDao.exists(person.getSocialSecurityNumber())) {
            return personDao.save(person);
        }else{
            throw new PersonExistException("Cannot Update Person is not existing");
        }

    }
}
