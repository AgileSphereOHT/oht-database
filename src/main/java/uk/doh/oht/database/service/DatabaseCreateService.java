package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Slf4j
@Service
public class DatabaseCreateService {
    private final RegistrationRepository registrationRepository;
    private final PendingRegistrationRepository pendingRegistrationRepository;
    private final RegistrationEntityCreator registrationEntityCreator;
    private final EntityRepositoryHelper entityRepositoryHelper;

    @Inject
    public DatabaseCreateService(final RegistrationRepository registrationRepository,
                                 final RegistrationEntityCreator registrationEntityCreator,
                                 final PendingRegistrationRepository pendingRegistrationRepository,
                                 final EntityRepositoryHelper entityRepositoryHelper) {
        this.registrationRepository = registrationRepository;
        this.registrationEntityCreator = registrationEntityCreator;
        this.pendingRegistrationRepository = pendingRegistrationRepository;
        this.entityRepositoryHelper = entityRepositoryHelper;
    }

    @Transactional
    public Boolean createS1Request(final PendingRegistrationData pendingRegistrationData) {
        try {
            log.info("Enter createS1Request");
            final RegistrationEntity registrationEntity = registrationEntityCreator.createRegistrationEntity(pendingRegistrationData);
            registrationRepository.save(registrationEntity);
            pendingRegistrationRepository.setPendingRegistrationDatesById(
                    entityRepositoryHelper.getCompletedStatus(),
                    pendingRegistrationData.getModifiedByUserId(),
                    pendingRegistrationData.getPendingRegistrationId()
            );
            return Boolean.TRUE;
        } finally {
            log.info("Exit createS1Request");
        }
    }
}
