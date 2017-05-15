package uk.doh.oht.database.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.model.RegistrationStatusEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Long> {
    RegistrationEntity findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(final String nino, final List<RegistrationStatusEntity> statuses);
    RegistrationEntity findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirthAndRegistrationStatusEntityIn(
            final String firstName, final String lastName, final Date dateOfBirth, final List<RegistrationStatusEntity> statuses);

    @Modifying
    @Query("update RegistrationEntity re set re.s073StartDate = ?1, re.startDate = ?2, re.registrationStatusEntity = ?3, re.lastUpdatedBy = ?4, re.lastUpdatedDate = CURRENT_TIMESTAMP() where re.registrationId = ?5")
    void setRegistrationDatesById(final Date s073StartDate,
                                  final Date startDate,
                                  final RegistrationStatusEntity registrationStatusEntity,
                                  final String modifiedBy,
                                  final long registrationId);

    @Query("select coalesce(count(re), 0) from RegistrationEntity re where re.lastUpdatedBy=?1 and date(re.lastUpdatedDate) = current_date() and re.registrationStatusEntity.registrationStatusId = ?2")
    Long findDailyCountOfCompletedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String lastUpdatedBy, final Long registrationStatusId);

    @Query("select coalesce(count(re), 0) from RegistrationEntity re where re.lastUpdatedBy=?1 and date(re.lastUpdatedDate) = current_date() and re.registrationStatusEntity.registrationStatusId = ?2")
    Long findDailyCountOfRejectedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String userName, final Long registrationStatusId);

    @Query("select coalesce(count(re), 0) from RegistrationEntity re where re.lastUpdatedBy=?1 and MONTH(re.lastUpdatedDate) = MONTH(current_date()) and re.registrationStatusEntity.registrationStatusId = ?2")
    Long findMonthlyCountOfCompletedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String userName, final long registrationStatusId);

    @Query("select coalesce(count(re), 0) from RegistrationEntity re where re.lastUpdatedBy=?1 and MONTH(re.lastUpdatedDate) = MONTH(current_date()) and re.registrationStatusEntity.registrationStatusId = ?2")
    Long findMonthlyCountOfRejectedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(final String userName, final long registrationStatusId);
}
