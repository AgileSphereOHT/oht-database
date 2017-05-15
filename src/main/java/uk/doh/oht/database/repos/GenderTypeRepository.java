package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.GenderEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface GenderTypeRepository extends CrudRepository<GenderEntity, Long> {
    GenderEntity findByName(final String name);
}
