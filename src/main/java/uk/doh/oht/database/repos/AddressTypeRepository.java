package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.AddressTypeEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface AddressTypeRepository extends CrudRepository<AddressTypeEntity, Long> {
    AddressTypeEntity findByName(final String name);
}
