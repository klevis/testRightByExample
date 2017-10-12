package ramo.klevis.testing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
@Component
public interface IPersonRepository extends JpaRepository<Person, String> {
}
