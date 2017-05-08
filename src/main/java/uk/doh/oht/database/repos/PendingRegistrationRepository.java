package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.PendingRegistrationEntity;
import uk.doh.oht.database.model.RegistrationStatusEntity;

import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface PendingRegistrationRepository extends CrudRepository<PendingRegistrationEntity, Long> {
    List<PendingRegistrationEntity> findByRegistrationStatusEntity(final RegistrationStatusEntity registrationStatusEntity);
}
