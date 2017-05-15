package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.database.domain.PendingRegistrationData;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.repos.RegistrationRepository;
import uk.doh.oht.database.repos.RegistrationStatusRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Slf4j
@Service
public class DatabaseCreateService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationEntityCreator registrationEntityCreator;

    @Inject
    public DatabaseCreateService(final RegistrationRepository registrationRepository,
                                 final RegistrationStatusRepository registrationStatusRepository,
                                 final RegistrationEntityCreator registrationEntityCreator) {
        this.registrationRepository = registrationRepository;
        this.registrationEntityCreator = registrationEntityCreator;
    }

    @Transactional
    public Boolean createS1Request(final PendingRegistrationData pendingRegistrationData) {
        final RegistrationEntity registrationEntity = registrationEntityCreator.createRegistrationEntity(pendingRegistrationData);
        registrationRepository.save(registrationEntity);
        return Boolean.TRUE;
    }
}
