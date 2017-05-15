package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.CountryEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    CountryEntity findByName(final String name);
}
