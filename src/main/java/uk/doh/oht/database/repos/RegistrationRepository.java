package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.RegistrationEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Long> {
    RegistrationEntity findByCitizenEntityNinoInIgnoreCase(final String ninos);
    RegistrationEntity findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirth(
            final String firstName, final String lastName, final Date dateOfBirth);
}
