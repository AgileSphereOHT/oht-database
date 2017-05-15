package uk.doh.oht.database.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Query("select coalesce(count(pre), 0) from PendingRegistrationEntity pre where pre.lastUpdatedBy=?1 and date(pre.lastUpdatedDate) = current_date() and pre.registrationStatusEntity.registrationStatusId = ?2")
    Long findDailyCountOfS1RequestsByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String userName, final Long registrationStatusId);

    @Modifying
    @Query("update PendingRegistrationEntity pre set pre.registrationStatusEntity = ?1, pre.lastUpdatedBy = ?2, pre.lastUpdatedDate = CURRENT_TIMESTAMP() where pre.pendingRegistrationId = ?3")
    void setPendingRegistrationDatesById(final RegistrationStatusEntity registrationStatusEntity,
                                         final String modifiedBy,
                                         final long pendingRegistrationId);

    @Query("select coalesce(count(pre), 0) from PendingRegistrationEntity pre where pre.lastUpdatedBy=?1 and MONTH(pre.lastUpdatedDate) = MONTH(current_date()) and pre.registrationStatusEntity.registrationStatusId = ?2")
    Long findMonthlyCountOfS1RequestsByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String userName, final long registrationStatusId);
}
