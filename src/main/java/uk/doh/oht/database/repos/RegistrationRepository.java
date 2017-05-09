package uk.doh.oht.database.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.model.RegistrationStatusEntity;

import java.util.Date;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Repository
public interface RegistrationRepository extends CrudRepository<RegistrationEntity, Long> {
    RegistrationEntity findByCitizenEntityNinoInIgnoreCase(final String nino);
    RegistrationEntity findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirth(
            final String firstName, final String lastName, final Date dateOfBirth);

    @Modifying
    @Query("update RegistrationEntity re set re.s073StartDate = ?1, re.startDate = ?2, re.registrationStatusEntity =?3 where re.registrationId = ?4")
    void setRegistrationDatesById(final Date s073StartDate, Date startDate, RegistrationStatusEntity registrationStatusEntity, long registrationId);
}
