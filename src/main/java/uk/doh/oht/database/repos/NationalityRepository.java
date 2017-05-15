package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.NationalityEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface NationalityRepository extends CrudRepository<NationalityEntity, Long> {
    NationalityEntity findByName(final String name);
}
