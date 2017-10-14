package ramo.klevis.testing.change1;

import org.hamcrest.core.Is;
import org.junit.Test;
import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.model.Address;

import static org.junit.Assert.*;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class AddressConverterTest {

    @Test
    public void convertToModel() throws Exception {
        AddressDbo addressDbo = createAddressDbo();
        Address addressModel = new AddressConverter().convertToModel(addressDbo);
        assertThat(addressDbo.getCity(), Is.is(addressModel.getCity()));
        assertThat(addressDbo.getCountry(), Is.is(addressModel.getCountry()));
        assertThat(addressDbo.getHouseNumber(), Is.is(addressModel.getHouseNumber()));
        assertThat(addressDbo.getPostalCode(), Is.is(addressModel.getPostalCode()));
        assertThat(addressDbo.getStreet(), Is.is(addressModel.getStreet()));
    }

    @Test
    public void convertToDbo() throws Exception {
        Address addressModel = createAddressModel();
        AddressDbo addressDbo = new AddressConverter().convertToDbo(addressModel);
        assertThat(addressDbo.getCity(), Is.is(addressModel.getCity()));
        assertThat(addressDbo.getCountry(), Is.is(addressModel.getCountry()));
        assertThat(addressDbo.getHouseNumber(), Is.is(addressModel.getHouseNumber()));
        assertThat(addressDbo.getPostalCode(), Is.is(addressModel.getPostalCode()));
        assertThat(addressDbo.getStreet(), Is.is(addressModel.getStreet()));
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


    private AddressDbo createAddressDbo() {
        AddressDbo address = new AddressDbo();
        address.setCity("Muenchen");
        address.setCountry("Deutschland");
        address.setStreet("SomeStreet");
        address.setHouseNumber("12");
        address.setPostalCode("81888");
        return address;
    }

}