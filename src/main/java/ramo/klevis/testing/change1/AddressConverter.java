package ramo.klevis.testing.change1;

import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.model.Address;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class AddressConverter implements Converter<Address, AddressDbo> {

    @Override
    public Address convertToModel(AddressDbo addressDbo) {
        Address address = new Address();
        address.setPostalCode(addressDbo.getPostalCode());
        address.setStreet(addressDbo.getStreet());
        address.setHouseNumber(addressDbo.getHouseNumber());
        address.setCountry(addressDbo.getCountry());
        address.setCity(addressDbo.getCity());
        return address;
    }

    @Override
    public AddressDbo convertToDbo(Address address) {
        AddressDbo addressDbo = new AddressDbo();
        addressDbo.setPostalCode(address.getPostalCode());
        addressDbo.setStreet(address.getStreet());
        addressDbo.setHouseNumber(address.getHouseNumber());
        addressDbo.setCountry(address.getCountry());
        addressDbo.setCity(address.getCity());
        return addressDbo;
    }

}
