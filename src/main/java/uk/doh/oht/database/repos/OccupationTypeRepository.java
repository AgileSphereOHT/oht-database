package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.OccupationTypeEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface OccupationTypeRepository extends CrudRepository<OccupationTypeEntity, Long> {
    OccupationTypeEntity findByName(final String name);
}
