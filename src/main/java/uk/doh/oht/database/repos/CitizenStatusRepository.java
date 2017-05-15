package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.CitizenStatusEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface CitizenStatusRepository extends CrudRepository<CitizenStatusEntity, Long> {
    CitizenStatusEntity findByName(final String name);
}
