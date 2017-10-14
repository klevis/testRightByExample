package ramo.klevis.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ramo.klevis.testing.entity.PersonDbo;

/**
 * Created by klevis.ramo on 10/12/2017.
 */
@Component
public interface IPersonRepository extends JpaRepository<PersonDbo, String> {
}
