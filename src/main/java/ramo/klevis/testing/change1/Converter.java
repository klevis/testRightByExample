package ramo.klevis.testing.change1;

import ramo.klevis.testing.entity.AddressDbo;
import ramo.klevis.testing.model.Address;

/**
 * Created by klevis.ramo on 10/15/2017.
 */
public interface Converter<Model, Dbo> {

    Model convertToModel(Dbo dbo);

    Dbo convertToDbo(Model model);
}
