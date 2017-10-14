package ramo.klevis.testing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by klevis.ramo on 10/14/2017.
 */
@Entity
public class PersonDbo {

    @Id
    private String socialSecurityNumber;
    private String name;
    private String surname;
    private Date birthDate;

    @OneToMany
    private List<AddressDbo> addresses;

    public List<AddressDbo> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDbo> addresses) {
        this.addresses = addresses;
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
