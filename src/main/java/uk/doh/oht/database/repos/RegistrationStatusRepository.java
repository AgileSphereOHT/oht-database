package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.RegistrationStatusEntity;

import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface RegistrationStatusRepository extends CrudRepository<RegistrationStatusEntity, Long> {
    RegistrationStatusEntity findByName(final String name);
    List<RegistrationStatusEntity> findByNameIn(final List<String> name);
}
