package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.ContactDetailTypeEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface ContactDetailTypeRepository extends CrudRepository<ContactDetailTypeEntity, Long> {
    ContactDetailTypeEntity findByName(final String name);
}
