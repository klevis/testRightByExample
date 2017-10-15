package ramo.klevis.testing.change1;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import ramo.klevis.testing.TestData;
import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.model.Address;

import static org.junit.Assert.assertThat;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class TestAddressConverter {

    @Test
    public void convertToModel() throws Exception {
        AddressDbo addressDbo = TestData.createAddressDbo();
        Address addressModel = new AddressConverter().convertToModel(addressDbo);
        assertThat(addressDbo.getCity(), Is.is(addressModel.getCity()));
        assertThat(addressDbo.getCountry(), Is.is(addressModel.getCountry()));
        assertThat(addressDbo.getHouseNumber(), Is.is(addressModel.getHouseNumber()));
        assertThat(addressDbo.getPostalCode(), Is.is(addressModel.getPostalCode()));
        assertThat(addressDbo.getStreet(), Is.is(addressModel.getStreet()));
    }

    @Test
    public void shouldReturnNUllWhenDboNull() {
        Address address = new AddressConverter().convertToModel(null);
        assertThat(address, IsNull.nullValue());
    }

    @Test
    public void shouldReturnNUllWhenModelNull() {
        AddressDbo addressDbo = new AddressConverter().convertToDbo(null);
        assertThat(addressDbo, IsNull.nullValue());
    }

    @Test
    public void convertToDbo() throws Exception {
        Address addressModel = TestData.createAddressModel();
        AddressDbo addressDbo = new AddressConverter().convertToDbo(addressModel);
        assertThat(addressDbo.getCity(), Is.is(addressModel.getCity()));
        assertThat(addressDbo.getCountry(), Is.is(addressModel.getCountry()));
        assertThat(addressDbo.getHouseNumber(), Is.is(addressModel.getHouseNumber()));
        assertThat(addressDbo.getPostalCode(), Is.is(addressModel.getPostalCode()));
        assertThat(addressDbo.getStreet(), Is.is(addressModel.getStreet()));
    }


}