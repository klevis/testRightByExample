package ramo.klevis.testing.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by klevis.ramo on 10/14/2017.
 */
@Entity
public class AddressDbo {

    @Id
    private long id;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String country;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
