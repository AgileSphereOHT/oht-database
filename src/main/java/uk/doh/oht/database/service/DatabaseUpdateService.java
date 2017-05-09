package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.model.RegistrationStatusEntity;
import uk.doh.oht.database.repos.RegistrationRepository;
import uk.doh.oht.database.repos.RegistrationStatusRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by peterwhitehead on 09/05/2017.
 */
@Slf4j
@Service
public class DatabaseUpdateService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationStatusRepository registrationStatusRepository;

    @Inject
    public DatabaseUpdateService(final RegistrationRepository registrationRepository,
                                 final RegistrationStatusRepository registrationStatusRepository) {
        this.registrationRepository = registrationRepository;
        this.registrationStatusRepository = registrationStatusRepository;
    }

    @Transactional
    public Boolean updateRegistration(final RegistrationData registrationData) {
        RegistrationStatusEntity registrationStatusEntity =
                registrationStatusRepository.findByName(registrationData.getRegistrationStatus());
        registrationRepository.setRegistrationDatesById(
                registrationData.getS073StartDate().getDate(),
                registrationData.getStartDate().getDate(),
                registrationStatusEntity,
                registrationData.getRegistrationId());
        return Boolean.TRUE;
    }
}
