package uk.doh.oht.database.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.model.RegistrationStatusEntity;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Long> {
    RegistrationEntity findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(final String nino, final List<RegistrationStatusEntity> statuses);

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

    default List<RegistrationEntity> getRegistrationEntity(final EntityManager entityManager, final String firstName, final String lastName, final Date dateOfBirth, final List<RegistrationStatusEntity> statuses) {
        final String statement = "SELECT * FROM registration r left outer join citizen c on r.citizen_id = c.citizen_id  where c.last_name % ?1 and c.first_name % ?2 and to_char(c.date_of_birth, 'YYYY-MM-DD') % ?3 and r.registration_status_id in ?4";
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final javax.persistence.Query query = entityManager.createNativeQuery(statement, RegistrationEntity.class);
        query.setParameter(1, lastName);
        query.setParameter(2, firstName);
        query.setParameter(3, simpleDateFormat.format(dateOfBirth));
        query.setParameter(4, statuses.stream().map(status -> status.getRegistrationStatusId()).collect(toList()));

        return query.getResultList();
    }
}
