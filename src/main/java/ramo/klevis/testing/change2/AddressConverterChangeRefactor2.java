package ramo.klevis.testing.change2;

import ramo.klevis.testing.change1.Converter;
import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.model.Address;

import java.util.Optional;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public class AddressConverterChangeRefactor2 implements Converter<Optional<Address>, Optional<AddressDbo>> {

    @Override
    public Optional<Address> convertToModel(Optional<AddressDbo> addressDboOptional) {
        if (!addressDboOptional.isPresent()) {
            return Optional.empty();
        }
        AddressDbo addressDbo = addressDboOptional.get();
        Address address = new Address();
        address.setPostalCode(addressDbo.getPostalCode());
        address.setStreet(addressDbo.getStreet());
        address.setHouseNumber(addressDbo.getHouseNumber());
        address.setCountry(addressDbo.getCountry());
        address.setCity(addressDbo.getCity());
        return Optional.ofNullable(address);
    }

    @Override
    public Optional<AddressDbo> convertToDbo(Optional<Address> addressOptional) {
        if (!addressOptional.isPresent()) {
            return Optional.empty();
        }
        Address address = addressOptional.get();
        AddressDbo addressDbo = new AddressDbo();
        addressDbo.setPostalCode(address.getPostalCode());
        addressDbo.setStreet(address.getStreet());
        addressDbo.setHouseNumber(address.getHouseNumber());
        addressDbo.setCountry(address.getCountry());
        addressDbo.setCity(address.getCity());
        return Optional.ofNullable(addressDbo);
    }

}
