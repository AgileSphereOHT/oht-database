package uk.doh.oht.database.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.BenefitTypeEntity;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Repository
public interface BenefitTypeRepository extends CrudRepository<BenefitTypeEntity, Long> {
    BenefitTypeEntity findByName(final String name);
}
