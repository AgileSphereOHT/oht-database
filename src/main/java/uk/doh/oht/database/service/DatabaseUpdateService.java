package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.db.domain.RegistrationData;
import uk.doh.oht.database.repos.RegistrationRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by peterwhitehead on 09/05/2017.
 */
@Slf4j
@Service
public class DatabaseUpdateService {
    private final RegistrationRepository registrationRepository;
    private final EntityRepositoryHelper entityRepositoryHelper;

    @Inject
    public DatabaseUpdateService(final RegistrationRepository registrationRepository,
                                 final EntityRepositoryHelper entityRepositoryHelper) {
        this.registrationRepository = registrationRepository;
        this.entityRepositoryHelper = entityRepositoryHelper;
    }

    @Transactional
    public Boolean updateRegistration(final RegistrationData registrationData) {
        registrationRepository.setRegistrationDatesById(
                registrationData.getS073StartDate().getDate(),
                registrationData.getStartDate().getDate(),
                entityRepositoryHelper.getCompletedStatus(),
                registrationData.getModifiedByUserId(),
                registrationData.getRegistrationId());
        return Boolean.TRUE;
    }
}
