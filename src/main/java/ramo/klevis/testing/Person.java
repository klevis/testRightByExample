package ramo.klevis.testing;

import java.util.Date;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
public class Person {

    private String socialSecurityNumber;
    private String name;
    private String surname;
    private Date birthDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!socialSecurityNumber.equals(person.socialSecurityNumber)) return false;
        if (!name.equals(person.name)) return false;
        if (!surname.equals(person.surname)) return false;
        return birthDate.equals(person.birthDate);
    }

    @Override
    public int hashCode() {
        int result = socialSecurityNumber.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + birthDate.hashCode();
        return result;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
